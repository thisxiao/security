package com.xjx.security.web.controller;

import com.xjx.security.dto.FileInFo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * @Author XJX
 * @Date 2020/7/1 14:47
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/file")
@Api(value = "文件处理接口",tags = "文件上传下载")
public class FileController {

    private String folder = "E:\\project\\spring_security_demo\\sx-security-demo\\src\\main\\java\\com\\xjx\\security\\web\\controller";


    @PostMapping
    public FileInFo upload(MultipartFile file) throws IOException {
        log.info(file.getName());
        log.info(file.getOriginalFilename());
        log.info(file.getSize() + "");

        File localFile = new File(folder,new Date().getTime()+".txt");

        file.transferTo(localFile);

        return new FileInFo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try(InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
            OutputStream outputStream = response.getOutputStream()){
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition","attachment;filename=test.txt");

            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
        }
    }
}
