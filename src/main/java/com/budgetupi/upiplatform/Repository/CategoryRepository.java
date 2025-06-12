package com.budgetupi.upiplatform.Repository;

import com.budgetupi.upiplatform.Model.Category;
import com.budgetupi.upiplatform.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByNameAndUser(String name, User user);
    boolean existsByNameAndUser(String name, User user);
}

