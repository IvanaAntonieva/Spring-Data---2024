package com.example.advquerying.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ShampooService {
    List<String> getAllShampoosByGivenSize(String size);
    List<String> getAllShampoosByGivenSizeOrLabel(String size, long id);
    List<String> getAllShampoosContainingIngredient(List<String> strings);

    List<String> getAllShampoosWithPriceHigherThan(BigDecimal price);

    int countOfIngredientsWithPriceLessThan(BigDecimal price);
    Set<String> getAllShampoosWithCountOfIngredientsBelowNumber();
}
