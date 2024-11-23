package tacos1.Repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import tacos1.entity.TacoOrder;
import tacos1.entity.User;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    List<TacoOrder> findByUserOrderByPlacedAtDesc(
            User user, Pageable pageable);
}
