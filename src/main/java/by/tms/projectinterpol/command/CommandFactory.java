package by.tms.projectinterpol.command;

import by.tms.projectinterpol.command.impl.*;

public class CommandFactory {

    private CommandFactory() {
        throw new UnsupportedOperationException();
    }

    public static Command defineCommand(String commandName){
        return switch (commandName) {
            case "register" -> new RegisterCommand();
            case "login" -> new LoginCommand();
            case "logout" -> new LogoutCommand();
            case "deleteUsers" -> new DeleteUserCommand();
            case "createNews" -> new CreateNewsCommand();
            case "createTag" -> new CreateTagCommand();
            case "createRequest" -> new CreateRequestCommand();
            case "approveRequest" -> new ApproveRequestCommand();
            case "deleteRequest" -> new DeleteRequestCommand();
            case "showAllUsers" -> new ShowAllUsersCommand();
            case "showAllNews" -> new ShowAllNewsCommand();
            case "showAllTags" -> new ShowAllTagsCommand();
            case "showAllDisapprovedRequest" -> new ShowAllDisapprovedRequestCommand();
            case "showAllMissingRequests" -> new ShowAllMissingRequestCommand();
            case "showAllWantedRequests" -> new ShowAllWantedRequestCommand();
            case "changeLanguage" -> new ChangeLanguageCommand();
            default -> null;
        };
    }
}
