package com.oskarro.booster.service;

import com.oskarro.booster.model.Product;
import com.oskarro.booster.repository.ProductRepository;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceMockTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;


    // TODO fix slyko
    public void whenSaveProduct_shouldReturnProduct() {
        // GIVEN
        Product product = Product.builder().name("ProductTest").energy(430).build();

        // WHEN
        when(productRepository.save(ArgumentMatchers.any(Product.class))).thenReturn(product);
        Product productCreated = productService.save(product);

        assertThat(productCreated.getName()).isSameAs(product.getName());
        verify(productRepository).save(product);
    }
}
