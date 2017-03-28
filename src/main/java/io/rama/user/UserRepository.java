package io.rama.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  /**
   * Retrieves a user by its org id and user id
   *
   * @param orgId  Identifier of org, to which the specified user belongs
   * @param userId Identifier of user, which is unique only within an org
   * @return User wrapped in an Optional, if one exists, otherwise an empty Optional
   */
  Optional<User> findByOrgIdAndUserId(String orgId, String userId);
}
