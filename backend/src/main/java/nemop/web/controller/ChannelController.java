package nemop.web.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nemop.model.Channel;
import nemop.service.ChannelService;
import nemop.service.UserService;
import nemop.support.ChannelToChannelDto;
import nemop.web.dto.ChannelDTO;

@RestController
@RequestMapping(value = "/api/channels", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class ChannelController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ChannelService channelService;
	
	@Autowired
	private ChannelToChannelDto toChannelDto;
	

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<ChannelDTO>> getAll() {

        List<Channel> channels = channelService.findAll();
        
        return new ResponseEntity<>(toChannelDto.convert(channels), HttpStatus.OK);
    }
    
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ChannelDTO> getOne(@PathVariable Long id){
		Optional<Channel> channel = channelService.findOne(id);
		if (!channel.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toChannelDto.convert(channel.get()), HttpStatus.OK);
	}
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChannelDTO> create(@Valid @RequestBody ChannelDTO channelDto){		

        Channel savedChannel = channelService.save(channelDto);

        return new ResponseEntity<>(toChannelDto.convert(savedChannel), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChannelDTO> update(@PathVariable Long id, @Valid @RequestBody ChannelDTO channelDto){

        if(!id.equals(channelDto.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Channel savedChannel = channelService.save(channelDto);

        return new ResponseEntity<>(toChannelDto.convert(savedChannel),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ChannelDTO> delete(@PathVariable Long id){
    	
    	Channel deletedChannel = channelService.delete(id);

        if(deletedChannel == null) {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(toChannelDto.convert(deletedChannel),HttpStatus.OK);
    }
    

}
