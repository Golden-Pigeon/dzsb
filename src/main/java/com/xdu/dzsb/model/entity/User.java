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
//@Data
//@NoArgsConstructor
//@Entity
//@Table
//public class User {
//
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Id
//    @Column(name = "id")
//    private Integer id;
//    private String openid;
//    private String name;
//    private Integer sex;
//    private Date birthday;
//    private Integer bust;
//    private Integer waistline;
//    private Integer hipline;
//    private String avatar;
//    private String signature;
//    private Integer height;
//    private Integer weight;
//    private Integer count;
//    private Integer energy;
//    @Column(name = "total_time", insertable = false)
//    private Integer totalTime;
//    @Column(name = "total_exercise_days", insertable = false)
//    private Integer totalExerciseDays;
//    @Column(name = "continue_exercise_days", insertable = false)
//    private Integer continueExerciseDays;
//
//    public User(String openid, String name, String avatar) {
//        this.openid = openid;
//        this.name = name;
//        this.avatar = avatar;
//    }
//}
@Data
@NoArgsConstructor
@Entity
@Table
public class User {

    @Id
    @Column(name = "openid")
    private String openid;
    private String nickname;
    private Integer sex;
    private Date birthday;
    private String avatarUrl;
    private Double height;
    private Double weight;
//    @Column(name = "goal_days")

    private Integer goalDays = 0;
    private Integer accomplishedDays = 0;
//    @Column(name = "total_exercise_days", insertable = false)
    private Integer totalExerciseDays = 0;
//    @Column(name = "continue_exercise_days", insertable = false)
    private Integer continueExerciseDays = 0;
    private Date lastCheckDate;
    public User(String openid) {
        this.openid = openid;
    }
}
