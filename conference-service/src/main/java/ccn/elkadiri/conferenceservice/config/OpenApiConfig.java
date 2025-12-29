package ccn.elkadiri.conferenceservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI conferenceOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Conference Service API")
                .description("API de gestion des conférences et reviews pour le système de conférences")
                .version("1.0.0")
                .contact(new Contact()
                    .name("CCN El Kadiri")
                    .email("contact@ccn.elkadiri.ma")
                )
            );
    }
}
