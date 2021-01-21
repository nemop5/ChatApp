package nemop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nemop.model.Channel;
import nemop.repository.ChannelRepository;
import nemop.service.ChannelService;
import nemop.support.ChannelDtoToChannel;
import nemop.web.dto.ChannelDTO;

@Service
public class JpaChannelService implements ChannelService{

	@Autowired
	private ChannelRepository channelRepository;
	
	@Autowired
	private ChannelDtoToChannel toChannel;
	
	
	@Override
	public Optional<Channel> findOne(Long id) {
		return channelRepository.findById(id);
	}

	@Override
	public List<Channel> findAll() {
		return channelRepository.findAll();
	}

	@Override
	public Channel save(ChannelDTO channelDto) {
		Channel channel = toChannel.convert(channelDto);
		Channel savedChannel = channelRepository.save(channel);
		return savedChannel;
	}

	@Override
	public Channel delete(Long id) {
		Optional<Channel> channelOptional = channelRepository.findById(id);
		if(channelOptional.isPresent()) {
			Channel channel = channelOptional.get();
			channelRepository.deleteById(id);
			return channel;
		}
		
		return null;
	}

	@Override
	public Optional<Channel> findByName(String name) {
		return channelRepository.findByName(name);
	}

}
