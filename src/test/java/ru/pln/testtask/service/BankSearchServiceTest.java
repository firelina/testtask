package ru.pln.testtask.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import ru.pln.testtask.dto.BankDTO;
import ru.pln.testtask.exceprion.ApiException;
import ru.pln.testtask.search.BankSearch;
import ru.pln.testtask.search.BaseSearch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static ru.pln.testtask.exceprion.Message.NOT_FOUND;

@SpringBootTest
@ActiveProfiles("test")
class BankSearchServiceTest {
    @Autowired
    private BankSearchService service;

    @Test
    void whenCorrectSearch_thenReturnResult(){
        BankSearch bankSearch = new BankSearch();
        Page<BankDTO> result = service.search(bankSearch);
        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result.getTotalElements()).isEqualTo(3L)
        );
    }
    @Test
    void whenCorrectSearchOneElement_thenReturnResult(){
        BankSearch bankSearch = new BankSearch("b", "4");
        Page<BankDTO> result = service.search(bankSearch);
        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result.getTotalElements()).isEqualTo(1L)
        );
    }
}