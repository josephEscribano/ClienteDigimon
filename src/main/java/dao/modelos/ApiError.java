package dao.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ApiError {
    private String message;
    private LocalDate date;

    @Override
    public String toString() {
        return "message='" + message + '\'' +
                ", date=" + date;
    }
}
