package media.social.controllers;

import media.social.dtos.JwtAuthResponseDto;
import media.social.dtos.RegisterDto;
import media.social.services.AuthService;
import media.social.dtos.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


//     Build Login REST API Without JWT
//    @PostMapping(value = {"/login","/signin"})
//    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
//       String response = authService.login(loginDto);
//        return ResponseEntity.ok(response);
//    }

    // Build Login REST API With JWT
    @PostMapping(value = {"/login","/signin"})
    public ResponseEntity<JwtAuthResponseDto> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        JwtAuthResponseDto jwtAuthResponseDto = new JwtAuthResponseDto();
        jwtAuthResponseDto.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponseDto);
    }

    // Build Register REST API
    @PostMapping(value = {"/register","signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

}
