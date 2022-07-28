package me.jasonsinclair.musicarchivebackend;

import me.jasonsinclair.musicarchivebackend.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class MusicArchiveBackendApplication {
//        extends SpringBootServletInitializer {

//    @Autowired
//    private static StorageService storageService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MusicArchiveBackendApplication .class, args);
//        storageService.getSongFileNames();
        StorageService storageService = context.getBean(StorageService.class);

        System.out.println(storageService.getSongFileNames());
    }

}
