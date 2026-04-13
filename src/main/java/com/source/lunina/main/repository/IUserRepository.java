package com.source.lunina.main.repository;

import com.source.lunina.common.repository.AbstractCrudRepository;
import com.source.lunina.main.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends AbstractCrudRepository<Users, Long> {
    @Query("""
            SELECT u
            FROM Users u
            WHERE u.email = :email
            AND u.deleted = false
            """)
    Optional<Users> findByEmail(String email);
}
