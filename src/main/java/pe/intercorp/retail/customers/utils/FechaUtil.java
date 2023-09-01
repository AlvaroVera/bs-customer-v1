package pe.intercorp.retail.customers.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FechaUtil {
    public static final String FORMATO_FECHA_HORA = "yyyy-MM-dd HH:mm:ss";

    private FechaUtil() {}

    public static String formatearFecha(LocalDateTime fechaHora, String formato) {
        DateTimeFormatter fechaHoraFormato = DateTimeFormatter.ofPattern(formato);
        return fechaHora.format(fechaHoraFormato);
    }

    public static LocalDate obtenerFecha() {
        return LocalDate.now();
    }

    public static LocalDateTime obtenerFechaHora() {
        return LocalDateTime.now();
    }

}
