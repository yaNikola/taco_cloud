package sia.tacocloud.tacos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sia.tacocloud.tacos.entities.Ingredient;
import sia.tacocloud.tacos.entities.udt.IngredientUDT;
import sia.tacocloud.tacos.repositories.IngredientRepository;
import sia.tacocloud.tacos.utils.TacoUDRUtils;

import java.util.HashMap;
import java.util.Map;

import static sia.tacocloud.tacos.entities.Ingredient.Type;

@Component
public class IngredientByIdConverter implements Converter<String, IngredientUDT> {

    private final IngredientRepository ingredientRepo;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public IngredientUDT convert(String id) {
        var ingredientUDT = TacoUDRUtils.toIngredientUDT(ingredientRepo.findById(id).get());
        return ingredientUDT;
    }
}
