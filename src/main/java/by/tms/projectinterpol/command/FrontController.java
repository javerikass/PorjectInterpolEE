package by.tms.projectinterpol.command;

import by.tms.projectinterpol.util.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(urlPatterns = "/controller")
public class FrontController extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(FrontController.class);
    public static final String COMMAND = "command";
    public static final String UNEXPECTED_VALUE = "Unexpected value: ";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            LOGGER.info(LoggerUtil.DO_GET_METHOD_PROCESSING_MESSAGE);
            processCommand(req, resp);
        } catch (ServletException | IOException e) {
            LOGGER.error(LoggerUtil.DO_GET_METHOD_PROCESSING_MESSAGE, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            LOGGER.info(LoggerUtil.DO_GET_METHOD_PROCESSING_MESSAGE);
            processCommand(req, resp);
        } catch (ServletException | IOException e) {
            LOGGER.error(LoggerUtil.DO_GET_METHOD_PROCESSING_MESSAGE, e);
        }
    }

    private void processCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = CommandFactory.defineCommand(req.getParameter(COMMAND));
        if (Objects.nonNull(command)) {
            CommandResult result = command.execute(req, resp);
            String navigationType = result.getNavigationType();
            String pageName = result.getPageName();
            switch (navigationType) {
                case CommandResult.FORWARD -> req.getServletContext().getRequestDispatcher(pageName).forward(req, resp);
                case CommandResult.INCLUDE -> req.getServletContext().getRequestDispatcher(pageName).include(req, resp);
                case CommandResult.REDIRECT -> resp.sendRedirect(pageName);
                default -> throw new IllegalStateException(UNEXPECTED_VALUE + navigationType);
            }
        }
    }
}
