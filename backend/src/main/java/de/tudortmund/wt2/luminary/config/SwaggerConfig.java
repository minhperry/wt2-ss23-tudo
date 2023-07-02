package de.tudortmund.wt2.luminary.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {
    @Bean
    public OpenAPI SwaggerOpenApiInfo() {
        return new OpenAPI()
                .info(new Info().title("Luminary API, a project for WEBTECHNOLOGIEN 2")
                        .description("Luminary is a groundbreaking social media platform that encourages users to share their creative ideas, connecting them with like-minded individuals and potential collaborators to turn those ideas into reality, fostering innovation and positive change in the world. Join Luminary to unleash the power of your imagination and shape a brighter, more innovative future for everyone."));
    }
}