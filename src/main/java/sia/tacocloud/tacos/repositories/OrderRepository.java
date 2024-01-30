package sia.tacocloud.tacos.repositories;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.entities.TacoOrder;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<TacoOrder, UUID> {
}
