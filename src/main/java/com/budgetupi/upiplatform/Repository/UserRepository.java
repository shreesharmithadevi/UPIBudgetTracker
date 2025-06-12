package com.budgetupi.upiplatform.Repository;

import com.budgetupi.upiplatform.Model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @EntityGraph(attributePaths = "categories")
    Optional<User> findByUpiId(String upiId);

    Optional<User> findByPhone(String phone);
}
