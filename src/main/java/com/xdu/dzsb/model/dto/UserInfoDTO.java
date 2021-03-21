package com.xdu.dzsb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.util.List;

/**
 * @author: wjy
 * @date: 2021/3/1
 * @description:
 */
@Data
@AllArgsConstructor
public class UserInfoDTO {
    
//    private Integer id;
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
//    private Integer totalTime;
//    private Integer totalExerciseDays;
//    private Integer continueExerciseDays;
    private String openid;
    private String nickname;
    private Integer sex;
    private Date birthday;
    private String avatarUrl;

    private Double height;
    private Double weight;
    //    @Column(name = "goal_days")
    private Integer goalDays;
    private Integer accomplishedDays;
    //    @Column(name = "total_exercise_days", insertable = false)
    private Integer totalExerciseDays;
    //    @Column(name = "continue_exercise_days", insertable = false)
    private Integer continueExerciseDays;
    private List<ExerciseInfoDTO> detail;
//    private List<BriefHistoryDTO> brief;
    private Date lastCheckDate;
}
