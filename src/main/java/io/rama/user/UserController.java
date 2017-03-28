package io.rama.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 * Handles all user-related requests
 */
@RestController
@RequestMapping("/users")
public class UserController {
  private final UserRepository users;

  public UserController(UserRepository users) {
    this.users = users;
  }

  /**
   * Handles a request for all users
   *
   * @return Serialized list of users
   */
  @RequestMapping(method = RequestMethod.GET)
  public Iterable<User> getAllUsers() {
    return users.findAll();
  }

  /**
   * Handles a request for saving a new user
   *
   * @param user User to save
   * @return Serialized user that was created
   */
  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public User saveUser(@RequestBody User user) {
    return users.save(user);
  }
}
