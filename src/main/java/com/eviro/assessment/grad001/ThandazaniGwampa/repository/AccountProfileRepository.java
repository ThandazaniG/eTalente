package com.eviro.assessment.grad001.ThandazaniGwampa.repository;

import com.eviro.assessment.grad001.ThandazaniGwampa.model.AccountProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountProfileRepository extends JpaRepository<AccountProfile, Long> {
    @Query(value = "SELECT ap FROM AccountProfile AS ap WHERE ap.name=:name AND ap.surname=:surname")
    AccountProfile findAccountProfile(String name, String surname);
}
