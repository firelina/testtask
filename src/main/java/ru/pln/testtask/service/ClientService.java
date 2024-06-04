package ru.pln.testtask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pln.testtask.dto.ClientDTO;
import ru.pln.testtask.exceprion.ApiException;
import ru.pln.testtask.models.Client;
import ru.pln.testtask.models.OrgLoyalForm;
import ru.pln.testtask.repository.ClientRepository;
import ru.pln.testtask.util.FormConvertor;

import java.util.List;
import java.util.stream.Collectors;

import static ru.pln.testtask.exceprion.Message.NOT_FOUND;


@Service
@RequiredArgsConstructor
public class ClientService implements CrudService<ClientDTO> {
    private final ClientRepository repository;
    @Transactional
    @Override
    public ClientDTO save(ClientDTO client) {
        Client entity = new Client();
//        entity.setId(client.getId());
        entity.setName(client.getName());
        entity.setShortName(client.getShortName());
        entity.setAddress(client.getAddress());
        entity.setOrgLoyalForm(FormConvertor.stringToForm(client.getOrgLoyalForm()));
        Client saved = repository.save(entity);
        return new ClientDTO(saved.getId(), saved.getName(), saved.getShortName(), saved.getAddress(),
                FormConvertor.formToString(saved.getOrgLoyalForm()));
    }
    @Transactional
    @Override
    public List<ClientDTO> findAll() {
        return repository.findAll().stream().map(client -> new ClientDTO(client.getId(), client.getName(),client.getShortName(),client.getAddress(),
                FormConvertor.formToString(client.getOrgLoyalForm()))).collect(Collectors.toList());
    }
    @Transactional
    @Override
    public ClientDTO getById(Long id) {
        Client client = repository.findById(id).orElseThrow(() -> new ApiException(NOT_FOUND));
        return new ClientDTO(client.getId(), client.getName(),client.getShortName(),client.getAddress(),
                FormConvertor.formToString(client.getOrgLoyalForm()));
    }
    @Transactional
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    @Transactional
    @Override
    public ClientDTO update(Long id, ClientDTO client) {
        Client persist = repository.findById(id).orElseThrow(() -> new ApiException(NOT_FOUND));
        persist.setName(client.getName());
        persist.setAddress(client.getAddress());
        persist.setShortName(client.getShortName());
        persist.setOrgLoyalForm(FormConvertor.stringToForm(client.getOrgLoyalForm()));
        return new ClientDTO(persist.getId(), persist.getName(),persist.getShortName(), persist.getAddress(),
                FormConvertor.formToString(persist.getOrgLoyalForm()));
    }



}
