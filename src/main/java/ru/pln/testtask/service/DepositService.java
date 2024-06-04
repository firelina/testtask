package ru.pln.testtask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pln.testtask.dto.BankDTO;
import ru.pln.testtask.dto.DepositDTO;
import ru.pln.testtask.exceprion.ApiException;
import ru.pln.testtask.models.Bank;
import ru.pln.testtask.models.Client;
import ru.pln.testtask.models.Deposit;
import ru.pln.testtask.repository.BankRepository;
import ru.pln.testtask.repository.ClientRepository;
import ru.pln.testtask.repository.DepositRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static ru.pln.testtask.exceprion.Message.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class DepositService implements CrudService<DepositDTO> {
    private final DepositRepository repository;
    private final ClientRepository clientRepository;
    private final BankRepository bankRepository;
    @Override
    @Transactional
    public DepositDTO save(DepositDTO deposit) {
        Deposit entity = new Deposit();
//        entity.setId(deposit.getId());
        entity.setOpenDate(deposit.getOpenDate());
        entity.setPeriodInMonths(deposit.getPeriodInMonths());
        Bank bank = bankRepository.findById(deposit.getBankId()).orElseThrow(() -> new ApiException(NOT_FOUND));
        entity.setBank(bank);
        Client client = clientRepository.findById(deposit.getClientId()).orElseThrow(() -> new ApiException(NOT_FOUND));
        entity.setClient(client);
        Deposit saved = repository.save(entity);
        return new DepositDTO(saved.getId(), saved.getClient().getId(), saved.getClient().getName(),
                saved.getBank().getId(), saved.getBank().getName(), saved.getOpenDate(), saved.getPeriodInMonths());
    }
    @Override
    @Transactional(readOnly = true)
    public List<DepositDTO> findAll() {
        return repository.findAll().stream().map(deposit -> new DepositDTO(deposit.getId(), deposit.getClient().getId(),
                deposit.getClient().getName(), deposit.getBank().getId(),
                deposit.getBank().getName(), deposit.getOpenDate(), deposit.getPeriodInMonths())).collect(Collectors.toList());
    }
    @Override
    @Transactional(readOnly = true)
    public DepositDTO getById(Long id) {
        Deposit deposit = repository.findById(id).orElseThrow(() -> new ApiException(NOT_FOUND));
        return new DepositDTO(deposit.getId(), deposit.getClient().getId(),
                deposit.getClient().getName(), deposit.getBank().getId(),
                deposit.getBank().getName(), deposit.getOpenDate(), deposit.getPeriodInMonths());
    }
    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    @Override
    @Transactional
    public DepositDTO update(Long id, DepositDTO deposit) {
        Deposit persist = repository.findById(id).orElseThrow(() -> new ApiException(NOT_FOUND));
        Bank bank = bankRepository.findById(deposit.getBankId()).orElseThrow(() -> new ApiException(NOT_FOUND));
        persist.setBank(bank);
        Client client = clientRepository.findById(deposit.getClientId()).orElseThrow(() -> new ApiException(NOT_FOUND));
        persist.setClient(client);
        persist.setOpenDate(deposit.getOpenDate());
        persist.setPeriodInMonths(deposit.getPeriodInMonths());
        return new DepositDTO(persist.getId(), persist.getClient().getId(), persist.getClient().getName(),
                persist.getBank().getId(), persist.getBank().getName(), persist.getOpenDate(), persist.getPeriodInMonths());
    }
}
