package group_3.service;

import group_3.dao.PersonDAO;
import group_3.dao.impl.PersonDAOImpl;
import group_3.model.Person;
import group_3.util.PasswordUtil;

import java.util.Optional;

/**
 * @author Group21
 */
public class AuthService {

    private final PersonDAO personDAO = new PersonDAOImpl();

    public Optional<Person> login(String username, String rawPassword) {

        Optional<Person> userOpt = personDAO.findByUsername(username);

        if (userOpt.isPresent()) {
            Person user = userOpt.get();
            if (PasswordUtil.verifyPassword(rawPassword, user.getPasswordHash())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
