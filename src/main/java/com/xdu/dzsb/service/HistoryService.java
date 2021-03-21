package com.xdu.dzsb.service;

import com.xdu.dzsb.model.dto.ResultDTO;

import java.sql.Date;

public interface HistoryService {
    void saveHistory(String openid, String videoPath, String action, Date date, String errorImg, String correctImg, String error, String advice);

    ResultDTO getDetailedHistory(Integer id);

    ResultDTO getBriefHistories(String openid);
}
