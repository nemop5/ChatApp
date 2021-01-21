package nemop.service;

import java.util.List;
import java.util.Optional;

import nemop.model.Message;
import nemop.web.dto.MessageDTO;

public interface MessageService {

    Optional<Message> findOne(Long id);

    List<Message> findAll();

    Message save(MessageDTO messageDto);

    Message delete(Long id);

    Optional<Message> findByContent(String content);
}
