package com.xdu.dzsb.model.dto;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class BriefHistoryDTO {
    private Integer id;
    private String openid;
    private String videoPath;
    private String action;
    private Date date;
}
