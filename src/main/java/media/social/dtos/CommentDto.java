package media.social.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private String id;
    @NotEmpty
    @NotNull
    @Size(min = 2, message = "Name should not be less than 2 characters")
    private String name;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @NotNull
    @Size(min = 2, message = "Comment Body should not be less than characters")
    private String body;
}
