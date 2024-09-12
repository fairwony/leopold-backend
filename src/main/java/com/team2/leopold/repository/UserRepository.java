package com.team2.leopold.repository;

import com.team2.leopold.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findById(String id);
}