package com.example.accessingdatarest.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.example.accessingdatarest.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {

}