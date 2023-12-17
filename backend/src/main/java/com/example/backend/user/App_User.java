package com.example.backend.user;

import com.example.backend.course.Course;
import com.example.backend.user_city_challenges.UserCityChallenges;
import com.example.backend.user_courses.UserCourses;
import com.example.backend.user_quests.UserQuests;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;






@Entity
@Table
@RequiredArgsConstructor
public class App_User implements UserDetails {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private String password;


    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value="user-movement")
    private Set<UserCourses> userCourses = new HashSet<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value="user-movement-2")
    private Set<UserQuests> userQuests = new HashSet<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value="user-movement-3")
    private Set<UserCityChallenges> userCityChallenges = new HashSet<>();


    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;


    public App_User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public App_User(String firstname, String lastname, String email, String encode) {
        this.name = firstname + " " + lastname;
        this.email = email;
        this.password = encode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserCourses> getUserCourses() {
        return userCourses;
    }

    public void setUserCourses(Set<UserCourses> userCourses) {
        this.userCourses = userCourses;
    }

    public void addUserCourses(UserCourses userCourses) {
        this.userCourses.add(userCourses);
    }

    public Set<UserQuests> getUserQuests() {
        return userQuests;
    }

    public void setUserQuests(Set<UserQuests> userQuests) {
        this.userQuests = userQuests;
    }

    public Set<UserCityChallenges> getUserCityChallenges() {
        return userCityChallenges;
    }

    public void setUserCityChallenges(Set<UserCityChallenges> userCityChallenges) {
        this.userCityChallenges = userCityChallenges;
    }

    public void addUserCityChallenges(UserCityChallenges userCityChallenges) {
        this.userCityChallenges.add(userCityChallenges);
    }

    @Override
    public String toString() {
        return "App_User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userCourses=" + userCourses + '\'' +
                ", userQuests=" + userQuests + '\'' +
                ", userCityChallenges=" + userCityChallenges + '\'' +
                ", role=" + role +
                '}';
    }

    public void addUserQuests(UserQuests userQuests) {
        this.userQuests.add(userQuests);
    }

    public void removeUserCityChallenges(UserCityChallenges userCityChallenge) {
        this.userCityChallenges.remove(userCityChallenge);
    }

}
