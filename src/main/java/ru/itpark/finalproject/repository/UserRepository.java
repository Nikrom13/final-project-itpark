package ru.itpark.finalproject.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;
import ru.itpark.finalproject.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class UserRepository {
  private final NamedParameterJdbcTemplate template;


  public User findByUsername(String username) {
    User user = template.queryForObject(
            "SELECT id, username, password, enabled, account_non_expired, account_non_locked, account_non_expired, credentials_non_expired " +
                    "FROM users WHERE username = :username",
            Map.of("username", username),
            new RowMapper<User>() {
              @Override
              public User mapRow(ResultSet rs, int i) throws SQLException {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        Collections.emptyList(),
                        rs.getBoolean("enabled"),
                        rs.getBoolean("account_non_expired"),
                        rs.getBoolean("account_non_locked"),
                        rs.getBoolean("credentials_non_expired")
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

  }


}
