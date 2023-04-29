package com.bakaev.repositories;

import com.bakaev.models.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {
    Students findStudentById(Long id);
    List<Students> findAllStudentsByClazz(int clazz);
}
