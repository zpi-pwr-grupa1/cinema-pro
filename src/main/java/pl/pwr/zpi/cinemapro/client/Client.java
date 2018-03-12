package pl.pwr.zpi.cinemapro.client;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Client {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String username;

    @Column
    private String password;

}