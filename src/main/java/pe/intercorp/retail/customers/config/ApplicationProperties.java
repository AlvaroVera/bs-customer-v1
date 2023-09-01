package pe.intercorp.retail.customers.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "application")
@Data
public class ApplicationProperties {
    private Map<String, String> indicators;

    private Constants constants;

    @Data
    public static class Constants {
        private Map<Integer, String> months;
        private String notFoundCustomer;
        private String internalServerErrorCustomer;
        private String notFoundIndicator;
    }

}
