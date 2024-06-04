package ru.pln.testtask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.pln.testtask.models.Client;
import ru.pln.testtask.models.Deposit;

import java.util.Date;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {
    @Query("select d from Deposit d join d.client c join d.bank b where " +
            "(:clientId is null or :clientId = c.id) and (:bankId is null or :bankId = b.id) and " +
            "(:clientName is null or lower(c.name) like lower(concat(:clientName, '%'))) and " +
            "(:bankName is null or lower(b.name) like lower(concat(:bankName, '%'))) and " +
            "(:periodInMonths is null or :periodInMonths = d.periodInMonths)")
    Page<Deposit> searchPage(@Param("clientId") Long clientId,
                             @Param("clientName") String clientName,
                             @Param("bankId") Long bankId,
                             @Param("bankName") String bankName,
                             @Param("periodInMonths") Integer periodInMonths,
                             Pageable pageable);
}
