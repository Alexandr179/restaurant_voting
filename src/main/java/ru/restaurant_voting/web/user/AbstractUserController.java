package ru.restaurant_voting.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import ru.restaurant_voting.HasId;
import ru.restaurant_voting.model.AbstractBaseEntity;
import ru.restaurant_voting.model.User;
import ru.restaurant_voting.repository.UserRepository;
import ru.restaurant_voting.util.exception.ModificationRestrictionException;

import java.util.List;

import static ru.restaurant_voting.util.ValidationUtil.assureIdConsistent;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    // Validate manually cause UniqueMailValidator doesn't work for update with user.id==null
    private WebDataBinder binder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UniqueMailValidator emailValidator;

    private boolean modificationRestriction;


//    @Autowired
//    @SuppressWarnings("deprecation")
//    public void setEnvironment(Environment environment) {
//        modificationRestriction = environment.acceptsProfiles(Profiles.POSTGRES_DB);// профиль !!!!!
//    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(emailValidator);
    }

    public List<User> getAll() {
        log.info("getAll");
        return userRepository.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return userRepository.get(id);
    }

    public User create(User user) {
        log.info("create {}", user);
//        checkNew(user);
        return userRepository.save(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
//        checkModificationAllowed(id); // -------------------------------- падение, без check.. проходит -------------
        userRepository.delete(id);
    }

    public void update(User user, int id) throws BindException {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
//        checkModificationAllowed(id);
        userRepository.save(user);
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return userRepository.getByEmail(email);
    }

    public void enable(int id, boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        checkModificationAllowed(id);
        userRepository.enable(id, enabled);
    }

    private void checkModificationAllowed(int id) {
        if (modificationRestriction && id < AbstractBaseEntity.START_SEQ + 2) {
            throw new ModificationRestrictionException();
        }
    }

    protected void checkAndValidateForUpdate(HasId user, int id) throws BindException {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
//        checkModificationAllowed(id);
//        binder.validate();
//        if (binder.getBindingResult().hasErrors()) {
//            throw new BindException(binder.getBindingResult());
//        }
    }
}