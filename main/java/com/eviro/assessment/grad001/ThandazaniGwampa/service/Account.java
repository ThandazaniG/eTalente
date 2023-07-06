package com.eviro.assessment.grad001.ThandazaniGwampa.service;

import com.eviro.assessment.grad001.ThandazaniGwampa.model.AccountProfile;
import org.springframework.stereotype.Service;

@Service
public interface Account {
    void saveAccount(String name, String surname);
    AccountProfile findAccountProfile(String name, String surname);

}
