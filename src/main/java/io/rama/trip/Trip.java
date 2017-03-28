package io.rama.trip;

import io.rama.base.UuidEntity;
import io.rama.user.User;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;


/**
 * Encapsulates all data related to a single trip
 */
@Data
@Entity
public class Trip extends UuidEntity {
  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;
  private BigDecimal distance;
  private Long duration;
}
