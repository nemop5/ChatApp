package nemop.service;

import java.util.List;
import java.util.Optional;

import nemop.model.Channel;

public interface ChannelService {

    Optional<Channel> findOne(Long id);

    List<Channel> findAll();

    Channel save(Channel channel);

    void delete(Long id);

    Optional<Channel> findByName(String name);

}
