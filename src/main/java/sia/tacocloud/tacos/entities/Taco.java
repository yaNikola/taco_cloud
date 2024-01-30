package sia.tacocloud.tacos.entities;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import sia.tacocloud.tacos.entities.udt.IngredientUDT;
import sia.tacocloud.tacos.utils.TacoUDRUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Table("tacos")
public class Taco {

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private UUID id = Uuids.timeBased();

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED,
            ordering = Ordering.DESCENDING)
    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5, message = "The name must be at least 5 characters long")
    private String name;

    @Size(min = 1, message = "You must choose at least 1 ingredient")
    @Column("ingredients")
    private List<IngredientUDT> ingredients;

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(TacoUDRUtils.toIngredientUDT(ingredient));
    }
}
