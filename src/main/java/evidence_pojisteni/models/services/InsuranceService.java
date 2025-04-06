package evidence_pojisteni.models.services;

import evidence_pojisteni.models.dto.InsuranceDTO;

import java.util.List;

public interface InsuranceService {

    void create(InsuranceDTO insurance);
    List<InsuranceDTO> getByClientId(Long clientId);
    InsuranceDTO getById(long insuranceId);
    void delete(long insurance);
    void edit(InsuranceDTO insurance);

}
