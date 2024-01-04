package sia.tacocloud.tacos.repositories.impl;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sia.tacocloud.tacos.entities.Ingredient;
import sia.tacocloud.tacos.entities.Taco;
import sia.tacocloud.tacos.entities.TacoOrder;
import sia.tacocloud.tacos.repositories.OrderRepository;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private final JdbcOperations jdbcOperations;

    public JdbcOrderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    @Transactional
    public TacoOrder save(TacoOrder tacoOrder) {
        PreparedStatementCreatorFactory psfc = new PreparedStatementCreatorFactory(
                "insert into Taco_Order " +
                        "(delivery_name, delivery_street, delivery_city," +
                        "delivery_state, delivery_zip, cc_number, cc_expiration," +
                        "cc_cvv, placed_at) " +
                        "values(?,?,?,?,?,?,?,?,?)",
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.TIMESTAMP
        );
        psfc.setReturnGeneratedKeys(true);

        tacoOrder.setPlacedAt(new Date());
        PreparedStatementCreator psc = psfc.newPreparedStatementCreator(
                Arrays.asList(
                        tacoOrder.getDeliveryName(),
                        tacoOrder.getDeliveryStreet(),
                        tacoOrder.getDeliveryCity(),
                        tacoOrder.getDeliveryState(),
                        tacoOrder.getDeliveryZip(),
                        tacoOrder.getCcNumber(),
                        tacoOrder.getCcExpiration(),
                        tacoOrder.getCcCVV(),
                        tacoOrder.getPlacedAt()
                )
        );

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long orderId = keyHolder.getKey().longValue();
        tacoOrder.setId(orderId);

        int i = 0;
        for (var taco : tacoOrder.getTacos()) {
            saveTaco(orderId, i++, taco);
        }

        return tacoOrder;
    }

    private long saveTaco(long orderId, int key, Taco taco) {
        var pscf = new PreparedStatementCreatorFactory(
                "insert into Taco " +
                        "(name, taco_order, taco_order_key, created_at) " +
                        "values ( ?, ?, ?, ? )"
        );
        pscf.setReturnGeneratedKeys(true);

        taco.setCreatedAt(new Date());
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        taco.getName(),
                        orderId,
                        key,
                        taco.getCreatedAt()
                )
        );

        var keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long tacoId = keyHolder.getKey().longValue();
        taco.setId(tacoId);

        saveIngredientRefs(tacoId, taco.getIngredients());

        return tacoId;
    }

    private void saveIngredientRefs(long tacoId, List<Ingredient> ingredients) {
        long key = 0;
        for (var ingredient : ingredients) {
            jdbcOperations.update(
                    "insert into Ingredient_Ref " +
                            "(ingredient, taco, taco_key) " +
                            "values(?,?,?)",
                    ingredient.getId(), tacoId, key++
            );
        }
    }


}
