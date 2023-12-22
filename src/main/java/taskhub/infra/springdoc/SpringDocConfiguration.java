package taskhub.infra.springdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;


@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI custOpenAPI(){
        return new OpenAPI()
        .components(new Components()
        .addSecuritySchemes("bearer-key", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
        .info(new Info()
            .title("TaskHub API")
            .description("API Rest da aplicação <strong>TaskHub</strong>, contendo as funcionalidades de CRUD de Projetos e Tarefas.<br> O TaskHub permite a criação de múltiplos projetos, cada um com sua própria equipe dedicada. Os usuários podem colaborar de forma eficaz, atribuindo tarefas a membros da equipe")
            .contact(new Contact()
            .name("Time Backend"))
            .license(new License()
            .name("Linkedin Desenvolvedor")
            .url("https://www.linkedin.com/in/gabriel-tessaro-b29728216/")));
}

}