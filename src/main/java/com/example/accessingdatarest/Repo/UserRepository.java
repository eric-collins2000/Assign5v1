package com.example.accessingdatarest.Repo;

import java.util.List;
import java.util.Optional;

import com.example.accessingdatarest.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.lang.NonNull;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	//List<User> findByLogin(@Param("login") String login);

/*	@Override
	Optional<User> findById(Long aLong);

	@Query("select u from User u where upper(u.login) = upper(?1)")
	List<User> findByLoginIgnoreCase(@NonNull String login);

	@Query("select count(u) from User u where u.login = ?1")
	long countByLogin(@NonNull String login);*/
}
