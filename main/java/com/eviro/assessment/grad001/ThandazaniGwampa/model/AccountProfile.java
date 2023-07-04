package com.eviro.assessment.grad001.ThandazaniGwampa.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "account_profiles")
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class AccountProfile {
      @EmbeddedId
      private AccountProfileID id;
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
