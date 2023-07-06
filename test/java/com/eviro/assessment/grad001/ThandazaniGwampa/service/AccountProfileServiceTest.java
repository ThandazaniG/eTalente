package com.eviro.assessment.grad001.ThandazaniGwampa.service;

import com.eviro.assessment.grad001.ThandazaniGwampa.exception.DuplicateAccountProfile;
import com.eviro.assessment.grad001.ThandazaniGwampa.model.AccountProfile;
import com.eviro.assessment.grad001.ThandazaniGwampa.repository.AccountProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountProfileServiceTest {
    @Mock
    private AccountProfileRepository accountProfileRepository;

    @InjectMocks
    private   AccountProfileService service;
    @BeforeEach
    void setUp() {
        service = new AccountProfileService(accountProfileRepository);
    }
    @Test
    void testNullAccount(){
        var output = assertThrows(NullPointerException.class, ()->service.save(null));
        assertEquals("Account is invalid", output.getMessage());
        verify(accountProfileRepository, times(0)).findAccountProfile(null, null);
        verify(accountProfileRepository, times(0)).save(any());
    }
    @ParameterizedTest
    @CsvSource(value = {"1, Zani, Health","2, Lunga, Engineering"},delimiter = ',')
    void testExitingAccount(Long id, String name, String surname){
        var account = new AccountProfile(id,name, surname);
        when(accountProfileRepository.findAccountProfile(account.getName(), account.getSurname())).thenReturn(account);
        var output = assertThrows(DuplicateAccountProfile.class, ()->service.save(account));
        assertEquals("Account already exists", output.getMessage());
        verify(accountProfileRepository, times(1)).findAccountProfile(account.getName(), account.getSurname());
        verify(accountProfileRepository, times(0)).save(account);
    }
    @ParameterizedTest
    @CsvSource(value = {"1, Zani, Health","2, Lunga, Engineering"},delimiter = ',')
    void testSuccessfulSavedAccount(String name, String surname){
        var account = new AccountProfile(name, surname);
        when(accountProfileRepository.findAccountProfile(account.getName(), account.getSurname())).thenReturn(null);
         service.save(account);
        verify(accountProfileRepository, times(1)).findAccountProfile(account.getName(), account.getSurname());
        verify(accountProfileRepository, times(1)).save(account);
    }


}