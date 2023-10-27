package media.social.services;

import media.social.dtos.LoginDto;
import media.social.dtos.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
