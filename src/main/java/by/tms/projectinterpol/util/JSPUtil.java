package by.tms.projectinterpol.util;

public final class JSPUtil {

    private static final String BASE_PREFIX = "/page/";
    private static final String SUFFIX = ".jsp";
    public static final String ERROR_PAGE = "/page/errorPage.jsp";
    public static final String CONTROLLER_COMMAND = "/controller?command=";

    private JSPUtil() {
        throw new UnsupportedOperationException();
    }

    public static String getJSPPath(String jspName) {
        return BASE_PREFIX + jspName + SUFFIX;
    }

    public static String getControllerCommandPath(String command) {
        return CONTROLLER_COMMAND + command;
    }
}
