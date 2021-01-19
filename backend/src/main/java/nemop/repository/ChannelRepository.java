package nemop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nemop.model.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

	Optional<Channel> findByName(String name);

}
