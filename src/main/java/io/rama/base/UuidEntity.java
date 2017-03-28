package io.rama.base;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;


/**
 * Base class for any <code>Entity</code> having a UUID as an <code>Id</code>
 */
@Data
@MappedSuperclass
public abstract class UuidEntity {
  @Id
  private String id = UUID.randomUUID().toString();

  private void setId(String id) {
    // Disallow manual setting of id
  }
}
