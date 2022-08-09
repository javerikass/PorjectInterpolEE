package by.tms.projectinterpol.command.impl;

import by.tms.projectinterpol.command.Command;
import by.tms.projectinterpol.command.CommandResult;
import by.tms.projectinterpol.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

import static by.tms.projectinterpol.util.PageUtil.LANGUAGE_ATTRIBUTE;
import static by.tms.projectinterpol.util.PageUtil.LANGUAGE_PARAMETER;

public class ChangeLanguageCommand implements Command {

    public static final String COUNTRY_RUSSIA = "RU";
    public static final String LANGUAGE_RUSSIAN = "ru";

    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lang = req.getParameter(LANGUAGE_PARAMETER);
        if (LANGUAGE_RUSSIAN.equalsIgnoreCase(lang)) {
            req.getSession().setAttribute(LANGUAGE_ATTRIBUTE, new Locale(LANGUAGE_RUSSIAN, COUNTRY_RUSSIA));
        } else {
            req.getSession().setAttribute(LANGUAGE_ATTRIBUTE, new Locale(Locale.US.getLanguage(), Locale.US.getCountry()));
        }
        String from = req.getHeader(PageUtil.REFERER_HEADER);
        return new CommandResult(from, CommandResult.REDIRECT);
    }
}
