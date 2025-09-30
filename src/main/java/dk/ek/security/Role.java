package dk.ek.security;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "roles")
public class Role {
    @Id
   @Column(name = "rolename", nullable = false)
    private String rolename;

    @ManyToMany(mappedBy = "roles")
    Set<User> users = new HashSet<>();

    public Role(String rolename){
        this.rolename = rolename;
    }
}
