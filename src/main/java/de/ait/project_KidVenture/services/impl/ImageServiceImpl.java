package de.ait.project_KidVenture.services.impl;

import de.ait.project_KidVenture.entity.Image;
import de.ait.project_KidVenture.repository.ImageRepository;
import de.ait.project_KidVenture.services.extraClassPhoto.FileRequest;
import de.ait.project_KidVenture.services.extraClassPhoto.ImageResponse;
import de.ait.project_KidVenture.services.interfaces.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public ImageResponse uploadPhoto(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        byte[] content = file.getBytes();
        Image image = new Image(fileName, content);
        imageRepository.save(image);
        String encodedImage = Base64.getEncoder().encodeToString(content);
        String photoUrl = "/api/users/get-photo/" + fileName;
        return new ImageResponse(photoUrl, encodedImage);
    }

    @Override
    public ImageResponse getPhoto(String filename) throws IOException {
        Image image = imageRepository.findByFilename(filename);
        if (image == null) {
            throw new IOException("Image not found");
        }
        String base64Image = Base64.getEncoder().encodeToString(image.getContent());
        String photoUrl = "/api/users/get-photo/" + filename;
        return new ImageResponse(photoUrl, base64Image);
    }

    @Override
    public void deletePhoto(FileRequest request) {
        String fileName = request.getPhotoFilename();
        imageRepository.deleteById(fileName);
    }
}
