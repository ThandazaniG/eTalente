package com.eviro.assessment.grad001.ThandazaniGwampa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "images")
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Image {
    @Id private Long id;
    @NonNull @Column(nullable = false) @NotEmpty(message = "image format is required")
    @Size(min = 9, message = "image format must contain 9 or more characters")
    private String imageFormat;

    @NonNull @NotEmpty(message = "image data is required")
    @Size(min = 64, message = "base64 image is required")
    @Lob
    @Column(length = 3072)
    private byte[] imageData;
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private AccountProfile company;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(id, image.id) && imageFormat.equals(image.imageFormat) && Arrays.equals(imageData, image.imageData);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, imageFormat);
        result = 31 * result + Arrays.hashCode(imageData);
        return result;
    }
}