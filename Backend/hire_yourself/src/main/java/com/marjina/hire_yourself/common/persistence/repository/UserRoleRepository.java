package com.marjina.hire_yourself.common.persistence.repository;

import com.marjina.hire_yourself.common.persistence.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole , Integer> {

    UserRole findByUser_Id(Integer userId);

}
