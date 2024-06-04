package ru.pln.testtask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.pln.testtask.models.Bank;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    @Query("select b from Bank b where (:name is null or lower(b.name) like lower(concat(:name, '%'))) and " +
            "((:bik is null or lower(b.bik) like lower(concat(:bik, '%'))))")
    List<Bank> search(@Param("name") String name, @Param("bik") String bik);

    @Query("select b from Bank b where (:name is null or lower(b.name) like lower(concat(:name, '%'))) and " +
            "((:bik is null or lower(b.bik) like lower(concat(:bik, '%'))))")
    Page<Bank> searchPage(@Param("name") String name, @Param("bik") String bik, Pageable pageable);
}
