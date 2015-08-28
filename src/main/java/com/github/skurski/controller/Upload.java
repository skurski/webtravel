package com.github.skurski.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

public class Upload {

    String[] run(String name, MultipartFile file) {
        if(file.isEmpty()) return null;

        try {
            byte[] bytes = file.getBytes();
 
            // Creating the directory to store file
            String rootPath = System.getProperty("user.dir");
            File dir = new File(rootPath + File.separator + "workspace" + File.separator + "webtravel" + File.separator +
            		"src" + File.separator + "main" + File.separator + "webapp" + File.separator + "resources" + File.separator + "upload");
            if (!dir.exists())
                dir.mkdirs();
            
            String[] fileArr = new String[2];
            fileArr[0] = new StringBuilder(hashName(name)).append(".jpg").toString();
            fileArr[1] = name;
 
            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath()
                    + File.separator + fileArr[0]);
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
 
            return fileArr;
        } catch (Exception e) {
            System.out.println("You failed to upload " + name + " => " + e.getMessage());
            return null;
        }
    }
    
    private String hashName(String name) {
    	String hash = "qazxswedcvfrtgbnhyujmkilop1234567890";
    	int min = (int) Math.floor((Math.random() * 10) + 1); 
    	int max = (int) Math.floor((Math.random() * 20) + 10); 
    	return hash.substring(min, max);
    }
}
