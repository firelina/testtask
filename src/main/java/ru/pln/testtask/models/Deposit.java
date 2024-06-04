package ru.pln.testtask.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Deposit {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deposit_seq")
    @SequenceGenerator(name = "deposit_seq", initialValue = 2000, allocationSize = 1)
    private Long id;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "fk_client"))
    private Client client;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "bank_id", foreignKey = @ForeignKey(name = "fk_bank"))
    private Bank bank;
    @Column
    private Date openDate;
    @Column
    private Integer periodInMonths;
}
