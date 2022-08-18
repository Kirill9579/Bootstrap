package ru.kata.spring.boot_security.demo.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Table(name = "roles")
public class Role implements GrantedAuthority {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String name;
   @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
   private Set<User> users = new HashSet<>();

   public Role() {
   }

   public Role(String name) {
      this.name = name;
   }

   @Override
   public String getAuthority() {
      return name;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Role)) return false;
      Role role = (Role) o;
      return Objects.equals(name, role.name);
   }

   @Override
   public int hashCode() {
      return Objects.hash(name);
   }
}
