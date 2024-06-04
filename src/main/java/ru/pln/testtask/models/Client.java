package ru.pln.testtask.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
@RequiredArgsConstructor
public class Client {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    @SequenceGenerator(name = "client_seq", initialValue = 2000, allocationSize = 1)
    private Long id;
    @Column
    private String name;
    @Column(name = "short_name")
    private String shortName;
    @Column
    private String address;
    @Column(name = "loyal_form")
    private OrgLoyalForm orgLoyalForm;


}


