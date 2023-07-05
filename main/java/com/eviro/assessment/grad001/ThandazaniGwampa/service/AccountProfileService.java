package com.eviro.assessment.grad001.ThandazaniGwampa.service;

import com.eviro.assessment.grad001.ThandazaniGwampa.exception.AccountProfileNofFound;
import com.eviro.assessment.grad001.ThandazaniGwampa.exception.DuplicateAccountProfile;
import com.eviro.assessment.grad001.ThandazaniGwampa.model.AccountProfile;
import com.eviro.assessment.grad001.ThandazaniGwampa.repository.AccountProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@AllArgsConstructor
public class AccountProfileService  implements  Account{
    private final AccountProfileRepository repository;

    /**
     * Save the account
     * @throws  NullPointerException is the account is null
     * @throws  DuplicateAccountProfile if the account(name and surname) already exist
     * @param account  to be saved
     *
     */
    protected void save(@Validated AccountProfile account) {
        if(account == null)throw new NullPointerException("Account is invalid");
        if(repository.existsById(account.getId())) throw new DuplicateAccountProfile("Account already exists");
        repository.save(account);
    }


    /**
     * @param name  of the  accountProfile to find
     * @param surname of the  accountProfile to find
     * @throws  AccountProfileNofFound if the account of the name and surname is null
     * @return AccountProfile  of given names
     */
    @Override
    public AccountProfile findAccountProfile(String name, String surname) {
        var account = repository.findAccountProfile(name, surname);
        if(account==null) throw  new AccountProfileNofFound("AccountProfile is not found");
        return account;
    }
}
