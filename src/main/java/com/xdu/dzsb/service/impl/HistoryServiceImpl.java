package com.xdu.dzsb.service.impl;

import com.xdu.dzsb.common.enums.ResultEnum;
import com.xdu.dzsb.model.dto.BriefHistoryDTO;
import com.xdu.dzsb.model.dto.ResultDTO;
import com.xdu.dzsb.model.entity.History;
import com.xdu.dzsb.repository.HistoryRepository;
import com.xdu.dzsb.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    HistoryRepository historyRepository;

    @Override
    public void saveHistory(String openid, String videoPath, String action, Date date, String errorImg, String correctImg, String error, String advice) {
        History history = new History(openid, videoPath, action, date, errorImg, correctImg, error, advice);
        historyRepository.save(history);
    }

    @Override
    public ResultDTO getDetailedHistory(Integer id) {
        Optional<History> history = historyRepository.findById(id);
        if(!history.isPresent())
            return new ResultDTO(ResultEnum.NO_HISTORY);
        ResultDTO result = new ResultDTO(ResultEnum.SUCCESS);
        result.setData(history);
        return result;
    }

    @Override
    public ResultDTO getBriefHistories(String openid) {

        List<BriefHistoryDTO> brief = new ArrayList<>();
        List<History> histories = historyRepository.findAllByOpenid(openid);
        for(History history : histories){
            brief.add(new BriefHistoryDTO(history.getId(), history.getOpenid(),
                    history.getVideoPath(), history.getAction(), history.getDate()));
        }
        ResultDTO result = new ResultDTO(ResultEnum.SUCCESS);
        result.setData(brief);
        return result;
    }

}
