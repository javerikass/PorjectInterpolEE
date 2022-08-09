package by.tms.projectinterpol.entity;

public enum Status {

    WANTED("Wanted"), MISSING("Missing");

    private final String statusName;

    Status(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }
}
