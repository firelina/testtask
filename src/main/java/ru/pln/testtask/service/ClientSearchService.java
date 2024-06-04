package ru.pln.testtask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pln.testtask.dto.BankDTO;
import ru.pln.testtask.dto.ClientDTO;
import ru.pln.testtask.models.Bank;
import ru.pln.testtask.models.Client;
import ru.pln.testtask.models.OrgLoyalForm;
import ru.pln.testtask.repository.BankRepository;
import ru.pln.testtask.repository.ClientRepository;
import ru.pln.testtask.search.ClientSearch;
import ru.pln.testtask.util.FormConvertor;

import java.util.stream.Collectors;

import static ru.pln.testtask.util.PageRequestUtil.createPageRequest;

@Service
@RequiredArgsConstructor
public class ClientSearchService implements SearchService<ClientDTO, ClientSearch>{
    private final ClientRepository clientRepository;
    @Transactional(readOnly = true)
    @Override
    public Page<ClientDTO> search(ClientSearch obj) {
        PageRequest pageRequest = createPageRequest(obj);
        Page<Client> pageClient = clientRepository.searchPage(obj.getName(), obj.getShortName(), obj.getAddress(), pageRequest);
        return new PageImpl<>(
                pageClient.getContent().stream().map(client -> new ClientDTO(client.getId(),client.getName(),
                        client.getShortName(), client.getAddress(),
                        FormConvertor.formToString(client.getOrgLoyalForm()))).collect(Collectors.toList()),
                pageRequest,
                pageClient.getTotalElements()
        );
    }
}
