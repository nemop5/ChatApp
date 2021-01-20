package nemop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import nemop.model.Message;
import nemop.web.dto.MessageDTO;

@Component
public class MessageToMessageDto implements Converter<Message, MessageDTO> {

    @Override
    public MessageDTO convert(Message message) {
    	
        MessageDTO messageDto = new MessageDTO();
        
        messageDto.setId(message.getId());
        messageDto.setContent(message.getContent());
        messageDto.setTimeStamp(message.getTimeStamp());

        messageDto.setChannelId(message.getChannel().getId());
        messageDto.setChannelName(message.getChannel().getName());
        
        messageDto.setUserId(message.getUser().getId());
        messageDto.setUserUsername(message.getUser().getUsername());
        
        return messageDto;
    }

    public List<MessageDTO> convert(List<Message> messages){
        List<MessageDTO> messagesDto = new ArrayList<>();

        for(Message m : messages) {
        	messagesDto.add(convert(m));
        }

        return messagesDto;
    }

}
