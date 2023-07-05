package com.eviro.assessment.grad001.ThandazaniGwampa.service;

import com.eviro.assessment.grad001.ThandazaniGwampa.model.AccountProfile;
import org.springframework.stereotype.Service;

@Service
public interface Account {

    AccountProfile findAccountProfile(String name, String surname);
}
