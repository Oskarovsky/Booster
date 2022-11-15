package com.oskarro.apiclient.model.auth;

import com.oskarro.apiclient.model.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserMan implements Serializable, BaseEntity<UserMan, Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    @Override
    public void update(UserMan source) {
        this.username = source.username;
        this.roles = source.roles;
    }

    @Override
    public UserMan createNewInstance() {
        UserMan newInstance = new UserMan();
        newInstance.update(this);
        return newInstance;
    }
}
