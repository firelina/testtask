package ru.pln.testtask.search;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Data
@EqualsAndHashCode(callSuper=false)
public class DepositSearch extends BaseSearch {
    private Long clientId;
    private String clientName;
    private Long bankId;
    private String bankName;
    private Integer periodInMonths;
}
