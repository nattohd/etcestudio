package cl.hdd.etc.controllers;

import cl.hdd.etc.entities.Solicitud;
import cl.hdd.etc.services.SolicitudesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SolicitudesController {

    @Autowired
    private SolicitudesService solicitudesService;

    @PostMapping("/ingresarSolicitud")
    public ResponseEntity<Solicitud> create(@RequestBody Solicitud solicitud){
        return ResponseEntity.ok().body(this.solicitudesService.create(solicitud));
    }


    @GetMapping("/verSolicitudes")
    public ResponseEntity<List<Solicitud>> getAll(){
        try{
           return ResponseEntity.ok(this.solicitudesService.getAll());
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body(null);
        }
    }

}
