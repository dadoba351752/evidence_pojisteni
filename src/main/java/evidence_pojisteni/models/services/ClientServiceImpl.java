package evidence_pojisteni.models.services;

import evidence_pojisteni.data.entities.ClientEntity;
import evidence_pojisteni.models.dto.ClientDTO;
import evidence_pojisteni.models.dto.mappers.ClientMapper;
import evidence_pojisteni.models.exceptions.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import evidence_pojisteni.data.repositories.ClientRepository;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public void create(ClientDTO client) {
        ClientEntity newClient = clientMapper.toEntity(client);
        clientRepository.save(newClient);
    }

    @Override
    public List<ClientDTO> getAll() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false)
                .map(i -> clientMapper.toDTO(i))
                .toList();
    }

    @Override
    public ClientDTO getById(long clientId) {
        ClientEntity fetchedClient = clientRepository
                .findById(clientId)
                .orElseThrow();
        return clientMapper.toDTO(fetchedClient);
    }

    @Override
    public void edit(ClientDTO client) {
        ClientEntity fetchedClient = getClientOrThrow(client.getClientId());

        clientMapper.updateClientEntity(client, fetchedClient);
        clientRepository.save(fetchedClient);
    }

    private ClientEntity getClientOrThrow(long clientId) {
        return clientRepository
                .findById(clientId)
                .orElseThrow(ClientNotFoundException::new);
    }

    @Override
    public void delete(long clientId) {
        ClientEntity fetchedEntity = getClientOrThrow(clientId);
        clientRepository.delete(fetchedEntity);
    }
}
