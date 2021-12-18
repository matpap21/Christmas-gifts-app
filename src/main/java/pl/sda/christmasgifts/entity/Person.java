package pl.sda.christmasgifts.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(nullable = false)
    private UUID id; // dlugi ciag generowany losowo
    private String username;
    private String email;

    @OneToMany
    private Set<Wish> wishes = new HashSet<>();

    @OneToMany
    private Set<Wish> gifts = new HashSet<>();



}
