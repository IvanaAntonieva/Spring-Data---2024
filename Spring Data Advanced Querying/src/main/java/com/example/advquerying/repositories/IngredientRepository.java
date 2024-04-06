package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
   // List<Ingredient> findAllByNameIn
    Set<Ingredient> findAllByNameStartsWith(String symbol);

    @Transactional
    @Modifying
    @Query("DELETE Ingredient WHERE name=:name")
    int deleteIngredientByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE Ingredient SET price = price * :percent")
    int updateAllByPrice(BigDecimal percent);

    @Modifying
    @Transactional
    @Query("UPDATE Ingredient SET price = price * :percent WHERE name in :names")
    int updateAllByPriceForGivenNames(BigDecimal percent, @Param("names") List<String> names);
}
