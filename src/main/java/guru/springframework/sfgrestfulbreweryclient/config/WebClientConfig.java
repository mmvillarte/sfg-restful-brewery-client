package guru.springframework.sfgrestfulbreweryclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

// Beans definition
@Configuration
public class WebClientConfig {
    public static String LOCAL_PATH = "http://localhost:8080";

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(LOCAL_PATH)
                .defaultHeader("Accept", "application/json")
                .build();
    }

}
