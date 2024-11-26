package tacos1.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import tacos1.entity.Taco;

import java.util.List;

public interface TacoRepository extends CrudRepository<Taco, Long> {
    Page<Taco> findAll(Pageable pr);
}
