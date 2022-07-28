package me.jasonsinclair.musicarchivebackend.controllers;


import me.jasonsinclair.musicarchivebackend.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
