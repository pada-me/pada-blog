package me.pada.blog.oauth.domain;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@Entity
@Table(name = "p_user")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;

  @Column(name = "password_hash")
  private String password;

  private String email;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "activation_key")
  private String activationKey;

  @Column(name = "reset_key")
  private String resetKey;

  @Column(name = "reset_date")
  private OffsetDateTime resetDate;

  @Column(name = "created_at")
  private OffsetDateTime createdAt;

  @Column(name = "updated_at")
  private OffsetDateTime updatedAt;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "p_user_role",
      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "name")})
  private Set<Role> roles = new LinkedHashSet<>();

  private Boolean enabled = Boolean.FALSE;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream().map(p -> new SimpleGrantedAuthority(p.getName())).collect(Collectors.toList());
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}