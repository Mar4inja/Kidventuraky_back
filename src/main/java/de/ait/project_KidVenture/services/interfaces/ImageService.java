package de.ait.project_KidVenture.services.interfaces;

import de.ait.project_KidVenture.services.extraClassPhoto.FileRequest;
import de.ait.project_KidVenture.services.extraClassPhoto.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    ImageResponse uploadPhoto(MultipartFile file) throws IOException;

    ImageResponse getPhoto(String filename) throws IOException;

    void deletePhoto(FileRequest request);
}
