package com.kerupuksda.kerupuksdawebapi.services.auth;

import com.kerupuksda.kerupuksdawebapi.configuration.security.jwt.JwtUtils;
import com.kerupuksda.kerupuksdawebapi.configuration.security.services.UserDetailsImpl;
import com.kerupuksda.kerupuksdawebapi.exception.BadRequestException;
import com.kerupuksda.kerupuksdawebapi.models.entity.User;
import com.kerupuksda.kerupuksdawebapi.models.request.users.UserLoginRequest;
import com.kerupuksda.kerupuksdawebapi.models.response.users.UserLoginResponse;
import com.kerupuksda.kerupuksdawebapi.repository.UserRepository;
import com.kerupuksda.kerupuksdawebapi.services.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Slf4j
@Service
public class PostSignInService implements BaseService<UserLoginRequest, UserLoginResponse> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserLoginResponse execute(UserLoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).findFirst().orElseThrow(() ->
                        new BadRequestException("User has not associate with role"));

        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with username " + userDetails.getUsername())
        );

        return UserLoginResponse.builder().id(user.getId().toString())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(roles)
                .token(jwt)
                .build();
    }

}
