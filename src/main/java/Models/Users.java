package Models;




import lombok.*;

@AllArgsConstructor
@Builder
@Data
@Getter
@Setter
@NoArgsConstructor

public class Users {
    private String username;
    private String email;
    private String phone;
    private String age;
    private String password;


}

