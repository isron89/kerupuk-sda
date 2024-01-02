package com.kerupuksda.kerupuksdawebapi.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "users", 
    indexes = {
      @Index(name = "user_indx_0", columnList = "username"),
      @Index(name = "user_indx_1", columnList = "email"),
      @Index(name = "user_indx_2", columnList = "nama_lengkap"),
      @Index(name = "user_indx_3", columnList = "nomor_hp"),
      @Index(name = "user_indx_4", columnList = "alamat")
    })
public class User extends BaseEntity {

  @Column(name = "username", length = 100, unique = true, nullable = false)
  private String username;
  @Email
  @Column(name = "email", length = 200, nullable = false)
  private String email;
  @Column(name = "password", length = 200, nullable = false)
  private String password;
  @Column(name = "nama_lengkap", length = 200)
  private String fullname;
  @Column(name = "nomor_hp", length = 50)
  private String phoneNo;
  @Column(name = "alamat", length = 200)
  private String address;
  @Column(name = "status", length = 1)
  private Integer status;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinTable(name = "user_role", joinColumns = {
          @JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {
          @JoinColumn(name = "role_id", referencedColumnName = "id")})
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @JsonBackReference
  private Role role;
  @Column(name = "is_deleted", columnDefinition = "BOOLEAN DEFAULT FALSE")
  private Boolean isDeleted;

}
