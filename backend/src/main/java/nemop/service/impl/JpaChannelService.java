package nemop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nemop.model.Channel;
import nemop.repository.ChannelRepository;
import nemop.service.ChannelService;

@Service
public class JpaChannelService implements ChannelService{

	@Autowired
	private ChannelRepository channelRepository;
	
	@Override
	public Optional<Channel> findOne(Long id) {
		return channelRepository.findById(id);
	}

	@Override
	public List<Channel> findAll() {
		return channelRepository.findAll();
	}

	@Override
	public Channel save(Channel channel) {
		return channelRepository.save(channel);
	}

	@Override
	public void delete(Long id) {
		channelRepository.deleteById(id);
		
	}

	@Override
	public Optional<Channel> findByName(String name) {
		return channelRepository.findByName(name);
	}

}
