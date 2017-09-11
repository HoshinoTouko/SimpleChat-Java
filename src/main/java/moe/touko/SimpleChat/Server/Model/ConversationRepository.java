package moe.touko.SimpleChat.Server.Model;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "Conversation", path = "Conversation")
public class ConversationRepository {
    public interface PersonRepository extends PagingAndSortingRepository<Conversation, Long> {

        List<Conversation> findByText(@Param("text") String text);

    }

}
