package com.example.diploma.model;

import com.example.diploma.enumiration.ERole;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_login")
})
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private Long id;
    @Column(name = "user_login", nullable = false)
    private String login;
    @Column(name = "user_password", nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @Column(name = "user_status_id", nullable = false)
    private Long status;
    @Column(name = "user_link", nullable = true)
    private String link;

    public User(String login, String password, Set<Role> roles, Long status, String link) {
        this.login = login;
        this.password = password;
        this.roles = roles;
        this.status = status;
        this.link = link;
    }

    public User(String login, String password, Long status) {
        this.login = login;
        this.password = password;
        this.status = status;
    }

    public User(String login, String password, Set<Role> roles, Long status) {
        this.login = login;
        this.password = password;
        this.roles = roles;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles) && Objects.equals(status, user.status) && Objects.equals(link, user.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, roles, status, link);
    }
}
