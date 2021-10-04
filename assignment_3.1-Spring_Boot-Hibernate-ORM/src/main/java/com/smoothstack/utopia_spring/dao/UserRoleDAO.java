package com.smoothstack.utopia_spring.dao;

import com.smoothstack.utopia_spring.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDAO extends JpaRepository<UserRole, Integer> {

    UserRole getByRoleName(String role_name);

    void deleteUserRoleByName(String role_name);
}
