package es.nextdigital.commons.config;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("app.bank")
public class BankProperties {
    private final List<BankEntityConfig> externalBankEntitiesConfig;
}
