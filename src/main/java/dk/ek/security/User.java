package dk.ek.security;

import jakarta.persistence.*;
import lombok.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
   @Column(name = "username", nullable = false)
    private String username;
    private String password;

    @ManyToMany()
            @JoinTable(name = "users_roles",
                    joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    Set<Role> roles = new HashSet<>();

    public User(String username, String password){
        this.username = username;
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
        this.password = hashed;

    }

    public boolean checkPassword(String candidate){
        if(BCrypt.checkpw(candidate, password)){
            System.out.println("It matches");
            return true;
        } else {
            System.out.println("doesn't match");
            return false;
        }
    }

    public void addRole(Role role){
        this.roles.add(role);
        role.getUsers().add(this);
    }

    public static void main(String[] args){
        User user = new User("elev", "1234");
        System.out.println("username: " + user.getUsername() + " password: " + user.getPassword());
    }
}
