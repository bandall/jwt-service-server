package com.ajousw.spring.domain.member;

import com.ajousw.spring.domain.member.enums.LoginType;
import com.ajousw.spring.domain.member.enums.Role;
import com.ajousw.spring.domain.member.repository.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    @Column(length = 50)
    private String username;

    @Column(length = 255)
    private String profileImageUri;

    private String roles;

    private LocalDateTime lastLoginTime;

    public void updateLastLoginTime() {
        this.lastLoginTime = LocalDateTime.now();
    }

    public boolean hasRole(Role role) {
        List<String> parsedRoles = Arrays.stream(roles.split(",")).toList();

        return parsedRoles.contains(role.getRoleName());
    }
}
