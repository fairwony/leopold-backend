package com.team2.leopold.repository;

import com.team2.leopold.entity.One2One;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface One2OneRepository extends JpaRepository<One2One, Integer> {
    @Query("SELECT o FROM One2One o WHERE deleteYn = 'n'")
    Page<One2One> findOne2Ones(Pageable pageable);
}