package cn.e3mall.controller;

import cn.e3mall.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by GMY on 2017/9/24.
 */

@Controller
public class PictureController {

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    @RequestMapping(value="/pic/upload", produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf8")
    @ResponseBody
    public String uploadFile(MultipartFile uploadFile) {

        String originalFilename = uploadFile.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String name = UUID.randomUUID().toString();

        try {
            uploadFile.transferTo(new File("/Users/GMY/Desktop/upload/apache-tomcat-9.0.0.M21/webapps/ROOT/upload/" + name + "." + extName));
            String url = IMAGE_SERVER_URL + "upload/" + name + "." + extName;
            System.out.println(url);
            Map result = new HashMap<>();
            result.put("error", 0);
            result.put("url", url);
            return JsonUtils.objectToJson(result);

        } catch (IOException e) {
            e.printStackTrace();
            Map result = new HashMap<>();
            result.put("error", 1);
            result.put("message", "图片上传失败");
            return JsonUtils.objectToJson(result);
        }
    }

}
