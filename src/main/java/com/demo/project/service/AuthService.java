package com.demo.project.service;

import com.demo.project.entity.RoleEntity;
import com.demo.project.entity.UserAccountEntity;
import com.demo.project.entity.StandardUserEntity;
import com.demo.project.dto.AuthResponse;
import com.demo.project.dto.StandardUserDto;
import com.demo.project.dto.UserLoginDto;
import com.demo.project.repository.RoleRepository;
import com.demo.project.repository.UserAccountRepository;
import com.demo.project.repository.StandardUserRepository;
import com.demo.project.security.JWTGenerator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * This class handles all the requests related to authentication and registration in application.
 */
@Component
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private StandardUserRepository standardUserRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTGenerator jwtGenerator;


    /**
     * Registers a standard user and adds it into database.
     * @param standardUserDto an object that contains all the details of a standard user
     * @return ResponseEntity - If the user was successfully added in the database it return 200 OK, otherwise
     * the registration will not succeed.
     */
    @Transactional
    public ResponseEntity<String> register(StandardUserDto standardUserDto) {
        if (userAccountRepository.existsByEmail(standardUserDto.getEmail())) {
            return new ResponseEntity<>("An account is already registered with this email!", HttpStatus.BAD_REQUEST);
        }

        UserAccountEntity userAcc = new UserAccountEntity();
        userAcc.setName(standardUserDto.getFirstName() + " " + standardUserDto.getLastName());
        userAcc.setEmail(standardUserDto.getEmail());
        userAcc.setPassword(passwordEncoder.encode((standardUserDto.getPassword())));
        RoleEntity role = roleRepository.findByName("USER").get();
        userAcc.setRole(role);
        userAccountRepository.saveAndFlush(userAcc);

        StandardUserEntity user = new StandardUserEntity();
        user.setUserAccountEntity(userAcc);
        user.setDateOfBirth(standardUserDto.getDateOfBirth());
        user.setCounty(standardUserDto.getCounty());
        user.setCity(standardUserDto.getCity());
        standardUserRepository.saveAndFlush(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }


    /**
     * If the principal of the input authentication is valid and verified, AuthenticationManager#authenticate returns an Authentication instance with the authenticated flag set to true and. In this case it is send in the SecurityContextHolder and the token is generated based on it.
     * Otherwise, if the principal is not valid, it will throw an AuthenticationException and that means the user can not log in application because the email or password are wrong.
     * @param userLoginDto email and password
     * @return AuthResponse - access token and token type if the user login successfully, otherwise it returns 401 Unauthorized.
     */
    public ResponseEntity<AuthResponse> login(UserLoginDto userLoginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDto.getEmail(),
                        userLoginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
    }
}
