package proiect.aplicatiebancara.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {


    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
