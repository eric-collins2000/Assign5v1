package com.example.accessingdatarest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
/*    @Override
    Optional<Person> findById(Integer integer);

    Person findByFirstNameIgnoreCase(@NonNull String firstName);

    Person findByLastNameIgnoreCase(@NonNull String lastName);*/
}