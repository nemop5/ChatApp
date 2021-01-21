package nemop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nemop.model.Message;
import nemop.repository.MessageRepository;
import nemop.service.MessageService;
import nemop.support.MessageDtoToMessage;
import nemop.web.dto.MessageDTO;

@Service
public class JpaMessageService implements MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private MessageDtoToMessage toMessage;
	
	@Override
	public Optional<Message> findOne(Long id) {
		return messageRepository.findById(id);
	}

	@Override
	public List<Message> findAll() {
		return messageRepository.findAll();
	}

	@Override
	public Message save(MessageDTO messageDto) {
		Message message = toMessage.convert(messageDto);
		Message savedMessage = messageRepository.save(message);
		return savedMessage;
	}

	@Override
	public Message delete(Long id) {
		Optional<Message> messageOptional = messageRepository.findById(id);
		if(messageOptional.isPresent()) {
			Message message = messageOptional.get();
			messageRepository.deleteById(id);
			return message;
		}
		
		return null;
		
	}

	@Override
	public Optional<Message> findByContent(String content) {
		return messageRepository.findByContent(content);
	}

}
