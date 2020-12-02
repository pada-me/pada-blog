package me.pada.blog.oauth.service.impl;

import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.pada.blog.oauth.repository.UserRepository;
import me.pada.blog.oauth.service.UserService;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
    log.debug("Authenticating {}", login);

    if (new EmailValidator().isValid(lowercaseLogin, null)) {
      return userRepository.findByEmail(login).orElseThrow(
          () -> new UsernameNotFoundException("User with email " + lowercaseLogin + " was not found in the database.")
      );
    }
    return userRepository.findByUsername(login).orElseThrow(
        () -> new UsernameNotFoundException("User with username " + lowercaseLogin + " was not found in the database.")
    );
  }
}
