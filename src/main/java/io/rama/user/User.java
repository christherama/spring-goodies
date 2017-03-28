package io.rama.user;

import io.rama.base.UuidEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


/**
 * Encapsulates all data related to a single user
 */
@Data
@Entity
@Table(uniqueConstraints = {
  @UniqueConstraint(columnNames = {"orgId","userId"})
})
public class User extends UuidEntity {
  private String orgId;
  private String userId;

  public User() {}

  private User(String orgId, String userId) {
    this.orgId = orgId;
    this.userId = userId;
  }

  public static UserBuilder builder() {
    return new UserBuilder();
  }

  /**
   * For fun, let's create our own builder! We could instead annotate
   * the <code>User</code> class with the Lombok <code>@Builder</code>
   * annotation.
   */
  public static class UserBuilder {
    private String orgId;
    private String userId;

    public UserBuilder orgId(String orgId) {
      this.orgId = orgId;
      return this;
    }

    public UserBuilder userId(String userId) {
      this.userId = userId;
      return this;
    }

    public User build() {
      return new User(orgId,userId);
    }
  }
}
