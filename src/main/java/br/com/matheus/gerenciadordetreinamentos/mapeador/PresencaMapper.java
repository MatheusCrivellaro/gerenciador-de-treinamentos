package br.com.matheus.gerenciadordetreinamentos.mapeador;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Presenca;
import br.com.matheus.gerenciadordetreinamentos.dto.PresencaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PresencaMapper {

    PresencaMapper INSTANCE = Mappers.getMapper( PresencaMapper.class );

    @Mapping(source = "id", target = "key")
    PresencaDTO toDTO(Presenca entity);

    @Mapping(source = "key", target = "id")
    Presenca toEntity(PresencaDTO dto);

    @Mapping(source = "id", target = "key")
    List<PresencaDTO> toDTOList(List<Presenca> entity);

    @Mapping(source = "key", target = "id")
    List<Presenca> toEntityList(List<PresencaDTO> dto);
}
