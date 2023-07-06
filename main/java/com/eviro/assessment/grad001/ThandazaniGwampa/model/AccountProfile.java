package com.eviro.assessment.grad001.ThandazaniGwampa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


import java.util.Objects;

@Entity
@Table(name = "account_profiles")
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AccountProfile {
    @Id
    @GenericGenerator(name="cmrSeq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_ID", value = "SEQUENCE")}
    )
    @GeneratedValue(generator = "sequence_ID")
    @Column(name = "id", nullable = false)

     // @GeneratedValue(strategy = GenerationType.AUTO)
      private Long id;

      @NonNull @Column(nullable = false) @NotEmpty(message = "name is required")
      @Size(min = 1, message = "name must contain more than 0 characters")
      private String name;

      @NonNull @Column(nullable = false) @NotEmpty(message = "surname is required")
      @Size(min = 2, message = "surname must contain more than 0 characters")
      private String surname;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountProfile that = (AccountProfile) o;
        return Objects.equals(id, that.id) && name.equals(that.name) && surname.equals(that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }
}
