package ru.pln.testtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.pln.testtask.dto.BankDTO;
import ru.pln.testtask.dto.BaseDTO;
import ru.pln.testtask.exceprion.ApiException;

import java.util.List;
import java.util.Objects;

import static ru.pln.testtask.exceprion.Message.*;

@Service
public class BankServiceImpl implements CrudService<BankDTO> {
    private final CrudService<BankDTO> bankService;
    @Autowired
    public BankServiceImpl(@Qualifier("bankService") CrudService<BankDTO> bankService) {
        this.bankService = bankService;
    }

    @Override
    public BankDTO save(BankDTO bankDTO) {
        if (Objects.isNull(bankDTO)) {
            throw new ApiException(IS_NULL);
        }
        if (Objects.isNull(bankDTO.getName()) || bankDTO.getName().trim().length() == 0) {
            throw new ApiException(INVALID_FIELD);
        }
        return this.bankService.save(bankDTO);
    }

    @Override
    public List<BankDTO> findAll() {
        return this.bankService.findAll();
    }

    @Override
    public BankDTO getById(Long i) {
        if (Objects.isNull(i) || i == 0L) {
            throw new ApiException(INVALID_ID);
        }
        return this.bankService.getById(i);
    }

    @Override
    public void deleteById(Long i) {
        if (Objects.isNull(i) || i == 0L) {
            throw new ApiException(INVALID_ID);
        }
        bankService.deleteById(i);
    }

    @Override
    public BankDTO update(Long i, BankDTO bankDTO) {
        if (Objects.isNull(i) || i == 0L) {
            throw new ApiException(INVALID_ID);
        }
        if (Objects.isNull(bankDTO)) {
            throw new ApiException(IS_NULL);
        }
        return this.bankService.update(i, bankDTO);
    }
}
