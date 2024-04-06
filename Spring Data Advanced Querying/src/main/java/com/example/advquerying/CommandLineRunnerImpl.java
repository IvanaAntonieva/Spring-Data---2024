package com.example.advquerying;

import com.example.advquerying.service.IngredientService;
import com.example.advquerying.service.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    public CommandLineRunnerImpl(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        this.shampooService.getAllShampoosByGivenSize(reader.readLine())
//                .forEach(System.out::println);
//        this.shampooService.getAllShampoosContainingIngredient(List.of(reader.readLine().split(" ")))
//                .forEach(System.out::println);
//        this.shampooService.getAllShampoosByGivenSizeOrLabel("medium", 10)
//                .forEach(System.out::println);
//        this.shampooService.getAllShampoosWithPriceHigherThan(BigDecimal.valueOf(5)).
//                forEach(System.out::println);
//        this.ingredientService.getAllIngredientWithStartingName("M")
//                .forEach(System.out::println);
//        System.out.println(shampooService.countOfIngredientsWithPriceLessThan(BigDecimal.valueOf(8.5)));
//        System.out.println(this.shampooService.getAllShampoosWithCountOfIngredientsBelowNumber());
//        System.out.println(this.ingredientService.deleteIngredientByName("Nettle"));
//        System.out.println(this.ingredientService.updateIngredientPrices());
        System.out.println(this.ingredientService.updatePricesForGivenNames());
    }
}
