package me.pada.blog.oauth.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "p_role")
public class Role {
  @Id
  private String name;
}
