package de.ait.project_KidVenture.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reward> reward = new ArrayList<>();

    @Column(name = "score")
    private int score;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Можно добавить логику проверки на истекший срок учетной записи
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Можно добавить логику проверки на блокировку учетной записи
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Можно добавить логику проверки на истекшие учетные данные
    }

    @Override
    public boolean isEnabled() {
        return isActive; // Можно добавить дополнительную логику активации учетной записи
    }
}
