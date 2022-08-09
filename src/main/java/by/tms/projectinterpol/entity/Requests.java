package by.tms.projectinterpol.entity;

import java.util.Objects;

public class Requests {

    private long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String nationality;
    private Gender gender;
    private String details;
    private Integer reward;
    private Status status;
    private boolean approved;
    private User users;

    public Requests(long id, String firstName, String lastName, Integer age, String nationality, Gender gender, String details, Integer reward, Status status, boolean approved, User users) {
        this.id = id;
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

    public Requests() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private long id;
        private String firstName;
        private String lastName;
        private Integer age;
        private String nationality;
        private Gender gender;
        private String details;
        private Integer reward;
        private Status status;
        private boolean approved;
        private User users;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

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

        public Builder users(User users) {
            this.users = users;
            return this;
        }

        public Requests build() {
            return new Requests(id,firstName, lastName, age, nationality, gender, details, reward, status, approved, users);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Requests requests = (Requests) o;
        return id == requests.id && approved == requests.approved && Objects.equals(firstName, requests.firstName) && Objects.equals(lastName, requests.lastName) && Objects.equals(age, requests.age) && Objects.equals(nationality, requests.nationality) && gender == requests.gender && Objects.equals(details, requests.details) && Objects.equals(reward, requests.reward) && status == requests.status && Objects.equals(users, requests.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, nationality, gender, details, reward, status, approved, users);
    }

    @Override
    public String toString() {
        return "Requests{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
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
}
