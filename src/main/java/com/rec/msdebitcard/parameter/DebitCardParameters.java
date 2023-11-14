package com.rec.msdebitcard.parameter;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.yml")
@Data
public class DebitCardParameters {

    @Value("${application.third.app.msclient}")
    private String clientUrl;
}
