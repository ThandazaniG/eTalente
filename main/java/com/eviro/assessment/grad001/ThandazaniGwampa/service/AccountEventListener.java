package com.eviro.assessment.grad001.ThandazaniGwampa.service;

import com.eviro.assessment.grad001.ThandazaniGwampa.dto.AccountEvent;
import com.eviro.assessment.grad001.ThandazaniGwampa.model.AccountProfile;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Component
public class AccountEventListener {
    private final AccountProfileService accountProfileService;
    @EventListener(ImageService.class)
    void saveAccount(AccountEvent accountEvent){
        System.out.println("222222222222222"+accountEvent.name()+accountEvent.surname());
        var account = new AccountProfile(accountEvent.name(), accountEvent.surname());
        accountProfileService.save(account);
    }
}
