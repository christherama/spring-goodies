package io.rama.trip;

import io.rama.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Repository for querying trips
 */
@Repository
public interface TripRespository extends PagingAndSortingRepository<Trip, String> {
  /**
   * Finds all trips of a specified user
   *
   * @param user User whose trips to retrieve
   * @return List of trips for the specified user. The list will be empty if the
   * specified user has no trips.
   */
  @Query("SELECT t FROM Trip t WHERE t.user.orgId = :#{#user.orgId} AND t.user.userId = :#{#user.userId}")
  List<Trip> findByUser(@Param("user") User user);
}
