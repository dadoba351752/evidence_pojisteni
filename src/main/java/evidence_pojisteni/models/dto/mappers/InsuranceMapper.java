package evidence_pojisteni.models.dto.mappers;

import evidence_pojisteni.data.entities.ClientEntity;
import evidence_pojisteni.data.entities.InsuranceEntity;
import evidence_pojisteni.models.dto.ClientDTO;
import evidence_pojisteni.models.dto.InsuranceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InsuranceMapper {
    InsuranceEntity toEntity(InsuranceDTO source);
    InsuranceDTO toDTO(InsuranceEntity source);

    void updateInsuranceEntity(InsuranceDTO source, @MappingTarget InsuranceEntity target);
    void updateInsuranceDTO(InsuranceDTO source, @MappingTarget InsuranceDTO target);
}
