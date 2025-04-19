package com.example.penstore.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileService {
    public String saveFile(MultipartFile file, String kind) {
        String dir = System.getProperty("user.dir");
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        File destFile = new File(dir + "/static/images/"+ kind +"/" + fileName);
        destFile.getParentFile().mkdirs();
        try {
            file.transferTo(destFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "images/"+kind+"/"+fileName;
    }

}
