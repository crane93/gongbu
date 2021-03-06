package com.yuri.gongbu.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import java.util.List;

import com.yuri.gongbu.domain.BaseTimeEntity;
import com.yuri.gongbu.domain.words.Words;
import com.yuri.gongbu.global.GlobalVariable;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(length = 50, nullable = false)
    private String userName;

    @Column(length = 50, nullable = false)
    private String userEmail;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Role userRole;

    @Column 
    private Integer deleteFlg;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<Words> words;

    @Builder
    public User(String userName, String userEmail, Role userRole, Integer deleteFlg) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.deleteFlg = deleteFlg;
    }

    public User update(String userName) {
        this.userName = userName;

        return this;
    }

    public String getUserRoleKey() {
        return this.userRole.getKey();
    }

    public void withdrawal() {
        this.deleteFlg = GlobalVariable.TRUE;
    }
}