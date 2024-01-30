package sia.tacocloud.tacos.utils;

import sia.tacocloud.tacos.entities.Ingredient;
import sia.tacocloud.tacos.entities.udt.IngredientUDT;

import java.util.ArrayList;
import java.util.List;

public class TacoUDRUtils {
    public static IngredientUDT toIngredientUDT(Ingredient ingredient) {
        if(ingredient == null) {
            return new IngredientUDT();
        }
        return new IngredientUDT(ingredient.getName(), ingredient.getType());
    }

    public static List<IngredientUDT> toIngredientUDTList(List<Ingredient> ingredients) {
        List<IngredientUDT> ingredientsUDT = new ArrayList<>();
        for (var ingredient : ingredients) {
            ingredientsUDT.add(TacoUDRUtils.toIngredientUDT(ingredient));
        }

        return ingredientsUDT;
    }
}
