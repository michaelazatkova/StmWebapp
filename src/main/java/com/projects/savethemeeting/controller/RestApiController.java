package com.projects.savethemeeting.controller;

import com.projects.savethemeeting.dao.MeetingDao;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MeetingDao meetingDao;

    @RequestMapping("/skuska")
    public @ResponseBody String skuska(@RequestParam(value = "param") String param) {
        return param+ "-checked";
    }

    @RequestMapping(value="/upload", method= RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            String name = System.getProperty("user.home") +File.separator + file.getOriginalFilename();
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                stream.close();
                System.out.println("You successfully uploaded " + name + "!");
                return "OK";
            } catch (Exception e) {
                System.out.println("You failed to upload " + name + " => " + e.getMessage());
                return "FAILED";
            }
        } else {
            System.out.println("You failed to upload file because the file was empty.");
            return "FAILED";
        }
    }

    @RequestMapping("/test")
    public String createTestData() {
        meetingDao.storeData();

        return "redirect:/";
    }
}
