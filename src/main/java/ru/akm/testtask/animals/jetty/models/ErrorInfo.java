package ru.akm.testtask.animals.jetty.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс сообщения об ошибке
 *
 * @author akm
 */
@NoArgsConstructor
@Data

public class ErrorInfo {
    Long errorNumber;
    String errorMessage;
    String exceptionMessage;

    public ErrorInfo(Long errorNumber, String errorMessage, String exceptionMessage) {
        this.errorNumber = errorNumber;
        this.errorMessage = errorMessage;
        this.exceptionMessage = exceptionMessage;
    }        
    
    String errorTS = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
}
