package com.codewithmosh.store.auth;

import com.codewithmosh.store.users.UserDto;
import com.codewithmosh.store.users.UserMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtConfig jwtConfig;
    private final UserMapper userMapper;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(
            @Valid @RequestBody UserLoginRequest loginRequest,
            HttpServletResponse response) {

        var login = authService.login(loginRequest);

        response.addCookie(getCookie(login));

        return ResponseEntity.ok(new JwtResponse(login.getAccessToken()));
    }

    private Cookie getCookie(LoginResponse login) {
        var cookie = new Cookie("refreshToken", login.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setPath("/auth/refresh");
        cookie.setMaxAge(jwtConfig.getRefreshTokenExpiration());
        cookie.setSecure(true);
        return cookie;
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(
            @CookieValue(value="refreshToken") String refreshToken
    ){
        var accessToken = authService.refreshToken(refreshToken);

        return new JwtResponse(accessToken.toString());
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me(){
        var user = authService.getCurrentUser();
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        var userDto = userMapper.toDto(user);

        return ResponseEntity.ok(userDto);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
