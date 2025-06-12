package com.budgetupi.upiplatform.Repository;

import com.budgetupi.upiplatform.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByPhone(String phone);
    Optional<User> findByUpiId(String upiId);
}
