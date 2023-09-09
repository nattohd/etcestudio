package cl.hdd.etc.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Solicitud {
    private String rut;
    private String nombre;
    private String apellido;
    private String tipoSolicitud;
}
