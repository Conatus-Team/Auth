package moine.domain.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpleDto {
    private Long userId;
    private String userName;
    private String email;
    private String userNickname;
}
