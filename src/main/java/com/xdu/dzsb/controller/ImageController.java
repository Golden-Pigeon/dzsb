package com.xdu.dzsb.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@Slf4j
@RequestMapping("/img")
public class ImageController {

    @Resource
    private HttpServletResponse response;

    @GetMapping(value = "/getImage/{imageName}")
    @ApiOperation("获取图片-以ImageIO流形式写回")
    public void getImage(@PathVariable String imageName) throws IOException {
        OutputStream os = null;
        try {
//        读取图片
            BufferedImage image = ImageIO.read(new FileInputStream(new File("/tmp/dzsb/img/" + imageName)));//TODO:给出文件名
            response.setContentType("image/jpg");
            os = response.getOutputStream();

            if (image != null) {
                ImageIO.write(image, "jpg", os);
            }
        } catch (IOException e) {
            log.error("获取图片异常{}",e.getMessage());
        } finally {
            if (os != null) {
                os.flush();
                os.close();
            }
        }
    }

}
