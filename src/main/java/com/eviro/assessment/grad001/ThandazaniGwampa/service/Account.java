package com.eviro.assessment.grad001.ThandazaniGwampa.service;
import com.eviro.assessment.grad001.ThandazaniGwampa.model.AccountProfile;
import org.springframework.stereotype.Service;

@Service
public interface Account {
    /**
     *
     * @param name is the name of the Account profile
     * @param surname is the surname of the Account profile
     */
    void saveAccount(String name, String surname);

    /**
     *
     * @param name is the name of the Account profile
     * @param surname is the name of the Account profile
     * @return the account profile
     */
    AccountProfile findAccountProfile(String name, String surname);

}
