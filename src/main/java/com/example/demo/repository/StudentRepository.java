package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Custom query methods (Spring Data will generate them automatically)
    Optional<Student> findByCode(String code);

    Optional<Student> findByEmail(String email);

    @Query("SELECT s FROM Student s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :namePart, '%'))")
    List<Student> searchByName(@Param("namePart") String namePart);

    @Transactional
    @Modifying
    @Query("UPDATE Student s SET s.year = :year WHERE s.id = :id")
    int updateYearById(@Param("id") Long id, @Param("year") Integer year);

    Optional<Student> findByUsername(String username);
    Optional<Student> findById(Long id);
}
