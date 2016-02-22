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
    public @ResponseBody Boolean handleFileUpload(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            String name = System.getProperty("user.home") +File.separator + file.getOriginalFilename();
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                stream.close();
                System.out.println("You successfully uploaded " + name + "!");
                return true;
            } catch (Exception e) {
                System.out.println("You failed to upload " + name + " => " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("You failed to upload file because the file was empty.");
            return false;
        }
    }
}
