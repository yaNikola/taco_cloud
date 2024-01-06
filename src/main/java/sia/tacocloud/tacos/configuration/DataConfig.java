package sia.tacocloud.tacos.configuration;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sia.tacocloud.tacos.entities.Ingredient;
import sia.tacocloud.tacos.repositories.IngredientRepository;

@Configuration
public class DataConfig {
    @Bean
    public ApplicationRunner dataLoader(IngredientRepository ingredientRepo) {
        return args -> {
            ingredientRepo.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
            ingredientRepo.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
            ingredientRepo.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
            ingredientRepo.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
            ingredientRepo.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
            ingredientRepo.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
            ingredientRepo.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
            ingredientRepo.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
            ingredientRepo.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
            ingredientRepo.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
        };
    }
}
