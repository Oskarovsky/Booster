package com.oskarro.booster.model;

import com.oskarro.booster.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
public class Provider implements Serializable, BaseEntity<Provider, Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 2, max = 255, message = "Name is required, maximum 255 characters")
    private String name;

    private String city;

    private String address;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provider")
    private Set<Product> products = new HashSet<>();

    public void updateRelations() {
        products.forEach(product -> product.setProvider(this));
    }

    @Override
    public void update(Provider provider) {

    }

    @Override
    public Provider createNewInstance() {
        return null;
    }
}
