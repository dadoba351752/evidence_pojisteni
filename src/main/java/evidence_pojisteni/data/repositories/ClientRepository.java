package evidence_pojisteni.data.repositories;

import evidence_pojisteni.data.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<ClientEntity, Long> {
}
