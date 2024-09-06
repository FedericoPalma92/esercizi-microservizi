package it.its.springdemo.restcontrollers;

import it.its.springdemo.services.StorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private final StorageService storageService;
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 10MB

    private List<String> files = new ArrayList<>();

    public HomeController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("files", files);
        return "home";
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) throws Exception {
        String message;
        String path = null;

        if (file.getSize() > MAX_FILE_SIZE) {
            message = "File size exceeds the maximum limit of 10MB";
        } else {
            try {
                path = storageService.store(file);
                if (path != null && path != "Failed to store empty file") {
                    message = "File uploaded successfully: " + path;
                } else {
                    message = "Failed to store empty file";
                }

            } catch (IOException e) {
                message = "Failed to upload file: " + e.getMessage();
            }
        }
        model.addAttribute("message", message);
        model.addAttribute("files", storageService.loadAll());
        return "home";
    }
}