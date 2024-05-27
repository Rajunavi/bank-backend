package com.bufferzero.bank.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "image-data")
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long imageId;
    private String imageName;
    private String imageDescription;

    public ImageModel() {
    }

    public ImageModel(String imageName, String imageDescription) {
        this.imageName = imageName;
        this.imageDescription = imageDescription;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }
}
