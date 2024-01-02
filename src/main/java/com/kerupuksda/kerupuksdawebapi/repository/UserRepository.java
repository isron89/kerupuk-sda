package com.kerupuksda.kerupuksdawebapi.repository;

import com.kerupuksda.kerupuksdawebapi.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    @Query(value = "SELECT * FROM USERS U JOIN USER_ROLE UR ON U.ID = UR.USER_ID JOIN ROLES R ON R.ID = UR.ROLE_ID " +
            "WHERE U.ID = :userId AND U.IS_DELETED = :isDeleted", nativeQuery = true)
    Optional<User> getUserById(@Param("userId") String userId,
                               @Param("isDeleted") Boolean isDeleted);

}
