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

/**
 * Implementace rozhraní ClientService.
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    /**
     * Vytvoří nového pojištěnce a uloží ho do databáze.
     *
     * @param client objekt s daty nového pojištěnce
     */
    @Override
    public void create(ClientDTO client) {
        ClientEntity newClient = clientMapper.toEntity(client);
        clientRepository.save(newClient);
    }

    /**
     * Vrátí seznam všech pojištěnců ve formátu DTO.
     *
     * @return seznam pojištěnců
     */

    @Override
    public List<ClientDTO> getAll() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false)
                .map(i -> clientMapper.toDTO(i))
                .toList();
    }

    /**
     * Vyhledá pojištěnce podle ID a vrátí jeho DTO reprezentaci.
     * Pokud pojištěnec neexistuje, vyvolá výjimku.
     *
     * @param clientId ID pojištěnce
     * @return objekt pojištěnce jako ClientDTO
     */
    @Override
    public ClientDTO getById(long clientId) {
        ClientEntity fetchedClient = clientRepository
                .findById(clientId)
                .orElseThrow();
        return clientMapper.toDTO(fetchedClient);
    }

    /**
     * Aktualizuje údaje o existujícím pojištěnci na základě předaného DTO.
     * Pokud pojištěnec neexistuje, vyvolá výjimku.
     *
     * @param client DTO s aktualizovanými daty
     */
    @Override
    public void edit(ClientDTO client) {
        ClientEntity fetchedClient = getClientOrThrow(client.getClientId());

        clientMapper.updateClientEntity(client, fetchedClient);
        clientRepository.save(fetchedClient);
    }

    /**
     * Vrátí entitu pojištěnce podle ID nebo vyvolá výjimku, pokud neexistuje.
     *
     * @param clientId ID pojištěnce
     * @return entita pojištěnce
     */
    private ClientEntity getClientOrThrow(long clientId) {
        return clientRepository
                .findById(clientId)
                .orElseThrow(ClientNotFoundException::new);
    }

    /**
     * Smaže pojištěnce podle jeho ID.
     * Pokud pojištěnec neexistuje, vyvolá výjimku.
     *
     * @param clientId ID pojištěnce, který má být odstraněn
     */
    @Override
    public void delete(long clientId) {
        ClientEntity fetchedEntity = getClientOrThrow(clientId);
        clientRepository.delete(fetchedEntity);
    }
}
