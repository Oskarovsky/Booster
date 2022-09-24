package com.oskarro.booster.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@Entity(name = "provider")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    public void addProduct(Product product) {
        if (product == null)
            throw new NullPointerException("Could not add null Product");
        if (product.getProvider() != null)
            throw new IllegalStateException("Product is already assigned to an Provider");
        products.add(product);
        product.setProvider(this);
    }

    public static Provider fromId(Integer providerId) {
        Provider provider = new Provider();
        provider.setId(providerId);
        return provider;
    }

    public void updateRelations() {
        products.forEach(product -> product.setProvider(this));
    }

    @Override
    public void update(Provider provider) {
        this.name = provider.getName();
        this.address = provider.getAddress();
        this.city = provider.getCity();
        this.products = provider.getProducts();
    }

    @Override
    public Provider createNewInstance() {
        Provider newInstance = new Provider();
        newInstance.update(this);
        return newInstance;
    }
}
