package br.com.matheus.gerenciadordetreinamentos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Presenças em Treinamentos")
                        .description("API para gerenciar as presenças de treinamentos de uma empresa")
                        .contact(new Contact()
                                .name("Matheus Crivellaro")
                                .email("matheuscriv@gmail.com")
                                .url("https://github.com/MatheusCrivellaro")
                        )
                        .version("1.1.3"));
    }

}
