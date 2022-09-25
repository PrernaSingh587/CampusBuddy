package com.example.campusbuddy.controller;


import com.example.campusbuddy.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FirebaseController {

    @Autowired
    FileService fileService;

    @PostMapping("/profile/pic")
    public String upload(@RequestParam("file") MultipartFile multipartFile) {
        //logger.info("HIT -/upload | File Name : {}", multipartFile.getOriginalFilename());
        return fileService.upload(multipartFile);
    }

    @PostMapping("/profile/pic/{fileName}")
    public String download(@PathVariable String fileName) throws IOException {
        //logger.info("HIT -/download | File Name : {}", fileName);
        return fileService.download(fileName);
    }
}
