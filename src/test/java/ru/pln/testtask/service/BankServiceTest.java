package ru.pln.testtask.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.pln.testtask.dto.BankDTO;
import ru.pln.testtask.exceprion.ApiException;
import ru.pln.testtask.search.BankSearch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.pln.testtask.exceprion.Message.NOT_FOUND;

@SpringBootTest
@ActiveProfiles("test")
class BankServiceTest {
    @Autowired
    private BankService service;
    private static final Long WRONG_ID = 10000L;
    @Test
    void whenWrongId_thenGetByIdThrowsException(){
        assertAll(
                () -> {
                    ApiException exception =  assertThrows(ApiException.class, () ->service.getById(WRONG_ID));
                    assertThat(exception.getMessage()).isEqualTo(NOT_FOUND);
                }
        );
    }
    @Test
    void whenWrongId_thenUpdateByIdThrowsException(){
        assertAll(
                () -> {
                    ApiException exception =  assertThrows(ApiException.class, () ->service.update(WRONG_ID,
                            new BankDTO(WRONG_ID, "", "")));
                    assertThat(exception.getMessage()).isEqualTo(NOT_FOUND);
                }
        );
    }
    @Test
    @Transactional
    void whenCorrectSave_thenReturnResult(){
        BankDTO bank = new BankDTO(null, "bank1", "1234");
        BankDTO result = service.save(bank);
        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result).isEqualTo(bank)
        );
    }
    @Test
    @Transactional
    void whenCorrectId_thenReturnResult(){
        BankDTO bank = new BankDTO(null, "bank1", "1234");
        BankDTO dto = service.save(bank);
        BankDTO result = service.getById(dto.getId());
        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result).isEqualTo(bank)
        );
    }
    @Test
    @Transactional
    void whenCorrectId_thenUpdateReturnResult(){
        BankDTO bank = new BankDTO(null, "bank1", "1234");
        BankDTO dto = service.save(bank);
        bank.setName("abc");
        bank.setBik("4321");
        BankDTO result = service.update(dto.getId(), bank);
        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result).isEqualTo(bank)
        );
    }
    @Test
    @Transactional
    void whenCorrectId_thenDelete(){
        BankDTO bank = new BankDTO(null, "bank1", "1234");
        BankDTO dto = service.save(bank);
        service.deleteById(dto.getId());
        assertAll(
                () -> {
                    ApiException exception =  assertThrows(ApiException.class, () ->service.getById(WRONG_ID));
                    assertThat(exception.getMessage()).isEqualTo(NOT_FOUND);
                }
        );
    }
}
