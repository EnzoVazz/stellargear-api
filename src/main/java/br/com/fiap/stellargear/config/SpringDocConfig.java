package br.com.fiap.stellargear.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag; 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("🚀 StellarGear API 🌌")
                        .description("API REST do projeto StellarGear construída para a Global Solution. Monitoramento de sinais vitais ❤️, trajes 🚀 e equipes médicas 🏥.")
                        .version("1.0.0"))
                .addSecurityItem(new SecurityRequirement().addList("bearer-key"))
                
                .addTagsItem(new Tag().name("Autenticação"))
                .addTagsItem(new Tag().name("Passageiros"))
                .addTagsItem(new Tag().name("Médicos"))
                .addTagsItem(new Tag().name("Monitoramento IoT (Sensores)"));
    }
}