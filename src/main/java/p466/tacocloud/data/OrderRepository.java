package p466.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import p466.tacocloud.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long>{
}
