package ru.pln.testtask.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import ru.pln.testtask.dto.BankDTO;
import ru.pln.testtask.dto.ClientDTO;
import ru.pln.testtask.exceprion.ApiException;
import ru.pln.testtask.models.OrgLoyalForm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.pln.testtask.exceprion.Message.NOT_FOUND;

@SpringBootTest
@ActiveProfiles("test")
class ClientServiceTest {
    @Autowired
    private ClientService service;
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
                            new ClientDTO(WRONG_ID, "", "", "", "ИП")));
                    assertThat(exception.getMessage()).isEqualTo(NOT_FOUND);
                }
        );
    }
    @Test
    void whenCorrectSave_thenReturnResult(){
        ClientDTO client = new ClientDTO(null, "Client1", "c", "Address", "ИП");
        ClientDTO result = service.save(client);
        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result).isEqualTo(client)
        );
    }
    @Test
    void whenCorrectId_thenReturnResult(){
        ClientDTO client = new ClientDTO(null, "Client1", "c", "Address", "ИП");
        ClientDTO dto = service.save(client);
        ClientDTO result = service.getById(dto.getId());
        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result).isEqualTo(client)
        );
    }
    @Test
    void whenCorrectId_thenUpdateReturnResult(){
        ClientDTO client = new ClientDTO(null, "Client1", "c", "Address", "ИП");
        ClientDTO dto = service.save(client);
        client.setName("abc");
        client.setShortName("a");
        client.setOrgLoyalForm("АО");
        ClientDTO result = service.update(dto.getId(), client);
        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result).isEqualTo(client)
        );
    }
    @Test
    void whenCorrectId_thenDelete(){
        ClientDTO client = new ClientDTO(null, "Client1", "c", "Address", "ИП");
        ClientDTO dto = service.save(client);
        service.deleteById(dto.getId());
        assertAll(
                () -> {
                    ApiException exception =  assertThrows(ApiException.class, () ->service.getById(WRONG_ID));
                    assertThat(exception.getMessage()).isEqualTo(NOT_FOUND);
                }
        );
    }
}
