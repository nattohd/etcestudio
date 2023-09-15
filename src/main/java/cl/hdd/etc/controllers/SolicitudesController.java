package cl.hdd.etc.controllers;

import cl.hdd.etc.entities.Solicitud;
import cl.hdd.etc.services.SolicitudesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class SolicitudesController {
    @Autowired
    private SolicitudesService solicitudesService;
    private boolean isTipoValid(String tipoSolicitud){
        String tipo1 = "SolicitudCedula";
        String tipo2= "RetiroCedula";
        String tipo3= "SolicitudCertificadoNac";
        String tipo4 = "SolicitudCertificadoDef";
        return tipo1.equalsIgnoreCase(tipoSolicitud)||
                tipo2.equalsIgnoreCase(tipoSolicitud)||
                tipo3.equalsIgnoreCase(tipoSolicitud)||
                tipo4.equalsIgnoreCase(tipoSolicitud);
    }
    private boolean esRutValido (String rut){
        Pattern pattern = Pattern.compile("^[0-9]+-[0-9kK]{1}$");
        Matcher matcher = pattern.matcher(rut);
        if (!matcher.matches()) {
            return false;
        }
        String[] stringRut = rut.split("-");
        return stringRut[1].toLowerCase().equals(dv(stringRut[0]));
    }
    public static String dv(String rut) {
        int M = 0, S = 1, T = Integer.parseInt(rut);
        for (; T != 0; T = (int) Math.floor(T / 10)) {
            S = (S + T % 10 * (9 - M++ % 6)) % 11;
        }
        return (S > 0) ? String.valueOf(S - 1) : "k";
    }

    @PostMapping("/ingresarSolicitud")
    public ResponseEntity<Solicitud> create(@RequestBody Solicitud solicitud){
        if (!isTipoValid(solicitud.getTipoSolicitud())||!esRutValido(solicitud.getRut())){
            return  ResponseEntity.badRequest().build();
        }
        try{
            return ResponseEntity.ok().body(this.solicitudesService.create(solicitud));
        }
        catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/verSolicitudes")
    public ResponseEntity<List<Solicitud>> getAll(){
        try{
           return ResponseEntity.ok(this.solicitudesService.getAll());
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body(null);
        }
    }
    @GetMapping("/verSolicitudes/{tipoSolicitud}")
    public ResponseEntity<List<Solicitud>> filter(@PathVariable String tipoSolicitud){
        try{
            return ResponseEntity.ok(this.solicitudesService.filter(tipoSolicitud));
        }
        catch (Exception ex){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/verSolicitudes/fechas/{fecha1}/{fecha2}")
    public ResponseEntity<List<Solicitud>> filterFechas(@PathVariable String fecha1, @PathVariable String fecha2){
        try{
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date date1 = dateFormat.parse(fecha1);
                Date date2 = dateFormat.parse(fecha2);
                return ResponseEntity.ok(this.solicitudesService.filterFecha(date1, date2));
            } catch (ParseException ex) {
                return ResponseEntity.badRequest().build();
            }
        }catch (Exception ex){
            return ResponseEntity.internalServerError().build();
        }

    }
}
