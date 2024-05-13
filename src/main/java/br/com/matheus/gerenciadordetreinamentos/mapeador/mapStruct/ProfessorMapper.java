package br.com.matheus.gerenciadordetreinamentos.mapeador.mapStruct;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Professor;
import br.com.matheus.gerenciadordetreinamentos.dto.ProfessorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProfessorMapper {

    ProfessorMapper INSTANCE = Mappers.getMapper( ProfessorMapper.class );

    @Mapping(source = "id", target = "key")
    ProfessorDTO toDTO(Professor entity);

    @Mapping(source = "key", target = "id")
    Professor toEntity(ProfessorDTO dto);

    @Mapping(source = "id", target = "key")
    List<ProfessorDTO> toDTOList(List<Professor> entity);

    @Mapping(source = "key", target = "id")
    List<Professor> toEntityList(List<ProfessorDTO> dto);
}
