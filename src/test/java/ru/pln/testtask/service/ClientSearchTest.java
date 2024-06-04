package ru.pln.testtask.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import ru.pln.testtask.dto.BankDTO;
import ru.pln.testtask.dto.ClientDTO;
import ru.pln.testtask.search.BankSearch;
import ru.pln.testtask.search.ClientSearch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@ActiveProfiles("test")
class ClientSearchTest {
    @Autowired
    private ClientSearchService service;

    @Test
    void whenCorrectSearch_thenReturnResult(){
        ClientSearch clientSearch = new ClientSearch();
        Page<ClientDTO> result = service.search(clientSearch);
        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result.getTotalElements()).isEqualTo(3L)
        );
    }
    @Test
    void whenCorrectSearchOneElement_thenReturnResult(){
        ClientSearch clientSearch = new ClientSearch("P", "", "");
        Page<ClientDTO> result = service.search(clientSearch);
        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result.getTotalElements()).isEqualTo(1L)
        );
    }
}
