package com.xdu.dzsb.controller;

import com.alibaba.fastjson.JSONObject;
import com.xdu.dzsb.common.enums.ResultEnum;
import com.xdu.dzsb.common.utils.FileUtil;
import com.xdu.dzsb.common.utils.HttpClientUtil;
import com.xdu.dzsb.model.dto.ResultDTO;
import com.xdu.dzsb.service.ExerciseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/app/exercise")
public class AppExerciseController {
    @Autowired
    private ExerciseService exerciseService;

    @PostMapping("/upload")
    @ResponseBody
    public ResultDTO upload(@RequestParam MultipartFile video) {
        if(video.isEmpty()){
            return new ResultDTO(ResultEnum.UPLOAD_FAILED);
        }
        String fileName;
        String filePath;
        fileName = video.getOriginalFilename();
        filePath = "/tmp/dzsb/mv/";
        File file = new File(filePath + fileName);
        try {
            if(!file.getParentFile().exists())
                file.getParentFile().mkdir();
            video.transferTo(file);
            //LOGGER.info("上传成功");
            ResultDTO result = new ResultDTO(ResultEnum.SUCCESS);
            Map<String, Object> data = new HashMap<>();
            data.put("file_name", fileName);
            result.setData(data);
            return result;
        } catch (IOException e) {
            // LOGGER.error(e.toString(), e);
            return new ResultDTO(ResultEnum.UPLOAD_FAILED);
        }

    }

    @PostMapping("/get_result")
    @ResponseBody
    public ResultDTO playVideo(@RequestParam String filename, @RequestParam String action){
        File file = new File("/mnt/dzsb/mv/" + filename);
        if(!file.exists()){
            return new ResultDTO(ResultEnum.FILE_NOT_EXIST);
        }
        MultipartFile multipartFile = FileUtil.fileToMultipartFile(file);
        Map<String, Object> postParams = new HashMap<>();
        postParams.put("video", multipartFile);
        postParams.put("action", action);
        String postJson = JSONObject.toJSONString(postParams);
        String responseJson = HttpClientUtil.doPost1("XXX", postJson);
        if(StringUtils.isEmpty(responseJson))
            return new ResultDTO(ResultEnum.REQUEST_FAILED);

        JSONObject responseJsonObject = JSONObject.parseObject(responseJson);
        if(responseJsonObject.getIntValue("code") != 200){
            ResultDTO result = new ResultDTO(ResultEnum.CAL_FAILED);
            result.setData(responseJson);
            return result;
        }
        else{
            ResultDTO result = new ResultDTO(ResultEnum.SUCCESS);
            Map<String, String> advice = new HashMap<>();
            advice.put("error", responseJsonObject.getString("error_res"));
            advice.put("advice", responseJsonObject.getString("advice_res"));
            result.setData(advice);
            return result;
        }

    }

}
