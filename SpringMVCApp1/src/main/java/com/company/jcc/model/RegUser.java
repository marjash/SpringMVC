//package com.company.jcc.model;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@Entity
//@Table(name = "reg_user")
//public class RegUser implements UserDetails {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(unique = true)
//    private  String username;
//
//    @Column
//    private String password;
//
//
//    public RegUser(String username, String password, List<SimpleGrantedAuthority> authorities) {
//        this.username = username;
//        this.password = password;
//    }
//
//
//    public RegUser() {
//
//    }
//
//    @Enumerated(EnumType.STRING)
//    private ProjectRole role = ProjectRole.ROLE_USER;
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
//        authorities.add(new SimpleGrantedAuthority(role.name()));
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    private boolean isAccNonExp = true;
//    @Override
//    public boolean isAccountNonExpired() {
//        return isAccNonExp;
//    }
//
//    private boolean isAccNonLock = true;
//    @Override
//    public boolean isAccountNonLocked() {
//        return isAccNonLock;
//    }
//
//    private boolean isCredNonExp = true;
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return isCredNonExp;
//    }
//
//    private boolean isEnebled = true;
//    @Override
//    public boolean isEnabled() {
//        return isEnebled;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public ProjectRole getRole() {
//        return role;
//    }
//
//    public void setRole(ProjectRole role) {
//        this.role = role;
//    }
//
//    public boolean isAccNonExp() {
//        return isAccNonExp;
//    }
//
//    public void setAccNonExp(boolean accNonExp) {
//        isAccNonExp = accNonExp;
//    }
//
//    public boolean isAccNonLock() {
//        return isAccNonLock;
//    }
//
//    public void setAccNonLock(boolean accNonLock) {
//        isAccNonLock = accNonLock;
//    }
//
//    public boolean isCredNonExp() {
//        return isCredNonExp;
//    }
//
//    public void setCredNonExp(boolean credNonExp) {
//        isCredNonExp = credNonExp;
//    }
//
//    public boolean isEnebled() {
//        return isEnebled;
//    }
//
//    public void setEnebled(boolean enebled) {
//        isEnebled = enebled;
//    }
//}