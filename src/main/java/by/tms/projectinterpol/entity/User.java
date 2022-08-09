package by.tms.projectinterpol.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {

    private long id;
    private String username;
    private String password;
    private Role role;
    private List<Requests> requests = new ArrayList<>();

    public User() {
    }

    public User(long id, String username, String password, Role role, List<Requests> requests) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.requests = requests;
    }

    public User(String username, String password, Role role, List<Requests> requests) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.requests = requests;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private long id;
        private String username;
        private String password;
        private Role role;
        private List<Requests> requests = new ArrayList<>();

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public Builder requests(List<Requests> requests) {
            this.requests = requests;
            return this;
        }

        public User build() {
            return new User(id,username, password, role, requests);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Requests> getRequests() {
        return requests;
    }

    public void setRequests(List<Requests> requests) {
        this.requests = requests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(password, user.password) && role == user.role && Objects.equals(requests, user.requests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, role, requests);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", requests=" + requests +
                '}';
    }
}
