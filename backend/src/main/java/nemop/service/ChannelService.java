package nemop.service;

import java.util.List;
import java.util.Optional;

import nemop.model.Channel;
import nemop.web.dto.ChannelDTO;

public interface ChannelService {

    Optional<Channel> findOne(Long id);

    List<Channel> findAll();

    Channel save(ChannelDTO channelDto);

    Channel delete(Long id);

    Optional<Channel> findByName(String name);

}
