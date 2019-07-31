package ru.itpark.finalproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
 public class User  implements UserDetails {
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String secondName;
    private String country;
    private String city;
    private String address;
    private String phoneNumber;
    private String secondPhoneNumber;
    private Collection<GrantedAuthority> authorities;
    private boolean enabled; // isEnabled() делает @Data
    private boolean accountNonExpired; // isAccountNonExpired() делает @Data
    private boolean accountNonLocked; // isAccountNonLocked
    private boolean credentialsNonExpired;
    private List<UserCards> userCards;
}
