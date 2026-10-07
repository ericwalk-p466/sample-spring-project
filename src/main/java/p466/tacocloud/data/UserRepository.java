package p466.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import p466.tacocloud.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
