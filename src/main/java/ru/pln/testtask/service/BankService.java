package ru.pln.testtask.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pln.testtask.dto.BankDTO;
import ru.pln.testtask.exceprion.ApiException;
import ru.pln.testtask.models.Bank;
import ru.pln.testtask.repository.BankRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ru.pln.testtask.exceprion.Message.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class BankService implements CrudService<BankDTO>{
    private final BankRepository repository;
    @Override
    @Transactional
    public BankDTO save(BankDTO bank) {
        Bank entity = new Bank();
//        entity.setId(bank.getId());
        entity.setName(bank.getName());
        entity.setBik(bank.getBik());
        Bank saved = repository.save(entity);
        return new BankDTO(saved.getId(),saved.getName(),saved.getBik());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BankDTO> findAll() {
        return repository.findAll().stream().map(bank -> new BankDTO(bank.getId(),bank.getName(),bank.getBik())).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BankDTO getById(Long id) {
        Bank bank = repository.findById(id).orElseThrow(() -> new ApiException(NOT_FOUND));
        return new BankDTO(bank.getId(),bank.getName(),bank.getBik());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public BankDTO update(Long id, BankDTO bank) {
        Bank persist = repository.findById(id).orElseThrow(() -> new ApiException(NOT_FOUND));
        persist.setName(bank.getName());
        persist.setBik(bank.getBik());
        return new BankDTO(persist.getId(),persist.getName(),persist.getBik());
    }
}
