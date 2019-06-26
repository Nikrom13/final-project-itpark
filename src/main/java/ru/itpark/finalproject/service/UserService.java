package ru.itpark.finalproject.service;


import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.finalproject.domain.Registration;
import ru.itpark.finalproject.domain.User;
import ru.itpark.finalproject.repository.UserRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      return repository.findByUsername(username);
    } catch (EmptyResultDataAccessException e) {
      throw new UsernameNotFoundException("Username not found");
    }
  }

  public void register(Registration data) {
    if (!data.getPassword().equals(data.getConfirm())) {
      throw new RuntimeException("Пароли не совпадают");
    }

    repository.save(new User(
            0,
            data.getUsername(),
            passwordEncoder.encode(data.getPassword()),
            List.of(new SimpleGrantedAuthority("ROLE_USER")),
            true,
            true,
            true,
            true
    ));
  }
}
