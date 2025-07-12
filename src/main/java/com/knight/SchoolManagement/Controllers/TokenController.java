package com.knight.SchoolManagement.Controllers;

import com.knight.SchoolManagement.Controllers.dto.LoginRequest;
import com.knight.SchoolManagement.Controllers.dto.LoginResponse;
import com.knight.SchoolManagement.Repository.UserRepository;
import com.knight.SchoolManagement.Services.UserService;
import com.knight.SchoolManagement.entities.User;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TokenController {

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){

        Optional<User> user = userService.findByEmail(loginRequest.email());

        if (user.isEmpty() || !user.get().isLoginCorrect(loginRequest, bCryptPasswordEncoder)){
            throw new BadCredentialsException("Name or password is invalid.");
        }

        Instant now = Instant.now();
        Long expiresIn = 300L;

        var roles = List.of(user.get().getRole());

        var claims = JwtClaimsSet.builder().issuer("mybackend").subject(user.get().getId().toString())
                .issuedAt(now).expiresAt(now.plusSeconds(expiresIn)).claim("roles", roles).build();

        String jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));
    }
}
