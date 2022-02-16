//package com.company.jcc.model;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "role")
//public class Role {
//    @Id
//    //@GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(name = "Name")
//    private String name;
//
//    @OneToMany(
//            mappedBy = "role",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
//    private List<User> users = new ArrayList<>();
//
//    public Role() {
//    }
//
//    public Role(String name) {
//        this.name = name;
//    }
//
//    public int getId() { return id; }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
//
//    @Override
//    public String toString() {
//        return "\n Role{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}' + "\n";
//    }
//}
