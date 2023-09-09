package cl.hdd.etc.services;

import cl.hdd.etc.entities.Solicitud;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolicitudesServiceImpl implements SolicitudesService {

    private static List<Solicitud> solicitudes = new ArrayList<>();

    @Override
    public Solicitud create(Solicitud solicitud){
        solicitudes.add(solicitud);
        return solicitud;
    }
    @Override
    public List<Solicitud> getAll(){
        return solicitudes;
    }

    @Override
    public  List<Solicitud> filter(String tipoSolicitud){
        return solicitudes.stream()
                .filter(s -> s.getTipoSolicitud().equalsIgnoreCase(tipoSolicitud))
                .collect(Collectors.toList());
    }


}
