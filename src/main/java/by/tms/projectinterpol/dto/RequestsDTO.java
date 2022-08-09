package by.tms.projectinterpol.dto;

import by.tms.projectinterpol.entity.Gender;
import by.tms.projectinterpol.entity.Status;

import java.io.Serializable;
import java.util.Objects;

public class RequestsDTO implements Serializable {

    private String firstName;
    private String lastName;
    private Integer age;
    private String nationality;
    private Gender gender;
    private String details;
    private Integer reward;
    private Status status;
    private boolean approved;
    private UserDTO users;

    public RequestsDTO(String firstName, String lastName, Integer age, String nationality, Gender gender, String details, Integer reward, Status status, boolean approved, UserDTO users) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.nationality = nationality;
        this.gender = gender;
        this.details = details;
        this.reward = reward;
        this.status = status;
        this.approved = approved;
        this.users = users;
    }

    public RequestsDTO() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private Integer age;
        private String nationality;
        private Gender gender;
        private String details;
        private Integer reward;
        private Status status;
        private boolean approved;
        private UserDTO users;

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public Builder nationality(String nationality) {
            this.nationality = nationality;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder details(String details) {
            this.details = details;
            return this;
        }

        public Builder reward(Integer reward) {
            this.reward = reward;
            return this;
        }

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public Builder approved(boolean approved) {
            this.approved = approved;
            return this;
        }

        public Builder users(UserDTO users) {
            this.users = users;
            return this;
        }

        public RequestsDTO build() {
            return new RequestsDTO(firstName, lastName, age, nationality, gender, details, reward, status, approved, users);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public UserDTO getUsers() {
        return users;
    }

    public void setUsers(UserDTO users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Requests{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", nationality='" + nationality + '\'' +
                ", gender=" + gender +
                ", details='" + details + '\'' +
                ", reward=" + reward +
                ", status=" + status +
                ", approved=" + approved +
                ", users=" + users +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestsDTO requestsDTO = (RequestsDTO) o;
        return approved == requestsDTO.approved && Objects.equals(firstName, requestsDTO.firstName) && Objects.equals(lastName, requestsDTO.lastName) && Objects.equals(age, requestsDTO.age) && Objects.equals(nationality, requestsDTO.nationality) && gender == requestsDTO.gender && Objects.equals(details, requestsDTO.details) && Objects.equals(reward, requestsDTO.reward) && status == requestsDTO.status && Objects.equals(users, requestsDTO.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, nationality, gender, details, reward, status, approved, users);
    }
}
