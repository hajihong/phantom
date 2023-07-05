package com.mimikyu.phantom.domain.user;

import com.mimikyu.phantom.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "seller_user")
public class Seller extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private Long id;

    @Column
    private String email;
    @Column
    private String password;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Seller(Long id, String email, String password, String name, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

}
