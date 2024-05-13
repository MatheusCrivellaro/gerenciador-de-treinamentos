package br.com.matheus.gerenciadordetreinamentos.mapeador.mapStruct;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Treinamento;
import br.com.matheus.gerenciadordetreinamentos.dto.TreinamentoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TreinamentoMapper {

    TreinamentoMapper INSTANCE = Mappers.getMapper( TreinamentoMapper.class );

    @Mapping(source = "id", target = "key")
    TreinamentoDTO toDTO(Treinamento entity);

    @Mapping(source = "key", target = "id")
    Treinamento toEntity(TreinamentoDTO dto);

    @Mapping(source = "id", target = "key")
    List<TreinamentoDTO> toDTOList(List<Treinamento> entity);

    @Mapping(source = "key", target = "id")
    List<Treinamento> toEntityList(List<TreinamentoDTO> dto);
}
