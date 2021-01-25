package nemop.support;
import nemop.model.User;
import nemop.web.dto.UserDTO;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserToUserDto implements Converter<User, UserDTO>{

    @Override
    public UserDTO convert(User user) {
        UserDTO userDto = new UserDTO();

        userDto.setId(user.getId());
        userDto.seteMail(user.geteMail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());

        return userDto;
    }

    public List<UserDTO> convert(List<User> users){
        List<UserDTO> usersDto = new ArrayList<>();

        for(User u : users) {
            UserDTO userDto = convert(u);
            usersDto.add(userDto);
        }

        return usersDto;
    }
}
