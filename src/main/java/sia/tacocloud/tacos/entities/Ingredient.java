package sia.tacocloud.tacos.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class Ingredient{
    @Id
    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
            WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
