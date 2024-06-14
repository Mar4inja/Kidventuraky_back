package de.ait.project_KidVenture.entity.dto;



import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
public class UserDto {



        private Long id;
        private String firstName;
        private String lastName;
        private Integer age;
        private String email;
        private Set<String> roles; // Mainīt uz String vai citu atbilstošu veidu, atkarībā no jūsu Role klases
        private LocalDate registrationDate;
        private boolean isActive;
        private int score;
        private String password;


        public UserDto(Integer age, String email, String firstName, Long id, boolean isActive, String lastName, String password, LocalDate registrationDate, Set<String> roles, int score) {
                this.age = age;
                this.email = email;
                this.firstName = firstName;
                this.id = id;
                this.isActive = isActive;
                this.lastName = lastName;
                this.password = password;
                this.registrationDate = registrationDate;
                this.roles = roles;
                this.score = score;
        }

        public UserDto() {
        }

        public Integer getAge() {
                return age;
        }

        public void setAge(Integer age) {
                this.age = age;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public boolean isActive() {
                return isActive;
        }

        public void setActive(boolean active) {
                isActive = active;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public LocalDate getRegistrationDate() {
                return registrationDate;
        }

        public void setRegistrationDate(LocalDate registrationDate) {
                this.registrationDate = registrationDate;
        }

        public Set<String> getRoles() {
                return roles;
        }

        public void setRoles(Set<String> roles) {
                this.roles = roles;
        }

        public int getScore() {
                return score;
        }

        public void setScore(int score) {
                this.score = score;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                UserDto userDto = (UserDto) o;
                return isActive == userDto.isActive && score == userDto.score && Objects.equals(id, userDto.id) && Objects.equals(firstName, userDto.firstName) && Objects.equals(lastName, userDto.lastName) && Objects.equals(age, userDto.age) && Objects.equals(email, userDto.email) && Objects.equals(roles, userDto.roles) && Objects.equals(registrationDate, userDto.registrationDate) && Objects.equals(password, userDto.password);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, firstName, lastName, age, email, roles, registrationDate, isActive, score, password);
        }

        @Override
        public String toString() {
                return "UserDto{" +
                        "age=" + age +
                        ", id=" + id +
                        ", firstName='" + firstName + '\'' +
                        ", lastName='" + lastName + '\'' +
                        ", email='" + email + '\'' +
                        ", roles=" + roles +
                        ", registrationDate=" + registrationDate +
                        ", isActive=" + isActive +
                        ", score=" + score +
                        ", password='" + password + '\'' +
                        '}';
        }
}

