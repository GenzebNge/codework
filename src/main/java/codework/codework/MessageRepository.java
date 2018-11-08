package codework.codework;

import org.springframework.data.repository.CrudRepository;

import javax.jws.soap.SOAPBinding;

public interface MessageRepository extends CrudRepository<Message, Long> {

//    Message findByContent(String content);
//    Long countByContent(User sender);
//    Message findByDate(String date);
//    Long CountByDate(long date);

}
