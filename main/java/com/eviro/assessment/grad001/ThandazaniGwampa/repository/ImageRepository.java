package com.eviro.assessment.grad001.ThandazaniGwampa.repository;
import com.eviro.assessment.grad001.ThandazaniGwampa.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query(value = "SELECT img FROM Image AS img JOIN fetch img.company")
    Set<Image> findAllImages();
    @Query(value = "SELECT img FROM Image AS img JOIN fetch img.company AS c WHERE c.name=:name AND c.surname=:surname")
    Image findImageByNameAndSurname(String name, String surname);
}
