package cl.hdd.etc.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Solicitud {
    private String rut;
    private String nombre;
    private String apellido;
    private String tipoSolicitud;
    @JsonFormat(pattern = "dd-MM-yyy")
    private Date fechaNacimiento;
}
