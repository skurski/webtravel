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

@Controller
public class UploadController {

    @RequestMapping(value="/uploadFile", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file){
        
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
 
                // Creating the directory to store file
                String rootPath = System.getProperty("user.dir");
                File dir = new File(rootPath + File.separator + "workspace" + File.separator + "webtravel" + File.separator +
                		"src" + File.separator + "main" + File.separator + "webapp" + File.separator + "resources" + File.separator + "upload");
                if (!dir.exists())
                    dir.mkdirs();
 
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
 
                return "You successfully uploaded file=" + name;
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name
                    + " because the file was empty.";
        }
    }
}
