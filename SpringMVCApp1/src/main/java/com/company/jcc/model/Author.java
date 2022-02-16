package com.company.jcc.model;

import javax.persistence.*;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "author_surname")
    private String authorSurname;

//    @OneToMany(
//            mappedBy = "author",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
//    private List<AuthorName> authorNames = new ArrayList<>();

    public Author() {
    }

    public Author(String authorName, String authorSurname, int coAuthorExists) {
        this.authorName = authorName;
        this.authorSurname = authorSurname;
    }

    public Integer getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    @Override
    public String toString() {
        return "\n Author{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                ", authorSurname='" + authorSurname + '\'' +
                '}' + "\n";
    }
}