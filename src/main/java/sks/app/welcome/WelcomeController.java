package sks.app.welcome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sks.domain.model.Dummy;
import sks.domain.service.dummy.DummyService;
import sks.domain.service.userdetails.LoginUserDetails;

@RestController
public class WelcomeController {
    @Autowired
    DummyService dummyService;

    @RequestMapping(value = "/", produces = "text/plain;charset=utf-8")
    String hello(@AuthenticationPrincipal LoginUserDetails userDetails) {
        return "Hello " + userDetails.getLastName() + " " + userDetails.getFirstName() + "!";
    }

    @RequestMapping("/dummy")
    Dummy dummy() {
        return dummyService.findOne();
    }
}
