package com.oskarro.apiclient.model.auth;

import com.oskarro.apiclient.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "role")
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable, BaseEntity<Role, Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserRole name;

    public Role(UserRole name) {
        this.name = name;
    }

    @Override
    public void update(Role source) {

    }

    @Override
    public Role createNewInstance() {
        Role newInstance = new Role();
        newInstance.update(this);
        return newInstance;
    }
}