package com.xdu.dzsb.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author: wjy
 * @date: 2021/3/1
 * @description:
 */
@Data
@NoArgsConstructor
@Entity
@Table
public class User {
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Integer id;
    private String openid;
    private String name;
    private Integer sex;
    private Date birthday;
    private Integer bust;
    private Integer waistline;
    private Integer hipline;
    private String avatar;
    private String signature;
    private Integer height;
    private Integer weight;
    private Integer count;
    private Integer energy;
    @Column(name = "total_time", insertable = false)
    private Integer totalTime;
    @Column(name = "total_exercise_days", insertable = false)
    private Integer totalExerciseDays;
    @Column(name = "continue_exercise_days", insertable = false)
    private Integer continueExerciseDays;
    
    public User(String openid, String name, String avatar) {
        this.openid = openid;
        this.name = name;
        this.avatar = avatar;
    }
}
