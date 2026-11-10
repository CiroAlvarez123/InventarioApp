package cesur.dam.inventarioappg1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class Equipo {
    private Long id;
    private String nombre;
    private String categoria;
    private Boolean disponible;
}
