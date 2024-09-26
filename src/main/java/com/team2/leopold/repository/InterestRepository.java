package com.team2.leopold.repository;

import com.team2.leopold.entity.Interest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InterestRepository extends JpaRepository<Interest, Integer> {
    @Query("SELECT i FROM Interest i JOIN FETCH i.user WHERE i.user.uid = :userUid")
    Page<Interest> findInterestListByUserUid(@Param("userUid") Integer userUid, Pageable pageable);
}
