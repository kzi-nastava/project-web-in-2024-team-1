package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String Name;

    @Column(name = "last_name")
    private String LastName;

    @Column(name = "username", unique = true)
    private String Username;

    @Column(name = "email", unique = true)
    private String Email;

    @Column(name = "phone_number")
    private String PhoneNumber;

    @Column(name = "password")
    private String Password;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private LocalDate DateOfBirth;

    @Column(name = "image_path")
    private String ImagePath;

    @Column(name = "description")
    private String Description;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role UserRole;

    @Column(name = "blocked")
    private Boolean Blocked;

    public User() {
        this(Long.valueOf(""),"","","","","","",LocalDate.MIN,"default.jpg","bio",Role.CUSTOMER,false);
    }

    public User(Long id, String name, String lastName, String username, String email, String phoneNumber, String password) {
        this.id = id;
        this.Name = name;
        this.LastName = lastName;
        this.Username = username;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
        this.Password = password;
        this.DateOfBirth = LocalDate.of(2001,12,12);
        this.ImagePath = "/images/user.jpg";
        this.Description = "bio";
        this.UserRole = Role.CUSTOMER;
        this.Blocked = false;
    }

    public User(Long id, String name, String lastName, String username, String email, String phoneNumber, String password, LocalDate dateOfBirth, String imagePath, String description, Role userRole, Boolean blocked) {
        this.id = id;
        this.Name = name;
        this.LastName = lastName;
        this.Username = username;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
        this.Password = password;
        this.DateOfBirth = dateOfBirth;
        this.ImagePath = imagePath;
        this.Description = description;
        this.UserRole = userRole;
        this.Blocked = blocked;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public LocalDate getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.DateOfBirth = dateOfBirth;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        this.ImagePath = imagePath;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public Role getUserRole() {
        return UserRole;
    }

    public void setUserRole(Role userRole) {
        this.UserRole = userRole;
    }

    public Boolean getBlocked() {
        return Blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.Blocked = blocked;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Username='" + Username + '\'' +
                ", Email='" + Email + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", Password='" + Password + '\'' +
                ", DateOfBirth=" + DateOfBirth +
                ", ImagePath='" + ImagePath + '\'' +
                ", Description='" + Description + '\'' +
                ", UserRole=" + UserRole +
                ", Blocked=" + Blocked +
                '}';
    }
}
