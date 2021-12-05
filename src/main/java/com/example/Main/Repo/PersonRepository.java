package com.example.Main.Repo;

import com.example.Main.Models.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.lang.NonNull;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Integer> {

    //Optional<Person> findById(Integer integer);

    Person findByFirstNameIgnoreCase(@NonNull String firstName);

    Person findByLastNameIgnoreCase(@NonNull String lastName);

    Person findByIdEquals(@NonNull Integer id);


    @Override
    boolean existsById(Integer integer);

    @Override
    void deleteById(Integer integer);
}