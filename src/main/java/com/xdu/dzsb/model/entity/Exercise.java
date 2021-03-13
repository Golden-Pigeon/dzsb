package com.xdu.dzsb.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author: wjy
 * @date: 2021/3/1
 * @description:
 */
@Data
@NoArgsConstructor
@Entity
@Table
public class Exercise {
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_id", insertable = false)
    private Integer userId;
    private Integer type;
    private Integer sport;
    private Integer count;
    private Integer time;
}
