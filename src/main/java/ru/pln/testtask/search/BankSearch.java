package ru.pln.testtask.search;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class BankSearch extends BaseSearch{
    private String name;
    private String bik;
}
