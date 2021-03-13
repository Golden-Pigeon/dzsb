package com.xdu.dzsb.model.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table
public class AppUser {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Integer id;

    private String userName;

    private String password;

    public AppUser(String userName, String password) {
        this.password = password;
        this.userName = userName;
    }
}
