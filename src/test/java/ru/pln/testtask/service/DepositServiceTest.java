package ru.pln.testtask.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.pln.testtask.dto.BankDTO;
import ru.pln.testtask.dto.ClientDTO;
import ru.pln.testtask.dto.DepositDTO;
import ru.pln.testtask.exceprion.ApiException;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.pln.testtask.exceprion.Message.NOT_FOUND;

@SpringBootTest
@ActiveProfiles("test")
class DepositServiceTest {
    @Autowired
    private DepositService service;
    @Autowired
    private  ClientService clientService;
    @Autowired
    private  BankService bankService;
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
                            new DepositDTO(WRONG_ID, 0L, "", 0L, "", new Date(), 8)));
                    assertThat(exception.getMessage()).isEqualTo(NOT_FOUND);
                }
        );
    }
    @Test
    @Transactional
    void whenCorrectSave_thenReturnResult(){
        ClientDTO clientDTO = clientService.getById(1L);
        BankDTO bankDTO = bankService.getById(1L);
        DepositDTO depositDTO = new DepositDTO(null, clientDTO.getId(), clientDTO.getName(), bankDTO.getId(),
                bankDTO.getName(), new Date(), 8);
        DepositDTO result = service.save(depositDTO);
        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result).isEqualTo(depositDTO)
        );
    }
    @Test
    @Transactional
    void whenCorrectId_thenReturnResult(){
        ClientDTO clientDTO = clientService.getById(1L);
        BankDTO bankDTO = bankService.getById(1L);
        DepositDTO depositDTO = new DepositDTO(null, clientDTO.getId(), clientDTO.getName(), bankDTO.getId(),
                bankDTO.getName(), new Date(), 8);
        DepositDTO deposit = service.save(depositDTO);
        DepositDTO result = service.getById(deposit.getId());
        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result).isEqualTo(deposit)
        );
    }
    @Test
    @Transactional
    void whenCorrectId_thenUpdateReturnResult(){
        ClientDTO clientDTO = clientService.getById(1L);
        BankDTO bankDTO = bankService.getById(1L);
        DepositDTO depositDTO = new DepositDTO(null, clientDTO.getId(), clientDTO.getName(), bankDTO.getId(),
                bankDTO.getName(), new Date(), 8);
        DepositDTO deposit = service.save(depositDTO);
        deposit.setPeriodInMonths(10);
        DepositDTO result = service.update(deposit.getId(), deposit);
        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result).isEqualTo(result)
        );
    }
    @Test
    @Transactional
    void whenCorrectId_thenDelete(){
        ClientDTO clientDTO = clientService.getById(1L);
        BankDTO bankDTO = bankService.getById(1L);
        DepositDTO depositDTO = new DepositDTO(null, clientDTO.getId(), clientDTO.getName(), bankDTO.getId(),
                bankDTO.getName(), new Date(), 8);
        DepositDTO deposit = service.save(depositDTO);
        service.deleteById(deposit.getId());
        assertAll(
                () -> {
                    ApiException exception =  assertThrows(ApiException.class, () ->service.getById(WRONG_ID));
                    assertThat(exception.getMessage()).isEqualTo(NOT_FOUND);
                }
        );
    }
}