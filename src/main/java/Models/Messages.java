package Models;

import lombok.*;



@Builder
@AllArgsConstructor
@Getter
@Setter
@Data
@NoArgsConstructor
public class Messages {
    private String message;
    private String receiver;
    private  String sender;
}
