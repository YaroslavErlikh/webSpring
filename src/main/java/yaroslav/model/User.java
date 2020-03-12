package yaroslav.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id",  columnDefinition = "bigint")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "varchar(50)", unique = true, nullable = false)
    private String name;

    @Column(name = "password", columnDefinition = "varchar(50)", nullable = false)
    private String pass;

    @Column(name = "role", columnDefinition = "varchar(10) default 'user'", nullable = false)
    private String role;

    public User() {
    }

    public User(String name, String pass, String role) {
        this.name = name;
        this.pass = pass;
        this.role = role;
    }

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
        role = "user";
    }

    public User(Long id, String name, String pass, String role) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.role = role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public String getRole() {
        return role;
    }
}
