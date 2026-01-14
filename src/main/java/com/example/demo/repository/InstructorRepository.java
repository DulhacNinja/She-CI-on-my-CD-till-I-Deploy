package com.example.demo.repository;

import com.example.demo.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Instructor i SET i.email = :email WHERE i.id = :id")
    int updateEmailById(@Param("id") Long id, @Param("email") String email);

    Optional<Instructor> findByEmail(String email);
}
