package ru.akm.testtask.animals.jetty.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author akm
 */
@AllArgsConstructor
public enum Sex {
    MALE("Мужской"),
    FEMALE("Женский");
    
    @Getter
    String caption;
}
