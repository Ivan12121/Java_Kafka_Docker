package com.bakaev.repositories;

import com.bakaev.models.SchoolSubjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolSubjectRepository extends JpaRepository<SchoolSubjects, Long> {

}
