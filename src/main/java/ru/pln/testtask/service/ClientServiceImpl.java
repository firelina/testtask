package ru.pln.testtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.pln.testtask.dto.BankDTO;
import ru.pln.testtask.dto.ClientDTO;
import ru.pln.testtask.exceprion.ApiException;

import java.util.List;
import java.util.Objects;

import static ru.pln.testtask.exceprion.Message.*;

@Service
public class ClientServiceImpl implements CrudService<ClientDTO> {
    private final CrudService<ClientDTO> clientService;
    @Autowired
    public ClientServiceImpl(@Qualifier("clientService") CrudService<ClientDTO> clientService) {
        this.clientService = clientService;
    }

    @Override
    public ClientDTO save(ClientDTO clientDTO) {
        if (Objects.isNull(clientDTO)) {
            throw new ApiException(IS_NULL);
        }
        if (Objects.isNull(clientDTO.getName()) || clientDTO.getName().trim().length() == 0) {
            throw new ApiException(INVALID_FIELD);
        }
        if (!checkOrgLoyalForm(clientDTO.getOrgLoyalForm())){
            throw new ApiException(INVALID_ORG_FORM);
        }
        return this.clientService.save(clientDTO);
    }

    @Override
    public List<ClientDTO> findAll() {
        return this.clientService.findAll();
    }

    @Override
    public ClientDTO getById(Long i) {
        if (Objects.isNull(i) || i == 0L) {
            throw new ApiException(INVALID_ID);
        }
        return this.clientService.getById(i);
    }

    @Override
    public void deleteById(Long i) {
        if (Objects.isNull(i) || i == 0L) {
            throw new ApiException(INVALID_ID);
        }
        clientService.deleteById(i);
    }

    @Override
    public ClientDTO update(Long i, ClientDTO clientDTO) {
        if (Objects.isNull(i) || i == 0L) {
            throw new ApiException(INVALID_ID);
        }
        if (Objects.isNull(clientDTO)) {
            throw new ApiException(IS_NULL);
        }
        if (!checkOrgLoyalForm(clientDTO.getOrgLoyalForm())){
            throw new ApiException(INVALID_ORG_FORM);
        }
        return this.clientService.update(i, clientDTO);
    }

    public boolean checkOrgLoyalForm(String form) {
        return form.equals("ИП") || form.equals("ООО") || form.equals("АО") || form.equals("самозанятый");
    }
}
