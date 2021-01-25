package nemop.support;

import nemop.model.User;
import nemop.service.UserService;
import nemop.web.dto.UserDTO;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class UserDtoToUser implements Converter<UserDTO, User> {

    @Autowired
    private UserService userService;

    @Override
    public User convert(UserDTO userDto) {
        User user = null;
        if(userDto.getId() != null) {
            user = userService.findOne(userDto.getId()).get();
        }

        if(user == null) {
        	user = new User();
        }

        user.setUsername(userDto.getUsername());
        user.seteMail(userDto.geteMail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        return user;
    }

}
