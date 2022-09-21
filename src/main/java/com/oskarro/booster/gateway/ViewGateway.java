package com.oskarro.booster.gateway;

import com.oskarro.booster.model.Provider;
import com.oskarro.booster.service.CalculatorService;
import com.oskarro.booster.service.MealService;
import com.oskarro.booster.service.ProductService;
import com.oskarro.booster.service.ProviderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/view")
public class ViewGateway {

    private final ProductService productService;
    private final ProviderService providerService;
    private final MealService mealService;
    private final CalculatorService calculatorService;

    public ViewGateway(ProductService productService, ProviderService providerService,
                       MealService mealService, CalculatorService calculatorService) {
        this.productService = productService;
        this.providerService = providerService;
        this.mealService = mealService;
        this.calculatorService = calculatorService;
    }

    @GetMapping(value = "/")
    public String indexPage(Model model) {
        return "index";
    }

    @PostMapping(value = "/provider/add")
    public String addProvider(@Valid Provider provider, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "provider-add";
        }
        providerService.save(provider);
        return "redirect:/provider-list";
    }
}
