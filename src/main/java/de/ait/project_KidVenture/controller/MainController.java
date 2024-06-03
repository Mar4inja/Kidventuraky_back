package de.ait.project_KidVenture.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Controller
public class MainController {



    @GetMapping("/front")
    public String home(Model model) throws IOException {
        // Atvērt HTML failu no resursiem
        Resource resource = new ClassPathResource("static/front.html");
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        StringBuilder htmlContent = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            htmlContent.append(line);
        }
        reader.close();

        // Iespraudīt HTML saturu modelī
        model.addAttribute("htmlContent", htmlContent.toString());

        return "front";
    }
}
