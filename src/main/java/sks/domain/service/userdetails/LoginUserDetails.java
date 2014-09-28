package sks.domain.service.userdetails;

import lombok.Data;
import org.springframework.security.core.authority.AuthorityUtils;
import sks.domain.model.Notification;

@Data
public class LoginUserDetails extends org.springframework.security.core.userdetails.User {
    private final String firstName;
    private final String lastName;
    private final Notification notification;

    public LoginUserDetails(String username, String firstName, String lastName, Notification notification
    ) {
        super(username, "****", AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.firstName = firstName;
        this.lastName = lastName;
        this.notification = notification;
    }
}