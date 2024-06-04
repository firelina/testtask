package ru.pln.testtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.pln.testtask.dto.ClientDTO;
import ru.pln.testtask.dto.DepositDTO;
import ru.pln.testtask.exceprion.ApiException;

import java.util.List;
import java.util.Objects;

import static ru.pln.testtask.exceprion.Message.*;

@Service
public class DepositServiceImpl implements CrudService<DepositDTO> {
    private final CrudService<DepositDTO> depositService;
    @Autowired
    public DepositServiceImpl(@Qualifier("depositService") CrudService<DepositDTO> depositService) {
        this.depositService = depositService;
    }

    @Override
    public DepositDTO save(DepositDTO depositDTO) {
        if (Objects.isNull(depositDTO)) {
            throw new ApiException(IS_NULL);
        }
        if (Objects.isNull(depositDTO.getOpenDate())) {
            throw new ApiException(INVALID_FIELD);
        }
        return this.depositService.save(depositDTO);
    }

    @Override
    public List<DepositDTO> findAll() {
        return this.depositService.findAll();
    }

    @Override
    public DepositDTO getById(Long i) {
        if (Objects.isNull(i) || i == 0L) {
            throw new ApiException(INVALID_ID);
        }
        return this.depositService.getById(i);
    }

    @Override
    public void deleteById(Long i) {
        if (Objects.isNull(i) || i == 0L) {
            throw new ApiException(INVALID_ID);
        }
        depositService.deleteById(i);
    }

    @Override
    public DepositDTO update(Long i, DepositDTO depositDTO) {
        if (Objects.isNull(i) || i == 0L) {
            throw new ApiException(INVALID_ID);
        }
        if (Objects.isNull(depositDTO)) {
            throw new ApiException(IS_NULL);
        }
        return this.depositService.update(i, depositDTO);
    }
}
