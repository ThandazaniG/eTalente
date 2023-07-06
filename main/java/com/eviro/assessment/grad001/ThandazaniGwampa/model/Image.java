package com.eviro.assessment.grad001.ThandazaniGwampa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "images")
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Image {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name="cmrSeq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_ID", value = "SEQUENCE")}
    )
    @GeneratedValue(generator = "sequence_ID")
    @Column(name = "id", nullable = false)
    private Long id;
    @NonNull @Column(nullable = false) @NotEmpty(message = "image format is required")
    @Size(min = 9, message = "image format must contain 9 or more characters")
    private String imageFormat;

    @NonNull @NotEmpty(message = "image data is required")
    @Size(min = 64, message = "base64 image is required")
    @Lob
    @Column(length = 3072)
    private byte[] imageData;
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name="name",referencedColumnName = "name"),
            @JoinColumn(name = "surname", referencedColumnName = "surname")
    })
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
