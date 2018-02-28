package ru.akm.testtask.animals.jetty.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author akm
 */
@NoArgsConstructor
@ToString
@Entity
@Table(name = "animal_types")
public class AnimalType implements Serializable {
    @Getter
    @Setter
    @Id
    @GeneratedValue
    Integer id;
    
    @Getter
    @Setter
    String caption;
}
