package evidence_pojisteni.data.repositories;

import evidence_pojisteni.data.entities.InsuranceEntity;
import org.springframework.data.repository.CrudRepository;

public interface InsuranceRepository extends CrudRepository<InsuranceEntity, Long> {
}
