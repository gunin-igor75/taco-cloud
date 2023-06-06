package ru.gil.tacocloud.dao.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gil.tacocloud.dao.OrderRepository;
import ru.gil.tacocloud.model.Ingredient;
import ru.gil.tacocloud.model.Taco;
import ru.gil.tacocloud.model.TacoOrder;

import java.sql.Types;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class JdbcOrderRepository implements OrderRepository {

    private final JdbcOperations jdbcOperations;

    @Override
    @Transactional
    public TacoOrder save(TacoOrder order) {
        PreparedStatementCreatorFactory factory =
                new PreparedStatementCreatorFactory(
                        "insert into taco_order"
                                + "(delivery_name, delivery_street, delivery_city"
                                + "delivery_state, delivery_zip, cc_number,  cc_expiration"
                                + "cc_cVV, placed_at)"
                                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                        Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
                );

        factory.setReturnGeneratedKeys(true);
        order.setPlacedAt(LocalDate.now());
        PreparedStatementCreator creator = factory.newPreparedStatementCreator(
                Arrays.asList(
                        order.getDeliveryName(),
                        order.getDeliveryStreet(),
                        order.getDeliveryCity(),
                        order.getDeliveryState(),
                        order.getDeliveryZip(),
                        order.getCcNumber(),
                        order.getCcExpiration(),
                        order.getCcCVV(),
                        order.getPlacedAt()
                )
        );
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(creator, keyHolder);
        long orderId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        order.setId(orderId);
        List<Taco> tacos = order.getTacos();
        int i = 0;
        for (Taco taco : tacos) {
            saveTaco(orderId, i++, taco);
        }
        return order;
    }

    private long saveTaco(long orderId, int orderKey, Taco taco) {
        PreparedStatementCreatorFactory factory =
                new PreparedStatementCreatorFactory(
                        "insert into taco"
                                + "(name, created_at, taco_order, taco_order_key)"
                                + "values (?, ?, ?, ?)",
                        Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
                );
        factory.setReturnGeneratedKeys(true);
        PreparedStatementCreator creator =
                factory.newPreparedStatementCreator(
                        Arrays.asList(
                                taco.getName(),
                                taco.getCreateAt(),
                                orderId,
                                orderKey
                        )
                );
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(creator, keyHolder);
        long tacoId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        taco.setId(tacoId);
        saveIngredientRefs(tacoId, taco.getIngredients());
        return tacoId;
    }

    private void saveIngredientRefs(long tacoId, List<Ingredient> ingredients) {
        int key = 0;
        for (Ingredient ingredient : ingredients) {
            jdbcOperations.update(
                    "insert into ingredient_ref"
                    + "(ingredient, taco, taco_key)"
                    + "values (?, ?, ?)",
                    ingredient.getId(), tacoId, key++
            );
        }
    }
}
