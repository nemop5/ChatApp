package nemop.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import nemop.model.Channel;
import nemop.model.Message;
import nemop.model.User;
import nemop.service.ChannelService;
import nemop.service.MessageService;
import nemop.service.UserService;
import nemop.web.dto.MessageDTO;

@Component
public class MessageDtoToMessage implements Converter<MessageDTO, Message> {

    @Autowired
    private MessageService messageService;
    
    @Autowired
    private ChannelService channelService;
    
    @Autowired
    private UserService userService;
    
    @Override
    public Message convert(MessageDTO messageDto) {
		
		Channel channel = null;
		if(messageDto.getChannelId() != null) {
			channel = channelService.findOne(messageDto.getChannelId()).get();
		}
		
		User user = null;
		if(messageDto.getUserId() != null) {
			user = userService.findOne(messageDto.getUserId()).get();
		}
		
		if(channel!=null && user!=null) {
			Long id = messageDto.getId();
			Message message = id == null ? new Message() : messageService.findOne(id).get();

			if(message != null) {
				message.setId(messageDto.getId());
				message.setContent(messageDto.getContent());
				message.setTimeStamp(messageDto.getTimeStamp());
				
				message.setChannel(channel);
				message.setUser(user);
			}
		
			return message;
		
		}else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
    }
    
}
