package com.example.Recipes;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RecipesRepository extends CrudRepository<Recipes, Long> {
    @Query(value = "Select*from recipes where UPPER(category) =UPPER(?1) OR" +
            " UPPER(name) like UPPER(concat('%', ?1,'%')) order by date desc", nativeQuery = true)
    List<Recipes> findByNameContainingIgnoreCase(@Param("category") String category, @Param("name") String name);
}
