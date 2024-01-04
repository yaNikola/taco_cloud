package sia.tacocloud.tacos.repositories;

import sia.tacocloud.tacos.entities.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder tacoOrder);
}
