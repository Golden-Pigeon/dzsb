package com.xdu.dzsb.controller;

import com.xdu.dzsb.common.enums.ResultEnum;
import com.xdu.dzsb.model.dto.ResultDTO;
import com.xdu.dzsb.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/app/exercise")
public class AppExerciseController {
    @Autowired
    private ExerciseService exerciseService;

    @PostMapping("/upload")
    @ResponseBody
    public ResultDTO upload(@RequestParam Integer type,
                            @RequestParam String sport,
                            @RequestParam MultipartFile video) {
        if(video.isEmpty()){
            return new ResultDTO(ResultEnum.UPLOAD_FAILED);
        }
        String fileName;
        String filePath;
        fileName = video.getOriginalFilename();
        filePath = "D:/Temp/mv/";
        File file = new File(filePath + fileName);
        try {
            if(!file.getParentFile().exists())
                file.getParentFile().mkdir();
            video.transferTo(file);
            //LOGGER.info("上传成功");
            return new ResultDTO(ResultEnum.SUCCESS);
        } catch (IOException e) {
            // LOGGER.error(e.toString(), e);
            return new ResultDTO(ResultEnum.UPLOAD_FAILED);
        }

    }

}
