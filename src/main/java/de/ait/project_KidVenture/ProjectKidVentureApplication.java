package de.ait.project_KidVenture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProjectKidVentureApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectKidVentureApplication.class, args);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode("Neskaidrs31##");
        System.out.println("Šifrētā parole: " + encodedPassword);
    }

}
