package dao.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Digimon {

    private int id;
    private String name;
    private int idSerie;
    private Atributos atributo;
    private Nivel nivel;
    private LocalDate nacimiento;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Digimon digimon = (Digimon) o;
        return id == digimon.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {

        return "id = " + id + "name='" + name + '\'' +
                ", atributo=" + atributo +
                ", nivel=" + nivel +
                ", nacimiento=" + nacimiento;
    }
}
