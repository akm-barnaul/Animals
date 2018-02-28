package ru.akm.testtask.animals.jetty.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "app_users")
public class User implements Serializable {

    public User(Long id, String userName, String userPwd) {
        this.id = id;
        this.userName = userName;
        this.userPwd = userPwd;
    }

    @Getter
    @Setter
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Getter
    @Setter
    @Column
    String userName;

    @Getter
    @Setter
    @Column
    String userPwd;
}
