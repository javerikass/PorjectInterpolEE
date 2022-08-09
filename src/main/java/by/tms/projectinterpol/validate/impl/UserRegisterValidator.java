package by.tms.projectinterpol.validate.impl;

import by.tms.projectinterpol.entity.Role;
import by.tms.projectinterpol.entity.User;
import by.tms.projectinterpol.validate.Validator;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

public class UserRegisterValidator implements Validator<User> {

    @Override
    public Optional<User> validate(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (isCorrectUsername(username) && isCorrectPassword(password)) {
            byte[] encodePass = Base64.encodeBase64(password.getBytes(StandardCharsets.UTF_8));
            User userForResult = User.builder().username(username).password(new String(encodePass)).role(Role.USER).build();
            return Optional.of(userForResult);
        } else {
            return Optional.empty();
        }
    }

    public static boolean isCorrectUsername(String username) {
        return Objects.nonNull(username) && Pattern.matches("^[A-Za-z0-9]{3,10}$", username);
    }

    public static boolean isCorrectPassword(String password) {
        return Objects.nonNull(password) && Pattern.matches("^[A-Za-z0-9\\._]{4,16}$", password);
    }
}
