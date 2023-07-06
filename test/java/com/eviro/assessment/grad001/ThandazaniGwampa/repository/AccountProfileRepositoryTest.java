package com.eviro.assessment.grad001.ThandazaniGwampa.repository;

import com.eviro.assessment.grad001.ThandazaniGwampa.model.AccountProfile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class AccountProfileRepositoryTest {
    @Autowired
    private AccountProfileRepository accountProfileRepository;
    private AccountProfile account1, account2;
    @BeforeEach
    void beforeEach(){
        account1 = new AccountProfile("Zani", "Health");
        account2 = new AccountProfile("Lunga", "Engineering");
        accountProfileRepository.saveAll(List.of(account1, account2));
    }
    @AfterEach
    void afterEach(){
        accountProfileRepository.deleteAll();
        account1 = account2 = null;
    }
    @ParameterizedTest
    @CsvSource(value = {"1, Zani, Health", "2, Lunga, Engineering"}, delimiter = ',')
    void testFindAllWhereAccountsExist(int index, String name, String surname){
        var account = accountProfileRepository.findAccountProfile(name, surname);
        if(index == 1){
            assertEquals(account1, account);
        }else{
            assertEquals(account2, account);
        }

    }@ParameterizedTest
    @CsvSource(value = {"1, zan, Health", "2, Lung, Engine"}, delimiter = ',')
    void testFindAllWhereAccountsDoesNotExist(int index, String name, String surname){
        var account = accountProfileRepository.findAccountProfile(name, surname);
        if(index == 1){
            assertNotEquals(account1, account);
        }else{
            assertNotEquals(account2, account);
        }

    }



}