package de.ait.project_KidVenture.repository;

import de.ait.project_KidVenture.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String> {

    Image findByFilename(String fileName);
    void deleteByFilename(String filename);
}
