package sia.tacocloud.tacos.repositories;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.entities.Ingredient;

import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
