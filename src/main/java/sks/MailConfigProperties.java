package sks;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "sks.mail")
@Data
public class MailConfigProperties {
    private String protocol = "smtp";
    private String host;
    private int port = 25;
    private boolean auth = true;
    private boolean starttls = true;
    private String from = "noreply@ik.am";
    private String username;
    private String password;
}
