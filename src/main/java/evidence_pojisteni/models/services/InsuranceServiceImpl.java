package evidence_pojisteni.models.services;

import evidence_pojisteni.data.entities.ClientEntity;
import evidence_pojisteni.data.entities.InsuranceEntity;
import evidence_pojisteni.data.repositories.ClientRepository;
import evidence_pojisteni.data.repositories.InsuranceRepository;
import evidence_pojisteni.models.dto.InsuranceDTO;
import evidence_pojisteni.models.dto.mappers.InsuranceMapper;
import evidence_pojisteni.models.exceptions.InsuranceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Implementace rozhraní InsuranceService.
 */
@Service
public class InsuranceServiceImpl implements InsuranceService{

    @Autowired
    private InsuranceMapper insuranceMapper;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private ClientRepository clientRepository;

    /**
     * Vytvoří nové pojištění a přiřadí ho konkrétnímu pojištěnci.
     * Automaticky nastaví aktuální datum jako datum sjednání.
     *
     * @param insurance DTO objekt s daty nového pojištění
     */
    @Override
    public void create(InsuranceDTO insurance) {
        ClientEntity client = clientRepository.findById(insurance.getClientId())
                .orElseThrow(() -> new RuntimeException("Client with ID " + insurance.getClientId() + " not found"));

        InsuranceEntity entity = insuranceMapper.toEntity(insurance);
        entity.setClient(client);
        entity.setInsuranceDate(LocalDate.now());

        insuranceRepository.save(entity);
    }

    /**
     * Vrátí seznam všech pojištění, která jsou přiřazena danému pojištěnci.
     *
     * @param clientId ID pojištěnce
     * @return seznam pojištění ve formátu DTO
     */
    @Override
    public List<InsuranceDTO> getByClientId(Long clientId) {
        return StreamSupport.stream(insuranceRepository.findAll().spliterator(), false)
                .filter(i -> i.getClient().getClientId() == clientId)
                .map(insuranceMapper::toDTO)
                .toList();
    }

    /**
     * Vyhledá pojištění podle jeho ID nebo vyvolá výjimku, pokud neexistuje.
     *
     * @param insuranceId ID pojištění
     * @return entita pojištění
     */
    private InsuranceEntity getInsuranceOrThrow(long insuranceId) {
        return insuranceRepository
                .findById(insuranceId)
                .orElseThrow(InsuranceNotFoundException::new);
    }

    /**
     * Smaže pojištění podle jeho ID.
     * Pokud pojištění neexistuje, vyvolá výjimku.
     *
     * @param insuranceId ID pojištění, které má být odstraněno
     */
    @Override
    public void delete(long insuranceId) {
        InsuranceEntity entity = getInsuranceOrThrow(insuranceId);
        insuranceRepository.delete(entity);
    }

    /**
     * Aktualizuje údaje o existujícím pojištění na základě dat z DTO.
     *
     * @param insurance DTO objekt s upravenými daty pojištění
     */
    @Override
    public void edit(InsuranceDTO insurance) {
        InsuranceEntity fetchedInsurance = getInsuranceOrThrow(insurance.getInsuranceId());

        insuranceMapper.updateInsuranceEntity(insurance, fetchedInsurance);
        insuranceRepository.save(fetchedInsurance);
    }

    /**
     * Vyhledá pojištění podle ID a převede ho do DTO.
     * Pokud pojištění neexistuje, vyvolá výjimku.
     *
     * @param insuranceId ID pojištění
     * @return DTO reprezentace pojištění
     */
    @Override
    public InsuranceDTO getById(long insuranceId) {
        InsuranceEntity entity = insuranceRepository
                .findById(insuranceId)
                .orElseThrow(() -> new InsuranceNotFoundException("Pojištění nenalezeno"));

        return insuranceMapper.toDTO(entity);
    }
}
