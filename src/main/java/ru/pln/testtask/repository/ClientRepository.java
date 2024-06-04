package ru.pln.testtask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.pln.testtask.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("select c from Client c where (:name is null or lower(c.name) like lower(concat(:name,'%'))) and " +
            "((:shortName is null or lower(c.shortName) like lower(concat(:shortName,'%')))) and " +
            "((:address is null or lower(c.address) like lower(concat(:address,'%'))))")
    Page<Client> searchPage(@Param("name") String name, @Param("shortName") String shortName,
                            @Param("address") String address, Pageable pageable);
}
