package sia.tacocloud.tacos.entities.udt;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import sia.tacocloud.tacos.entities.Ingredient;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@UserDefinedType("ingredient")
public class IngredientUDT {
    private final String name;
    private final Ingredient.Type type;
}
