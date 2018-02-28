package ru.akm.testtask.animals.jetty.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
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
@Table(name = "animals", 
        uniqueConstraints = {@UniqueConstraint(columnNames = "CAPTION")}
)
public class Animal implements Serializable {
    @Getter
    @Setter
    @Id
    @GeneratedValue
    Long id;
    
    @Getter
    @Setter
    @NotNull
    String caption;
    
    @Getter
    @Setter
    Date birthDate;
    
    @Getter
    @Setter
    Sex sex;
    
    @Getter
    @Setter
    Long type;
    
    @Getter
    @Setter
    @NotNull
    Long creator;
}
