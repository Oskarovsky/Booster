package com.oskarro.booster.gateway;

import com.oskarro.booster.model.Product;
import com.oskarro.booster.model.Provider;
import com.oskarro.booster.service.ProviderService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
@WebMvcTest(ProviderGateway.class)
@EnableSpringDataWebSupport
@ActiveProfiles("test")
public class ProviderGatewayTest {

    @MockBean
    private ProviderService providerService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void test_gateway_getProviderById() throws Exception {
        Provider provider = new Provider();
        provider.setId(55);
        provider.setName("ProviderTest");
        provider.setCity("Warsaw");

        given(providerService.getById(55)).willReturn(provider);

        mvc.perform(get("/api/provider/55").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(55)))
                .andExpect(jsonPath("$.name", is("ProviderTest")))
                .andExpect(jsonPath("$.city", is("Warsaw")));
    }

    @Test
    public void test_gateway_getAllProviders() throws Exception {
        Provider providerOne = new Provider();
        providerOne.setId(11);
        providerOne.setName("ProviderTest1");
        providerOne.setCity("Warsaw");

        Provider providerTwo = new Provider();
        providerTwo.setId(22);
        providerTwo.setName("ProviderTest2");
        providerTwo.setCity("Cracow");

        List<Provider> providers = Arrays.asList(providerOne, providerTwo);
        PageImpl<Provider> expectedResult = new PageImpl<>(providers);

        given(providerService.getPage(Mockito.any(PageRequest.class))).willReturn(expectedResult);

        mvc.perform(get("/api/provider").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(2)))
                .andExpect(jsonPath("$.content[0].id", is(11)))
                .andExpect(jsonPath("$.content[0].name", is("ProviderTest1")))
                .andExpect(jsonPath("$.content[1].city", is("Cracow")))
                .andExpect(jsonPath("$.content[1].name", is("ProviderTest2")));
    }
}
