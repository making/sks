package sks.domain.service.userdetails;

import lombok.Data;
import org.springframework.security.core.authority.AuthorityUtils;

@Data
public class LoginUserDetails extends org.springframework.security.core.userdetails.User {
    private final String firstName;
    private final String lastName;

    public LoginUserDetails(String username, String firstName, String lastName) {
        super(username, "****", AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.firstName = firstName;
        this.lastName = lastName;
    }
}