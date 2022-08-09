package by.tms.projectinterpol.util;

public final class PageUtil {

    private PageUtil() {
        throw new UnsupportedOperationException();
    }

    public static final String USER_ATTRIBUTE = "sessionUser";
    public static final String USERS_PAGE = "usersPage";
    public static final String SHOW_ALL_USERS_PAGE = "showAllUsers";
    public static final String REQUESTS_PAGE = "requestsPage";
    public static final String SHOW_ALL_DISAPPROVED_REQUEST = "showAllDisapprovedRequest";
    public static final String SHOW_WANTED_APPROVED_REQUEST = "showAllWantedRequests&offset=0";
    public static final String SHOW_MISSING_APPROVED_REQUEST = "showAllMissingRequests&offset=0";
    public static final String INTERPOL_PAGE = "interpol";
    public static final String REFERER_HEADER = "Referer";
    public static final String MISSING_PAGE = "missingPage";
    public static final String WANTED_PAGE = "wantedPage";
    public static final String ADMIN_PAGE = "admin";
    public static final String LOGIN_PAGE = "loginPage";
    public static final String NEWS_PAGE = "newsPage";
    public static final String LANGUAGE_ATTRIBUTE = "language";
    public static final String LANGUAGE_PARAMETER = "lang";
    public static final String ERROR_PAGE = "errorPage";
    public static final String CREATE_NEWS_PAGE = "createNews";
    public static final String SHOW_ALL_TAGS_PAGE = "showAllTags";
    public static final String CREATE_REQUEST_PAGE = "createRequest";

    public static final int OFFSET_ZERO = 0;
    public static final int ONE_BOOK = 1;
    public static final int LIMIT_TEN = 10;
}
