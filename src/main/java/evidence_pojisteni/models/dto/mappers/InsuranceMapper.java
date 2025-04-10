package evidence_pojisteni.models.dto.mappers;

import evidence_pojisteni.data.entities.ClientEntity;
import evidence_pojisteni.data.entities.InsuranceEntity;
import evidence_pojisteni.models.dto.ClientDTO;
import evidence_pojisteni.models.dto.InsuranceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Mapovací rozhraní pro převod mezi objekty InsuranceEntity a InsuranceDTO využívající knihovnu MapStruct.
 */
@Mapper(componentModel = "spring")
public interface InsuranceMapper {

    /**
     * Převede objekt typu InsuranceDTO na entitu InsuranceEntity.
     *
     * @param source DTO objekt obsahující vstupní data
     * @return nová instance InsuranceEntity odpovídající datům z DTO
     */
    InsuranceEntity toEntity(InsuranceDTO source);

    /**
     * Převede entitu InsuranceEntity na objekt typu InsuranceDTO.
     *
     * @param source entita získaná z databáze
     * @return DTO objekt odpovídající entitě
     */
    InsuranceDTO toDTO(InsuranceEntity source);

    /**
     * Zkopíruje hodnoty z objektu source do cílové entity target.
     *
     * @param source DTO s aktualizovanými daty
     * @param target entita, která má být upravena
     */
    void updateInsuranceEntity(InsuranceDTO source, @MappingTarget InsuranceEntity target);

    /**
     * Zkopíruje hodnoty z objektu source do cílového DTO target.
     *
     * @param source entita s daty
     * @param target DTO, který má být aktualizován
     */
    void updateInsuranceDTO(InsuranceDTO source, @MappingTarget InsuranceDTO target);
}
