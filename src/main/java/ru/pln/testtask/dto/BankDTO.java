package ru.pln.testtask.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class BankDTO extends BaseDTO{
    private String name;
    private String bik;

    public BankDTO(Long id, String name, String bik) {
        super(id);
        this.name = name;
        this.bik = bik;
    }
}
