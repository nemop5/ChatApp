package nemop.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import nemop.model.Channel;
import nemop.model.User;
import nemop.service.ChannelService;
import nemop.service.UserService;
import nemop.web.dto.ChannelDTO;

@Component
public class ChannelDtoToChannel implements Converter<ChannelDTO, Channel>{

	@Autowired
	private ChannelService channelService;
	
	@Autowired
	private UserService userService;
	
    @Override
    public Channel convert(ChannelDTO channelDto) {
		
		User createdByUser = null;
		if(channelDto.getCreatedByUserId() != null) {
			createdByUser = userService.findOne(channelDto.getCreatedByUserId()).get();
		}
		
		
		if(createdByUser!=null) {
			Long id = channelDto.getId();
			Channel channel = id == null ? new Channel() : channelService.findOne(id).get();

			if(channel != null) {
				channel.setId(channelDto.getId());
				channel.setName(channelDto.getName());
				channel.setDetails(channelDto.getDetails());
			}
		
			return channel;
		
		}else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
    }
        
}
