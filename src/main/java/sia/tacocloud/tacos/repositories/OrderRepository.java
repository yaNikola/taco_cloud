package sia.tacocloud.tacos.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.entities.TacoOrder;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    List<TacoOrder> findByDeliveryZip(String deliveryZip);
    List<TacoOrder> readByDeliveryZipAndPlacedAtBetween(String deliveryZip,
                                                        Date startDate, Date endDate);
    List<TacoOrder> findByDeliveryCityAndDeliveryStreetIgnoreCase(String deliveryCity,
                                                                       String deliveryStreet);
    List<TacoOrder> findByDeliveryStreetOrderByDeliveryName(String deliveryStreet);

    @Query("SELECT order FROM TacoOrder order WHERE order.deliveryCity = 'Seattle'")
    List<TacoOrder> findOrderDeliveredInSeattle();


}
