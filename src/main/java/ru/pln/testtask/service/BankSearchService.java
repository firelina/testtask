package ru.pln.testtask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pln.testtask.dto.BankDTO;
import ru.pln.testtask.models.Bank;
import ru.pln.testtask.repository.BankRepository;
import ru.pln.testtask.search.BankSearch;

import java.util.stream.Collectors;

import static ru.pln.testtask.util.PageRequestUtil.createPageRequest;

@Service
@RequiredArgsConstructor
public class BankSearchService implements SearchService<BankDTO, BankSearch>{
    private final BankRepository bankRepository;
    @Override
    @Transactional(readOnly = true)
    public Page<BankDTO> search(BankSearch obj) {
        PageRequest pageRequest = createPageRequest(obj);
        Page<Bank> pageBank = bankRepository.searchPage(obj.getName(), obj.getBik(), pageRequest);
        return new PageImpl<>(
                pageBank.getContent().stream().map(bank -> new BankDTO(bank.getId(),bank.getName(),bank.getBik())).collect(Collectors.toList()),
                pageRequest,
                pageBank.getTotalElements()
                );
    }
}
