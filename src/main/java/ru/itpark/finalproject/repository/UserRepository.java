package ru.itpark.finalproject.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;
import ru.itpark.finalproject.domain.Card;
import ru.itpark.finalproject.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class UserRepository {
  private final NamedParameterJdbcTemplate template;

  public List<User> findAllUsers() {

        return template.query(
                "SELECT id, username, password, first_name, second_name,country, city, address,phone_number, second_phone_number, " +
                        "enabled, account_non_expired, account_non_locked, account_non_expired, credentials_non_expired FROM users",
                (rs, i) -> new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("first_name"),
                        rs.getString("second_name"),
                        rs.getString("country"),
                        rs.getString("city"),
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        rs.getString("second_phone_number"),
                        Collections.emptyList(),
                        rs.getBoolean("enabled"),
                        rs.getBoolean("account_non_expired"),
                        rs.getBoolean("account_non_locked"),
                        rs.getBoolean("credentials_non_expired"),
                        new LinkedList<>()
                )
        );
    }

  public User findByUsername(String username) {
    User user = template.queryForObject(
            "SELECT id, username, password, first_name, second_name,country, city, address,phone_number, second_phone_number, " +
                    "enabled, account_non_expired, account_non_locked, account_non_expired, credentials_non_expired FROM users WHERE username = :username",
            Map.of("username", username),
            new RowMapper< User>() {
              @Override
              public User mapRow(ResultSet rs, int i) throws SQLException {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("first_name"),
                        rs.getString("second_name"),
                        rs.getString("country"),
                        rs.getString("city"),
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        rs.getString("second_phone_number"),
                        Collections.emptyList(),
                        rs.getBoolean("enabled"),
                        rs.getBoolean("account_non_expired"),
                        rs.getBoolean("account_non_locked"),
                        rs.getBoolean("credentials_non_expired"),
                        new LinkedList<>() //:TODO точно должен возвращаться пустой лист?
                );
              }
            }
    );


    List<GrantedAuthority> authorities = template.query(
            "SELECT authority FROM authorities WHERE user_id = :user_id",
            Map.of("user_id", user.getId()),
            new RowMapper<GrantedAuthority>() {
              @Override
              public GrantedAuthority mapRow(ResultSet rs, int i) throws SQLException {
                return new SimpleGrantedAuthority(
                        rs.getString("authority")
                );
              }
            }
    );

    user.setAuthorities(authorities);

    return user;
  }

  public void save(User user) {
    if (user.getId() == 0) {

      KeyHolder keyHolder = new GeneratedKeyHolder();
      MapSqlParameterSource params = new MapSqlParameterSource();
      params.addValues(Map.of(
              "username", user.getUsername(),
              "password", user.getPassword(),
              "enabled", user.isEnabled(),
              "account_non_expired", user.isAccountNonExpired(),
              "account_non_locked", user.isAccountNonLocked(),
              "credentials_non_expired", user.isCredentialsNonExpired()
      ));

      template.update(
              "INSERT INTO users (username, password, enabled, account_non_expired, account_non_locked, credentials_non_expired) VALUES (:username, :password, :enabled, :account_non_expired, :account_non_locked, :credentials_non_expired)",
              params,
              keyHolder
      );

      int id = keyHolder.getKey().intValue();

      for (GrantedAuthority authority : user.getAuthorities()) {
        template.update(
                "INSERT INTO authorities (user_id, authority) VALUES (:user_id, :authority)",
                Map.of(
                        "user_id", id,
                        "authority", authority.getAuthority()
                )
        );
      }

      return;
    }

    template.update("UPDATE users SET username = :username, password = :password, enabled = :enabled, account_non_expired = :account_non_expired, account_non_locked = :account_non_locked, credentials_non_expired = :credentials_non_expired WHERE user_id = :user_id",
            Map.of(
                    "user_id", user.getId(),
                    "username", user.getUsername(),
                    "password", user.getPassword(),
                    "enabled", user.isEnabled(),
                    "account_non_expired", user.isAccountNonExpired(),
                    "account_non_locked", user.isAccountNonLocked(),
                    "credentials_non_expired", user.isCredentialsNonExpired()
            )
    );
    template.update(
            "DELETE FROM authorities WHERE user_id = :user_id",
            Map.of("user_id", user.getId())
    );

    // сохраняем его права
    for (GrantedAuthority authority : user.getAuthorities()) {
      template.update(
              "INSERT INTO authorities (user_id, authority) VALUES (:user_id, :authority)",
              Map.of(
                      "user_id", user.getId(),
                      "authority", authority.getAuthority()
              )
      );
    }
  }

    public List<User> findByRequest(String search) {

        return template.query(
                "SELECT id, username, password, first_name, second_name,country, city, address,phone_number, second_phone_number, " +
                        "enabled, account_non_expired, account_non_locked, account_non_expired, credentials_non_expired FROM users WHERE  LOWER (username) LIKE :search",
                Map.of("search", "%"+search.toLowerCase()+"%"),
                (rs, i) -> new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("first_name"),
                        rs.getString("second_name"),
                        rs.getString("country"),
                        rs.getString("city"),
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        rs.getString("second_phone_number"),
                        Collections.emptyList(),
                        rs.getBoolean("enabled"),
                        rs.getBoolean("account_non_expired"),
                        rs.getBoolean("account_non_locked"),
                        rs.getBoolean("credentials_non_expired"),
                        new LinkedList<>()
                )
        );
    }
}
