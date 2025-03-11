package cc.whyy0u.repository.user;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import cc.whyy0u.entity.user.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

      Optional<UserEntity> findBylogin(String login);

} 
