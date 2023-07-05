package com.eviro.assessment.grad001.ThandazaniGwampa.service;

import com.eviro.assessment.grad001.ThandazaniGwampa.dto.AccountEvent;
import com.eviro.assessment.grad001.ThandazaniGwampa.model.AccountProfile;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;

@AllArgsConstructor
public class AccountEventListener {
    private final AccountProfileService accountProfileService;
    @EventListener
    void saveAccount(AccountEvent accountEvent){
        var account = new AccountProfile(accountEvent.name(), accountEvent.surname());
        accountProfileService.save(account);
    }
}
