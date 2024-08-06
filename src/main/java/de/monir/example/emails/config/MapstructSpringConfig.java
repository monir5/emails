package de.monir.example.emails.config;

import de.monir.example.emails.mapper.EmailMapper;
import de.monir.example.emails.mapper.impl.EmailMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapstructSpringConfig {
    @Bean
    EmailMapper emailMapper() {
        return new EmailMapperImpl();
    }
}