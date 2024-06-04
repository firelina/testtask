package ru.pln.testtask.dto;

import lombok.*;
import org.springframework.context.annotation.Scope;
import ru.pln.testtask.models.OrgLoyalForm;


@Setter @Getter
@EqualsAndHashCode(callSuper = false)
public class ClientDTO extends BaseDTO{
    private String name;
    private String shortName;
    private String address;
    private String orgLoyalForm;

    public ClientDTO(Long id, String name, String shortName, String address, String orgLoyalForm) {
        super(id);
        this.name = name;
        this.shortName = shortName;
        this.address = address;
        this.orgLoyalForm = orgLoyalForm;
    }
}
