package nemop.service;

import java.util.List;
import java.util.Optional;

import nemop.model.Message;

public interface MessageService {

    Optional<Message> findOne(Long id);

    List<Message> findAll();

    Message save(Message message);

    void delete(Long id);

    Optional<Message> findByContent(String content);
}
