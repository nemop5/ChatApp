package nemop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nemop.model.Message;
import nemop.repository.MessageRepository;
import nemop.service.MessageService;

@Service
public class JpaMessageService implements MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	@Override
	public Optional<Message> findOne(Long id) {
		return messageRepository.findById(id);
	}

	@Override
	public List<Message> findAll() {
		return messageRepository.findAll();
	}

	@Override
	public Message save(Message message) {
		return messageRepository.save(message);
	}

	@Override
	public void delete(Long id) {
		messageRepository.deleteById(id);
		
	}

	@Override
	public Optional<Message> findByContent(String content) {
		return messageRepository.findByContent(content);
	}

}
