package com.xdu.dzsb.model.entity;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    private String openid;
    private String videoPath;
    private String action;
    private Date date;
    private String errorImg;
    private String correctImg;
    private String error;
    private String advice;

    public History(String openid, String videoPath, String action, Date date, String errorImg, String correctImg, String error, String advice) {
        this.openid = openid;
        this.videoPath = videoPath;
        this.action = action;
        this.date = date;
        this.errorImg = errorImg;
        this.correctImg = correctImg;
        this.error = error;
        this.advice = advice;
    }
}
