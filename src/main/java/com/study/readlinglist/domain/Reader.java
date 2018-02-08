package com.study.readlinglist.domain;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity // JPA 엔티티
public class Reader implements UserDetails{

	private static final long serialVersionUID=1L;
	
	@Id // Reader 필드
	private String username; // 엔티티 ID
	private String fullname;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	// UserDetails 인터페이스의 메서드 구현
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { // READER 권한 부여
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_READER"));
	}
	
	@Override
	public boolean isAccountNonExpired() { // 계정이 만료되지 않았다는 것을 반환
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() { // 계정이 잠겨 있지 않았다는 것을 반환
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() { // 자격이 유효하다는 것을 반환
		return true;
	}
	
	@Override
	public boolean isEnabled() { // 계정이 활성화되어 있다는 것을 반환
		return true;
	}

}
