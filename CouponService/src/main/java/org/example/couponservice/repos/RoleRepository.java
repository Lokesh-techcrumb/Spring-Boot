package org.example.couponservice.repos;

import org.example.couponservice.model.Role;
import org.example.couponservice.model.User;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    User findByEmail(String email);
}
