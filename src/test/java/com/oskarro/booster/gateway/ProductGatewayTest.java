package com.oskarro.booster.gateway;

import com.oskarro.booster.model.Meal;
import com.oskarro.booster.model.Product;
import com.oskarro.booster.service.MealService;
import com.oskarro.booster.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductGateway.class)
@EnableSpringDataWebSupport
@ActiveProfiles("test")
public class ProductGatewayTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void test_gateway_getProductById() throws Exception {
        Product product = new Product();
        product.setProviderById(999);
        product.setEnergy(10);
        product.setCarbs(0.5);

        given(productService.getById(999)).willReturn(product);

        mvc.perform(get("/api/product/999").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.provider", is(999)))
                .andExpect(jsonPath("$.energy", is(10)))
                .andExpect(jsonPath("$.carbs", is(0.5)));
    }

    @Test
    public void test_gateway_getAllProducts() throws Exception {
        Product productOne = new Product();
        productOne.setProviderById(999);
        productOne.setEnergy(10);
        productOne.setCarbs(0.5);

        Product productTwo = new Product();
        productTwo.setProviderById(666);
        productTwo.setEnergy(100);
        productTwo.setCarbs(0.1);

        List<Product> products = Arrays.asList(productOne, productTwo);
        PageImpl<Product> expectedResult = new PageImpl<>(products);

        given(productService.getPage(Mockito.any(PageRequest.class))).willReturn(expectedResult);

        mvc.perform(get("/api/product").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(2)))
                .andExpect(jsonPath("$.content[0].provider", is(999)))
                .andExpect(jsonPath("$.content[0].energy", is(10)))
                .andExpect(jsonPath("$.content[0].carbs", is(0.5)))
                .andExpect(jsonPath("$.content[1].provider", is(666)));
    }
}
