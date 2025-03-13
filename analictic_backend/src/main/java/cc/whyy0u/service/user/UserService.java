package cc.whyy0u.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import cc.whyy0u.entity.user.UserEntity;
import cc.whyy0u.repository.user.UserRepository;

@Service
public
class UserService {

   @Autowired
   UserRepository userRepository;

    public UserEntity findByLogin(String login) {
      return userRepository.findBylogin(login).orElse(null);
    }

    public void save(UserEntity entity) {
      userRepository.save(entity);
    }
    public void delete(Long id) {
      userRepository.deleteById (id);
    }

      public UserEntity getCurrentUser() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        return findByLogin(login);
    }
    
}
