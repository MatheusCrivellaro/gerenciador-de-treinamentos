package br.com.matheus.gerenciadordetreinamentos.service;

import br.com.matheus.gerenciadordetreinamentos.repository.TreinamentoRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodigoTreinamentoService {

    @Autowired
    private TreinamentoRepository treinamentoRepository;

    public String buildCodigo() {
        String code;
        do
            code = RandomStringUtils.randomAlphanumeric(9);
        while (treinamentoRepository.findByCodigoAndAtivoTrue(code).isPresent());
        return code;
    }

}
