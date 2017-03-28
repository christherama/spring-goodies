package io.rama.user;


/**
 * Encapsulates info related to searching for a user
 */
public class UserNotFoundException extends RuntimeException {
  private String userId;
  public UserNotFoundException(String userId) {
    this.userId = userId;
  }

  @Override
  public String getMessage() {
    return String.format("User with id '%s' not found", userId);
  }
}
