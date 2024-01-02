package com.kerupuksda.kerupuksdawebapi.models.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "roles", indexes = {
        @Index(name = "role_indx_0", columnList = "role_code"),
        @Index(name = "role_indx_1", columnList = "role_name")
})
public class Role extends BaseEntity {

  @Column(name = "role_code", length = 50)
  private String roleCode;
  @Column(name = "role_name", length = 50)
  private String roleName;
  @OneToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_role", joinColumns = {
          @JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = {
          @JoinColumn(name = "user_id", referencedColumnName = "id") })
  private Set<User> users = new HashSet<>();
  @Column(name = "is_deleted", columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
  private Boolean isDeleted;

}