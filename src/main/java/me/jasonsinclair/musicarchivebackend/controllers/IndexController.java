package me.jasonsinclair.musicarchivebackend.controllers;


import me.jasonsinclair.musicarchivebackend.model.Song;
import me.jasonsinclair.musicarchivebackend.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
public class IndexController {

    private final StorageService storageService;

    @Autowired
    public IndexController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    public String getHomepage(Model model){
//        MVC - Model,View, Controller
//    Key value pair
        model.addAttribute("songFileNames", storageService.getSongFileNames());
        return "index";
    }

    @PostMapping
    public String handleFileUpload(@RequestParam("file")MultipartFile file) throws IOException {

        storageService.uploadSong(file);

        return "redirect:/";
    }



}
