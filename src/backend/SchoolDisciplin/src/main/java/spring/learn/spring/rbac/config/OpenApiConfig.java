package spring.learn.spring.rbac.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {

        @Value("${openapi.definition.server.url}")
        private String serverUrl;

        @Value("${openapi.definition.server.description}")
        private String serverDescription;

        @Bean
        public OpenAPI openAPI() {
                return new OpenAPI()
                                .addSecurityItem(new SecurityRequirement()
                                                .addList("bearerAuth"))
                                .components(new Components()
                                                .addSecuritySchemes("bearerAuth", securityScheme()))
                                .info(new Info().title("OpenAPI Specification")
                                                .description("OpenAPI Documentation for V REST API")
                                                .version("1.0").contact(contact()))
                                .servers(Arrays.asList(server()));
        }

        private Contact contact() {
                Contact contact = new Contact();
                contact.setEmail("vergezkenfack2004@gmail.com");
                contact.setName("SchoolDisciplin");
                contact.setUrl("");
                return contact;
        }

        private SecurityScheme securityScheme() {
                return new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .in(SecurityScheme.In.HEADER)
                                .description("JWT auth description")
                                .scheme("bearer")
                                .bearerFormat("JWT");
        }

        private Server server() {
                return new Server().url(serverUrl).description(serverDescription);
        }
}
