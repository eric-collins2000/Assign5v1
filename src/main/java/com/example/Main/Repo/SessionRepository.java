package com.example.Main.Repo;

import com.example.Main.Models.Session;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.lang.NonNull;


@RepositoryRestResource(collectionResourceRel = "session", path = "session")
public interface SessionRepository extends PagingAndSortingRepository<Session, String> {
    Session findByToken(@NonNull String token);

}