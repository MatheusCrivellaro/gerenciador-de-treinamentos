package br.com.matheus.gerenciadordetreinamentos.mapeador.mapStruct;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Funcionario;
import br.com.matheus.gerenciadordetreinamentos.dto.FuncionarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FuncionarioMapper {

    FuncionarioMapper INSTANCE = Mappers.getMapper( FuncionarioMapper.class );

    @Mapping(source = "id", target = "key")
    FuncionarioDTO toDTO(Funcionario entity);

    @Mapping(source = "key", target = "id")
    Funcionario toEntity(FuncionarioDTO dto);

    @Mapping(source = "id", target = "key")
    List<FuncionarioDTO> toDTOList(List<Funcionario> entity);

    @Mapping(source = "key", target = "id")
    List<Funcionario> toEntityList(List<FuncionarioDTO> dto);
}
