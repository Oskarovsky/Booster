package com.oskarro.booster.gateway;

import com.oskarro.booster.model.Meal;
import com.oskarro.booster.service.MealService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MealGateway.class)
@EnableSpringDataWebSupport
public class MealGatewayTest {

    @MockBean
    private MealService mealService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getMealById() throws Exception {
        Meal meal = new Meal();
        meal.setProductById(1);

        given(mealService.getMealById(1)).willReturn(meal);

        mvc.perform(get("/api/meal/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.portion", is(meal.getPortion())))
                .andExpect(jsonPath("$.productId").value(is(meal.getProduct().getId()), Integer.class));
    }
}
