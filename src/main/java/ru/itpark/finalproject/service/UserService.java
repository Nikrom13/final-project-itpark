package ru.itpark.finalproject.service;


import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.finalproject.domain.Registration;
import ru.itpark.finalproject.domain.User;
import ru.itpark.finalproject.dto.UserInfo;
import ru.itpark.finalproject.repository.UserRepository;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final NamedParameterJdbcTemplate template;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      return repository.findByUsername(username);
    } catch (EmptyResultDataAccessException e) {
      throw new UsernameNotFoundException("Username not found");
    }
  }

  public void register(Registration data) {

    List<String> names = template.query("SELECT username FROM users", new RowMapper<String>() {
              @Override
              public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("username");
              }
            }
    );

    if (names.contains(data.getUsername())) {
      throw new RuntimeException("Такое имя пользователя уже существует");
    }


    if (!data.getPassword().equals(data.getConfirm())) {
      throw new RuntimeException("Пароли не совпадают");
    }

    repository.save(new User(
            0,
            data.getUsername(),
            passwordEncoder.encode(data.getPassword()),
            "нет записи",
            "нет записи",
            "нет записи",
            "нет записи",
            "нет записи",
            "нет записи",
            "нет записи",
            List.of(new SimpleGrantedAuthority("ROLE_USER")),
            true,
            true,
            true,
            true,
            Collections.emptyList()
    ));


  }


    public List<User> findAllUsers() {
      return repository.findAllUsers();
    }

  public List<User> findByRequest(String search) {
    return repository.findByRequest(search);
  }

  public void changeProfileData(UserInfo userInfo) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    int id = user.getId();
    template.update("UPDATE users SET first_name = :first_name, second_name = :second_name, country = :country , city = :city, address = :address," +
                    " phone_number = :phone_number, second_phone_number = :second_phone_number WHERE id = :id",
            Map.of(
                    "id", id,
                    "first_name", userInfo.getFirstName(),
                    "second_name", userInfo.getSecondName(),
                    "country",userInfo.getCountry(),
                    "city",userInfo.getCity(),
                    "address",userInfo.getAddress(),
                    "phone_number",userInfo.getPhoneNumber(),
                    "second_phone_number",userInfo.getSecondPhoneNumber()
            )
    );
  }
}
