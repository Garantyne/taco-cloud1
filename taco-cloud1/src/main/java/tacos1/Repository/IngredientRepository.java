package tacos1.Repository;

import org.springframework.data.repository.CrudRepository;

import tacos1.entity.Ingredient;

import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
