package sks.domain.service.userdetails;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class Moneyger4UIntegratedAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    RestTemplate restTemplate;
    @Value("${moneyger4u.url:http://moneyger4u.ik.am}")
    String moneyger4uUrl;

    Pattern CSRF_TOKEN_PATTERN = Pattern.compile("name=\"_csrf\" value=\"([a-zA-Z0-9\\-]+)\"");
    Pattern JSESSION_ID_PATTERN = Pattern.compile("JSESSIONID=([a-zA-Z0-9\\-]+)");
    Pattern DISP_NAME_PATTERN = Pattern.compile("<span>(.+)</span>");

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        String username = authenticationToken.getName();
        String password = authenticationToken.getCredentials().toString();

        HttpEntity<MultiValueMap<String, String>> request = prepareRequest(username, password);
        if (request == null) {
            return null;
        }

        ResponseEntity<String> authResponse = restTemplate.postForEntity(moneyger4uUrl + "/authentication", request, String.class);
        String location = authResponse.getHeaders().getFirst("Location");
        if (!(moneyger4uUrl + "/").equals(location)) {
            return null;
        }

        UserDetails userDetails = createUserDetails(username, location, authResponse);
        if (userDetails == null) {
            return null;
        }
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    HttpEntity<MultiValueMap<String, String>> prepareRequest(String username, String password) {
        ResponseEntity<String> loginForm = restTemplate.getForEntity(moneyger4uUrl + "/login", String.class);

        Matcher csrfMatcher = CSRF_TOKEN_PATTERN.matcher(loginForm.getBody());
        if (!csrfMatcher.find()) {
            return null;
        }
        String csrfToken = csrfMatcher.group(1);

        Matcher sessionIdMatcher = JSESSION_ID_PATTERN.matcher(loginForm.getHeaders().getFirst("Set-Cookie"));
        if (!sessionIdMatcher.find()) {
            return null;
        }
        String jsessionId = sessionIdMatcher.group(1);

        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("username", username);
        param.add("password", password);
        param.add("_csrf", csrfToken);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        requestHeaders.add("Cookie", "JSESSIONID=" + jsessionId);

        return new HttpEntity<>(param, requestHeaders);
    }

    public UserDetails createUserDetails(String username, String location, ResponseEntity<String> authResponse) {
        Matcher sessionIdMatcher = JSESSION_ID_PATTERN.matcher(authResponse.getHeaders().getFirst("Set-Cookie"));
        if (!sessionIdMatcher.find()) {
            return null;
        }
        String jsessionId = sessionIdMatcher.group(1);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", "JSESSIONID=" + jsessionId);
        ResponseEntity<String> loggedIn = restTemplate.exchange(location, HttpMethod.GET, new HttpEntity<Void>(requestHeaders), String.class);
        Matcher dispNameMatcher = DISP_NAME_PATTERN.matcher(loggedIn.getBody());
        if (!dispNameMatcher.find()) {
            return null;
        }
        String[] dispName = dispNameMatcher.group(1).split("\\s", 2);
        String lastName = dispName[0];
        String firstName = dispName[1];

        return new LoginUserDetails(username, firstName, lastName);
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
