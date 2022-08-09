package by.tms.projectinterpol.command.impl;


import by.tms.projectinterpol.command.Command;
import by.tms.projectinterpol.command.CommandResult;
import by.tms.projectinterpol.entity.User;
import by.tms.projectinterpol.service.UserService;
import by.tms.projectinterpol.service.impl.UserServiceImpl;
import by.tms.projectinterpol.util.JSPUtil;
import by.tms.projectinterpol.util.PageUtil;
import by.tms.projectinterpol.validate.Validator;
import by.tms.projectinterpol.validate.impl.UserRegisterValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.Optional;


public class RegisterCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        UserService userService = UserServiceImpl.getInstance();
        Validator<User> userValidator = new UserRegisterValidator();
        Optional<User> userOptional = userValidator.validate(req);
        if (userOptional.isPresent()) {
            if (userService.findUserByUsername(userOptional.get().getUsername()).isEmpty()) {
                userService.save(userOptional.get());
                Object attribute = req.getSession().getAttribute(PageUtil.USER_ATTRIBUTE);
                if (!Objects.nonNull(attribute)) {
                    req.getSession().setAttribute(PageUtil.USER_ATTRIBUTE, userOptional.get());
                }
                return new CommandResult(req.getContextPath() + JSPUtil.getJSPPath(PageUtil.INTERPOL_PAGE), CommandResult.REDIRECT);
            } else {
                return new CommandResult(req.getContextPath() + JSPUtil.ERROR_PAGE, CommandResult.REDIRECT);
            }
        } else {
            return new CommandResult(req.getContextPath() + JSPUtil.ERROR_PAGE, CommandResult.REDIRECT);
        }
    }
}
