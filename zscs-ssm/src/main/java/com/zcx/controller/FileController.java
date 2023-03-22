package com.zcx.controller;

import java.io.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * 文件上传接口
     *
     * @param file 待上传的文件
     * @return 上传结果
     * @throws IOException IO异常
     */
    @PostMapping("/upload")
    public Return upload(@RequestParam("file") MultipartFile file) throws IOException {
        // 判断上传文件是否为空
        if (!file.isEmpty()) {
            // 获取上传文件的原始文件名
            String fileName = file.getOriginalFilename();
            // 指定上传文件保存的目录路径
            String filePath = "D:/file/";
            // 创建目标文件对象
            File dest = new File(filePath + fileName);
            // 将上传文件保存到目标文件中
            file.transferTo(dest);
            return Return.success(fileName);
        } else {
            return Return.error("上传失败");
        }
    }

    /**
     * 文件下载接口
     *
     * @param filename 待下载的文件名
     * @return 文件下载结果
     * @throws IOException IO异常
     */
    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam("filename") String filename) throws IOException {
        // 指定下载文件所在的目录路径
        String filePath = "D:/file/";
        // 创建FileSystemResource对象，用于读取下载文件的内容
        FileSystemResource file = new FileSystemResource(filePath + filename);
        // 创建HttpHeaders对象，用于设置相应头信息
        HttpHeaders headers = new HttpHeaders();
        // 设置缓存控制，禁止浏览器缓存下载文件
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        // 设置Content-Disposition，指定文件名，用于告诉浏览器如何处理响应体的内容
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
        // 设置Pragma，禁止浏览器缓存下载文件
        headers.add("Pragma", "no-cache");
        // 设置Expires，禁止浏览器缓存下载文件
        headers.add("Expires", "0");
        // 构建ResponseEntity对象，设置响应状态码、响应头信息、响应体等信息
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }
}
