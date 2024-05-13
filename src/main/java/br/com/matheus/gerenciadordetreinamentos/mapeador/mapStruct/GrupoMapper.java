package br.com.matheus.gerenciadordetreinamentos.mapeador.mapStruct;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Grupo;
import br.com.matheus.gerenciadordetreinamentos.dto.GrupoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GrupoMapper {

    GrupoMapper INSTANCE = Mappers.getMapper( GrupoMapper.class );

    @Mapping(source = "id", target = "key")
    GrupoDTO toDTO(Grupo entity);

    @Mapping(source = "key", target = "id")
    Grupo toEntity(GrupoDTO dto);

    @Mapping(source = "id", target = "key")
    List<GrupoDTO> toDTOList(List<Grupo> entity);

    @Mapping(source = "key", target = "id")
    List<Grupo> toEntityList(List<GrupoDTO> dto);
}
