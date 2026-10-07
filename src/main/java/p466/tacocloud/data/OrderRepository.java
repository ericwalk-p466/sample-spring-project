package p466.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import p466.tacocloud.TacoOrder;

import java.util.List;
public interface OrderRepository extends CrudRepository<TacoOrder, Long>{
    List<TacoOrder> findByDeliveryZip(String deliveryZip);
}
