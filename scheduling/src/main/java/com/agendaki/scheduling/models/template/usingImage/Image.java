package com.agendaki.scheduling.models.template.usingImage;

import com.agendaki.scheduling.services.template.ImageService;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String uuidImage;
    @ManyToOne
    private ImageToExhibition imageToExhibition;

    public Image(String imageInBase64, ImageToExhibition imageToExhibition) {
//        final ImageService imageService = new ImageService();
//        String uuidCreated = UUID.randomUUID().toString().replace("-", "");
//        String uuidWithExtension = imageService.saveImageIntoArchive(imageInBase64, uuidCreated);
        this.uuidImage = imageInBase64;
        this.imageToExhibition = imageToExhibition;
    }

    public Image() {

    }

    public Long getId() {
        return id;
    }

    public String getUuidImage() {
        return uuidImage;
    }
}
