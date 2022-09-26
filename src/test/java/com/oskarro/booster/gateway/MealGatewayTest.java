package com.oskarro.booster.gateway;

import com.oskarro.booster.model.Meal;
import com.oskarro.booster.service.MealService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MealGateway.class)
@EnableSpringDataWebSupport
@ActiveProfiles("test")
public class MealGatewayTest {

    @MockBean
    private MealService mealService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void test_gateway_getMealById() throws Exception {
        Meal meal = new Meal();
        meal.setProductById(1);

        given(mealService.getById(1)).willReturn(meal);

        mvc.perform(get("/api/meal/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.portion", is(meal.getPortion())))
                .andExpect(jsonPath("$.product").value(is(meal.getProduct().getId()), Integer.class));
    }

    @Test
    public void test_gateway_getAllMeals() throws Exception {
        Meal mealOne = new Meal();
        mealOne.setProductById(1);

        Meal mealTwo = new Meal();
        mealTwo.setProductById(1);

        Meal mealThree = new Meal();
        mealThree.setProductById(2);
        List<Meal> meals = Arrays.asList(mealOne, mealTwo, mealThree);
        PageImpl<Meal> expectedResult = new PageImpl<>(meals);

        given(mealService.getPage(Mockito.any(PageRequest.class))).willReturn(expectedResult);

        mvc.perform(get("/api/meal").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(3)))
                .andExpect(jsonPath("$.content[0].product", is(1)))
                .andExpect(jsonPath("$.content[1].product", is(1)))
                .andExpect(jsonPath("$.content[2].product", is(2)));
    }
}
