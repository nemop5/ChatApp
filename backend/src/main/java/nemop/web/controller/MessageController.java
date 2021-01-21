package nemop.web.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import nemop.model.Message;
import nemop.service.MessageService;
import nemop.support.MessageToMessageDto;
import nemop.web.dto.MessageDTO;

@RestController
@RequestMapping(value = "/api/messages", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private MessageToMessageDto toMessageDto;
	

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<MessageDTO>> getAll() {

        List<Message> messages = messageService.findAll();
        
        return new ResponseEntity<>(toMessageDto.convert(messages), HttpStatus.OK);
    }
    
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    @GetMapping("/{id}")
//    public ResponseEntity<ChannelDTO> getOne(@PathVariable Long id){
//		Optional<Channel> channel = channelService.findOne(id);
//		if (!channel.isPresent()) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//
//		return new ResponseEntity<>(toChannelDto.convert(channel.get()), HttpStatus.OK);
//	}
    
    @PreAuthorize("hasRole('USER', 'ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageDTO> create(@Valid @RequestBody MessageDTO messageDto){		

        Message savedMessage = messageService.save(messageDto);

        return new ResponseEntity<>(toMessageDto.convert(savedMessage), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageDTO> update(@PathVariable Long id, @Valid @RequestBody MessageDTO messageDto){

        if(!id.equals(messageDto.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Message savedMessage = messageService.save(messageDto);

        return new ResponseEntity<>(toMessageDto.convert(savedMessage),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO> delete(@PathVariable Long id){
    	
    	Message deletedMessage = messageService.delete(id);

        if(deletedMessage == null) {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(toMessageDto.convert(deletedMessage),HttpStatus.OK);
    }
    

}
