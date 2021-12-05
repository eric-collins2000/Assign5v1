package com.example.Main.Repo;

import com.example.Main.Models.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
/*    @Override
    Optional<Person> findById(Integer integer);

    Person findByFirstNameIgnoreCase(@NonNull String firstName);

    Person findByLastNameIgnoreCase(@NonNull String lastName);*/
}