package com.marjina.hire_yourself.common.persistence.repository;

import com.marjina.hire_yourself.common.persistence.models.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education,Integer> {

}
