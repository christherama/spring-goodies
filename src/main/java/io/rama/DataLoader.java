package io.rama;

import io.rama.user.User;
import io.rama.user.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class DataLoader implements ApplicationRunner {
  private final UserRepository users;

  public DataLoader(UserRepository users) {
    this.users = users;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    User user = User.builder()
        .orgId("my-org")
        .userId("rama")
        .build();
    users.save(user);
  }
}
