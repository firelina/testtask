package ru.pln.testtask.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import ru.pln.testtask.models.Bank;
import ru.pln.testtask.models.Client;

import java.util.Date;

@Setter @Getter
@EqualsAndHashCode(callSuper = false)
public class DepositDTO extends BaseDTO {
    private Long clientId;
    private String clientName;
    private Long bankId;
    private String bankName;
    private Date openDate;
    private Integer periodInMonths;

    public DepositDTO(Long id, Long clientId, String clientName, Long bankId, String bankName,  Date openDate, Integer periodInMonths) {
        super(id);
        this.clientId = clientId;
        this.clientName = clientName;
        this.bankId = bankId;
        this.bankName = bankName;
        this.openDate = openDate;
        this.periodInMonths = periodInMonths;
    }
}
