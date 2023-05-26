package com.example.NewFriends.entity;



import com.example.NewFriends.util.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "user")
@DynamicUpdate
public class User implements UserDetails {
    @Id
    @Column
    private String login;
    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @ToString.Exclude
    @OneToOne(mappedBy = "user")
    private UserData userData;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(status.name()));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public String getPassword(){
        return password;
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
        return true;
    }
}