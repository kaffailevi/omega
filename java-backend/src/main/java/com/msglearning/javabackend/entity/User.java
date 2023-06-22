package com.msglearning.javabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = User.TABLE_NAME)
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    static final String TABLE_NAME = "user";



    public User(Long id, String firstName, String lastName, String email, String phone, String profileImage, String occupation, boolean isManager, List<Borrow> borrowList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.profileImage = profileImage;
        this.occupation = occupation;
        this.borrowList = borrowList;
        this.isManager = isManager;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(unique = true,
            nullable = false)
    private String email;

    @Column(unique = true,
            nullable = false)
    private String phone;

    @Column
    private String profileImage;

    @Column
    private String occupation;

    @Column
    private  boolean isManager;

    @OneToMany(mappedBy = "user")
    private List<Borrow> borrowList;

    @OneToMany(mappedBy = "user")
    private List<Rating> ratingList;

}
