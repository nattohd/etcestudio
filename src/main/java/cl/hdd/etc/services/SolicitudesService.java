package cl.hdd.etc.services;

import cl.hdd.etc.entities.Solicitud;

import java.util.List;

public interface SolicitudesService {

    Solicitud create(Solicitud solicitud);
    List<Solicitud> getAll();
    List<Solicitud> filter(String tipoSolicitud);

}
