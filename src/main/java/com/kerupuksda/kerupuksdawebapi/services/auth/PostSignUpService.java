package com.kerupuksda.kerupuksdawebapi.services.auth;

import com.kerupuksda.kerupuksdawebapi.models.entity.Role;
import com.kerupuksda.kerupuksdawebapi.models.entity.User;
import com.kerupuksda.kerupuksdawebapi.models.request.users.UserRegisterRequest;
import com.kerupuksda.kerupuksdawebapi.models.response.ValidationResponse;
import com.kerupuksda.kerupuksdawebapi.repository.RoleRepository;
import com.kerupuksda.kerupuksdawebapi.repository.UserRepository;
import com.kerupuksda.kerupuksdawebapi.services.BaseService;
import com.kerupuksda.kerupuksdawebapi.utils.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Slf4j
@Service
public class PostSignUpService implements BaseService<UserRegisterRequest, ValidationResponse> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;

    @Transactional
    @Override
    public ValidationResponse execute(UserRegisterRequest request) {

        validationRequest(request);

        // Create new user's account
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setFullname(request.getFullname());
        user.setPhoneNo(request.getPhoneNo());
        user.setAddress(request.getAddress());
        user.setStatus(AppConstants.USER_STATUS.ACTIVE.getCode());
        user.setCreatedBy("SYSTEMS");
        user.setCreatedDate(LocalDateTime.now());
        user.setIsDeleted(false);

        String strRoles = request.getRole();

        if (StringUtils.isEmpty(strRoles)) {
            Role userRole = roleRepository.findByRoleCode(AppConstants.USER_ROLES.USER.getCode())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error: Role is not found."));
            user.setRole(userRole);
        } else {
            Role userRole = roleRepository.findByRoleCode(strRoles)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error: Role is not found."));
            user.setRole(userRole);
        }
        userRepository.save(user);

        return ValidationResponse.builder().result(true).build();
    }

    private void validationRequest(UserRegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is already in use!");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Email is already in use!");
        }
    }

}
