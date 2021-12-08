package assign.Repo;

import assign.Models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	User findByLogin(@Param("login") String login);

	@Query("select u from User u where upper(u.login) = upper(?1)")
	List<User> findByLoginIgnoreCase(@NonNull String login);

	@Query("select count(u) from User u where u.login = ?1")
	long countByLogin(@NonNull String login);
}
