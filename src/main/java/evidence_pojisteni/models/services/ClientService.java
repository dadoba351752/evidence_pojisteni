package evidence_pojisteni.models.services;

import evidence_pojisteni.models.dto.ClientDTO;

import java.util.List;

public interface ClientService {

    void create(ClientDTO client);

    List<ClientDTO> getAll();

    ClientDTO getById(long clientId);

    void edit(ClientDTO client);

    void delete(long clientId);
}
