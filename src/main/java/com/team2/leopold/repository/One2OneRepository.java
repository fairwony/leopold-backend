package com.team2.leopold.repository;

import com.team2.leopold.entity.One2One;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface One2OneRepository extends JpaRepository<One2One, Integer> {
    Page<One2One> insertPosts(One2One one2One);
}