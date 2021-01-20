package nemop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import nemop.model.Channel;
import nemop.web.dto.ChannelDTO;

@Component
public class ChannelToChannelDto implements Converter<Channel, ChannelDTO> {

    @Override
    public ChannelDTO convert(Channel channel) {
    	
        ChannelDTO channelDTO = new ChannelDTO();
        
        channelDTO.setId(channel.getId());
        channelDTO.setName(channel.getName());
        channelDTO.setDetails(channel.getDetails());

        channelDTO.setCreatedByUserId(channel.getCreatedByUser().getId());
        channelDTO.setCreatedByUsername(channel.getCreatedByUser().getUsername());
        
        channelDTO.setStarredByUserId(channel.getStarredByUser().getId());
        channelDTO.setStarredByUsername(channel.getStarredByUser().getUsername());
        
        return channelDTO;
    }

    public List<ChannelDTO> convert(List<Channel> channels){
        List<ChannelDTO> channelsDto = new ArrayList<>();

        for(Channel channel : channels) {
        	channelsDto.add(convert(channel));
        }

        return channelsDto;
    }

}
