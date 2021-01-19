package nemop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nemop.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	Optional<Message> findByContent(String content);

}
