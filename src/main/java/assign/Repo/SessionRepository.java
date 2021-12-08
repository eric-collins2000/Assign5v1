package assign.Repo;

import assign.Models.Session;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "session", path = "session")
public interface SessionRepository extends PagingAndSortingRepository<Session, String> {
    Session findByToken(@NonNull String token);

}