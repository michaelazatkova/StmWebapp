package com.projects.savethemeeting.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Michaela on 22.02.2016.
 */
@RestController
@RequestMapping("/api")
public class RestApiController {

    @RequestMapping("/skuska")
    public @ResponseBody String skuska(@RequestParam(value = "param") String param) {
        return param+ "-checked";
    }

    @RequestMapping(value="/upload", method= RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            String name = new File(".").getAbsolutePath()+File.separator+file.getName();
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload file because the file was empty.";
        }
    }
}
