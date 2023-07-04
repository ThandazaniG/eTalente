package com.eviro.assessment.grad001.ThandazaniGwampa.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class AccountProfileID implements Serializable {
        private String name;
        private String surname;
}
