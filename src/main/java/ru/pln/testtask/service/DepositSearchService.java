package ru.pln.testtask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pln.testtask.dto.BankDTO;
import ru.pln.testtask.dto.DepositDTO;
import ru.pln.testtask.models.Bank;
import ru.pln.testtask.models.Deposit;
import ru.pln.testtask.repository.BankRepository;
import ru.pln.testtask.repository.DepositRepository;
import ru.pln.testtask.search.BankSearch;
import ru.pln.testtask.search.DepositSearch;

import java.util.stream.Collectors;

import static ru.pln.testtask.util.PageRequestUtil.createPageRequest;

@Service
@RequiredArgsConstructor
public class DepositSearchService implements SearchService<DepositDTO, DepositSearch> {
    private final DepositRepository depositRepository;
    @Override
    @Transactional(readOnly = true)
    public Page<DepositDTO> search(DepositSearch obj) {
        PageRequest pageRequest = createPageRequest(obj);
        Page<Deposit> pageDeposit = depositRepository.searchPage(obj.getClientId(), obj.getClientName(), obj.getBankId(),
                obj.getBankName(), obj.getPeriodInMonths(), pageRequest);
        return new PageImpl<>(
                pageDeposit.getContent().stream().map(deposit -> new DepositDTO(deposit.getId(),
                        deposit.getClient().getId(), deposit.getClient().getName(), deposit.getBank().getId(),
                        deposit.getBank().getName(), deposit.getOpenDate(), deposit.getPeriodInMonths())).collect(Collectors.toList()),
                pageRequest,
                pageDeposit.getTotalElements()
        );
    }
}
