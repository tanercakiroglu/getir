package com.example.reading.is.good.entity;

import com.example.reading.is.good.config.AuditEntityListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditEntityListener.class)
@Entity(name="customers")
public class Customer implements Serializable, UserDetails {

    private static final long serialVersionUID = -3603020426909727824L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String name;

    private String surname;

    private boolean isAccountNonExpired;

    private boolean isAccountNonLocked;

    private boolean isCredentialsNonExpired;

    private boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }






}
