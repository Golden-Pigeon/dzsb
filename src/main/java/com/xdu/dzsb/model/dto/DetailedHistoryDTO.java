package com.xdu.dzsb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class DetailedHistoryDTO {
    private Integer id;
    private String openid;
    private String videoPath;
    private String action;
    private Date date;
    private String errorImg;
    private String correctImg;
    private String error;
    private String advice;
}
