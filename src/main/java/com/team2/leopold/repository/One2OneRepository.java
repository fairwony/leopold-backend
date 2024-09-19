package com.team2.leopold.repository;

import com.team2.leopold.entity.One2One;
import com.team2.leopold.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface One2OneRepository extends JpaRepository<One2One, Integer> {
}