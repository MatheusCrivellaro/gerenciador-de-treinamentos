package br.com.matheus.gerenciadordetreinamentos.mapeador;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Administrador;
import br.com.matheus.gerenciadordetreinamentos.dto.AdministradorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AdministradorMapper {

    AdministradorMapper INSTANCE = Mappers.getMapper( AdministradorMapper.class );

    @Mapping(source = "id", target = "key")
    AdministradorDTO toDTO(Administrador entity);

    @Mapping(source = "key", target = "id")
    Administrador toEntity(AdministradorDTO dto);

    @Mapping(source = "id", target = "key")
    List<AdministradorDTO> toDTOList(List<Administrador> entity);

    @Mapping(source = "key", target = "id")
    List<Administrador> toEntityList(List<AdministradorDTO> dto);
}
