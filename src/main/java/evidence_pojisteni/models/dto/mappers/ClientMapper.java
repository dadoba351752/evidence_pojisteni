package evidence_pojisteni.models.dto.mappers;

import evidence_pojisteni.data.entities.ClientEntity;
import evidence_pojisteni.models.dto.ClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Mapovací rozhraní pro převod mezi objekty ClientEntity a ClientDTO využívající MapStruct.
 */
@Mapper(componentModel = "spring")
public interface ClientMapper {

    /**
     * Převede objekt typu ClientDTO na entitu ClientEntity.
     *
     * @param source DTO objekt obsahující vstupní data
     * @return nová instance ClientEntity odpovídající datům z DTO
     */
    ClientEntity toEntity(ClientDTO source);

    /**
     * Převede entitu ClientEntity na objekt typu ClientDTO.
     *
     * @param source entita získaná z databáze
     * @return DTO objekt odpovídající entitě
     */
    ClientDTO toDTO(ClientEntity source);

    /**
     * Zkopíruje hodnoty z objektu source do cílového DTO target.
     *
     * @param source zdroj dat (např. z formuláře)
     * @param target cílový DTO, který bude aktualizován
     */
    void updateClientDTO(ClientDTO source, @MappingTarget ClientDTO target);

    /**
     * Zkopíruje hodnoty z objektu source do cílové entity target.
     *
     * @param source DTO s aktualizovanými daty
     * @param target entita, která má být upravena
     */
    void updateClientEntity(ClientDTO source, @MappingTarget ClientEntity target);
}
