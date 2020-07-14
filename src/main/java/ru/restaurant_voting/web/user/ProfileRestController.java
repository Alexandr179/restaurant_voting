package ru.restaurant_voting.web.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import ru.restaurant_voting.model.User;

import static ru.restaurant_voting.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(ProfileRestController.REST_URL)
public class ProfileRestController extends AbstractUserController {
    static final String REST_URL = "/rest/profile";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        return super.get(authUserId());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete() {
        super.delete(authUserId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user) throws BindException {
        super.update(user, authUserId());
    }

    @GetMapping(value = "/text")
    public String testUTF() {
        return "Test ProfileRestController: .../restaurant_voting/rest/profile/text is OK. UTF-8";
    }
}