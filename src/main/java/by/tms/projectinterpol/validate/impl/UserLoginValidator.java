package by.tms.projectinterpol.validate.impl;

import by.tms.projectinterpol.entity.User;
import by.tms.projectinterpol.service.UserService;
import by.tms.projectinterpol.service.impl.UserServiceImpl;
import by.tms.projectinterpol.validate.Validator;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;

public class UserLoginValidator implements Validator<User> {

    @Override
    public Optional<User> validate(HttpServletRequest req) {
        UserService userService = UserServiceImpl.getInstance();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (Objects.nonNull(username) && !username.isBlank() && Objects.nonNull(password) && !password.isBlank()) {
            Optional<User> optionalUser = userService.findUserByUsername(username);
            if (optionalUser.isPresent()) {
                byte[] decodePass = Base64.encodeBase64(password.getBytes(StandardCharsets.UTF_8));
                if (Objects.equals(new String(decodePass), optionalUser.get().getPassword())) {
                    return optionalUser;
                }
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
        return Optional.empty();
    }
}
