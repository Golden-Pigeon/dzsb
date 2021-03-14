package com.xdu.dzsb.controller;

import com.xdu.dzsb.common.enums.ResultEnum;
import com.xdu.dzsb.common.utils.NonStaticResourceHttpRequestHandler;
import com.xdu.dzsb.model.dto.ResultDTO;
import com.xdu.dzsb.service.ExerciseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: wjy
 * @date: 2021/3/1
 * @description:
 */
@RestController
@RequestMapping("/exercise")
public class ExerciseController {
    
    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;

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

    @GetMapping("/play_video")
    @ResponseBody
    public ResultDTO playVideo(HttpServletRequest request, HttpServletResponse response){
        try {
            String filename = request.getParameter("filename");
//            String sourcePath = Objects.requireNonNull(Objects.requireNonNull(ClassUtils.getDefaultClassLoader()).getResource("")).getPath().substring(1);
            String realPath = "/mnt/dzsb/mv/" + filename;



            Path filePath = Paths.get(realPath );
            if (Files.exists(filePath)) {
                String mimeType = Files.probeContentType(filePath);
                if (!StringUtils.isEmpty(mimeType)) {
                    response.setContentType(mimeType);
                }
                request.setAttribute(NonStaticResourceHttpRequestHandler.ATTR_FILE, filePath);
                nonStaticResourceHttpRequestHandler.handleRequest(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            }
        } catch (Exception e) {
            return new ResultDTO(ResultEnum.VIDEO_PLAY_EXCEPTION);
        }

        return new ResultDTO(ResultEnum.SUCCESS);
    }

}
