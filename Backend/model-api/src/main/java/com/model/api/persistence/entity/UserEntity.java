package com.model.api.persistence.entity;

import com.model.api.persistence.audit.AuditUsername;
import com.model.api.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
@EntityListeners({AuditingEntityListener.class, AuditUsername.class})
@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends AuditableEntity implements Serializable {
    @Id // Tiene que ser el de Jakarta.
    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(length = 50)
    private String email;

    @Column(nullable = false)
    private Boolean locked;

    @Column(nullable = false)
    private Boolean disabled;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRoleEntity> roles;

    @Override
    public String toString() {
        return "UserEntity{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", locked=" + locked +
                ", disabled=" + disabled +
                ", roles=" + roles +
                '}';
    }
}
