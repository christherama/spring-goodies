package io.rama.trip;

import io.rama.user.User;
import io.rama.web.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 * Handles all trip-related requests
 */
@Slf4j
@RestController
@RequestMapping(path = "/users/{id}/trips")
public class TripController {
  private final TripRespository trips;

  public TripController(TripRespository trips) {
    this.trips = trips;
  }

  /**
   * Handles request for all a user's trips
   *
   * @param user User whose trip's are requested.
   *             See {@link io.rama.web.request.UserHandlerMethodArgumentResolver}
   *             for how the <code>User argument</code> is resolved.
   * @return Response containing list of trips
   */
  @RequestMapping(method = RequestMethod.GET)
  public Response getUserTrips(User user) {
    return Response.builder().data(trips.findByUser(user)).build();
  }

  /**
   * Handles request for saving a new trip
   *
   * @param trip Trip to save
   * @param user User who took the trip. See
   *             {@link io.rama.web.request.UserHandlerMethodArgumentResolver}
   *             for how the <code>User argument</code> is resolved.
   * @return Response containing trip that was created.
   */
  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public Response saveTrip(@RequestBody Trip trip, User user) {
    trip.setUser(user);
    return Response.builder().data(trips.save(trip)).build();
  }
}
