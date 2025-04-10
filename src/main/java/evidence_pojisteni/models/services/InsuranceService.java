package evidence_pojisteni.models.services;

import evidence_pojisteni.models.dto.InsuranceDTO;

import java.util.List;

/**
 * Rozhraní definující operace pro práci s pojištěními.
 */
public interface InsuranceService {

    /**
     * Vytvoří nové pojištění a uloží ho do databáze.
     *
     * @param insurance objekt s daty nového pojištění
     */
    void create(InsuranceDTO insurance);

    /**
     * Vrátí seznam všech pojištění přiřazených danému pojištěnci.
     *
     * @param clientId ID pojištěnce
     * @return seznam pojištění ve formátu DTO
     */
    List<InsuranceDTO> getByClientId(Long clientId);

    /**
     * Vyhledá pojištění podle jeho ID.
     *
     * @param insuranceId ID pojištění
     * @return objekt pojištění jako DTO
     */
    InsuranceDTO getById(long insuranceId);

    /**
     * Smaže pojištění podle jeho ID.
     *
     * @param insurance ID pojištění, které má být odstraněno
     */
    void delete(long insurance);

    /**
     * Aktualizuje údaje o existujícím pojištění na základě předaného DTO.
     *
     * @param insurance objekt s aktualizovanými daty pojištění
     */
    void edit(InsuranceDTO insurance);

}
