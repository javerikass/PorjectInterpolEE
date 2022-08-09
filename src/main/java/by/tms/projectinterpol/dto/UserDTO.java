package by.tms.projectinterpol.dto;

import by.tms.projectinterpol.entity.Role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDTO implements Serializable {

    private String username;
    private String password;
    private Role role;
    private List<RequestsDTO> requests = new ArrayList<>();

    public UserDTO() {
    }

    public UserDTO(String username, String password, Role role, List<RequestsDTO> requests) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.requests = requests;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String username;
        private String password;
        private Role role;
        private List<RequestsDTO> requests = new ArrayList<>();

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

        public Builder requests(List<RequestsDTO> requests) {
            this.requests = requests;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(username, password, role, requests);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, role, requests);
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

    public List<RequestsDTO> getRequests() {
        return requests;
    }

    public void setRequests(List<RequestsDTO> requests) {
        this.requests = requests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return username.equals(userDTO.username) && password.equals(userDTO.password) && role == userDTO.role && Objects.equals(requests, userDTO.requests);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", requests=" + requests +
                '}';
    }
}
