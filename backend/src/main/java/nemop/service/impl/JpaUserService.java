package nemop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import nemop.enumeration.UserRole;
import nemop.model.User;
import nemop.repository.UserRepository;
import nemop.service.UserService;
import nemop.web.dto.UserChangePasswordDto;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class JpaUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(int numOfPage) {
        return userRepository.findAll(PageRequest.of(numOfPage,10));
    }

    @Override
    public User save(User user) {
    	user.setRole(UserRole.USER);
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
    	userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findFirstByUsername(username);
    }

    @Override
    public boolean changePassword(Long id, UserChangePasswordDto userChangePasswordDto) {
        Optional<User> result = userRepository.findById(id);

        if(!result.isPresent()) {
            throw new EntityNotFoundException();
        }

        User user = result.get();

        if(!user.getUsername().equals(userChangePasswordDto.getUsername())
                || !user.getPassword().equals(userChangePasswordDto.getPassword())){
            return false;
        }

        String password = userChangePasswordDto.getPassword();
        if (!userChangePasswordDto.getPassword().equals("")) {
            password = passwordEncoder.encode(userChangePasswordDto.getPassword());
        }

        user.setPassword(password);

        userRepository.save(user);

        return true;
    }
}
