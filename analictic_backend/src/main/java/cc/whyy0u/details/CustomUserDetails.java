package cc.whyy0u.details;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cc.whyy0u.entity.user.UserEntity;

public class CustomUserDetails implements UserDetails {

  private final UserEntity entity;

  public CustomUserDetails(UserEntity entity) {
   this.entity = entity;
  }

  public UserEntity getEntity() {
  return entity;
  }
  
  @Override
  public String getPassword() {
    return entity.getPassword();
  }

  @Override
  public String getUsername() {
  return entity.getLogin();
  }

  @Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")); 
}




} 
