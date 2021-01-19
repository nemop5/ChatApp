package nemop.service;

import nemop.model.User;
import nemop.web.dto.UserChangePasswordDto;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findOne(Long id);

    List<User> findAll();

    Page<User> findAll(int numOfPage);

    User save(User user);

    void delete(Long id);

    Optional<User> findByUsername(String username);

    boolean changePassword(Long id, UserChangePasswordDto userChangePasswordDto);
}
