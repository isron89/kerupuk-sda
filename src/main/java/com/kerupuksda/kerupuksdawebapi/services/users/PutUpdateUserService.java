package com.kerupuksda.kerupuksdawebapi.services.users;

import com.kerupuksda.kerupuksdawebapi.exception.BadRequestException;
import com.kerupuksda.kerupuksdawebapi.models.entity.Role;
import com.kerupuksda.kerupuksdawebapi.models.entity.User;
import com.kerupuksda.kerupuksdawebapi.models.request.users.UpdateUserRequest;
import com.kerupuksda.kerupuksdawebapi.models.response.ValidationResponse;
import com.kerupuksda.kerupuksdawebapi.repository.RoleRepository;
import com.kerupuksda.kerupuksdawebapi.repository.UserRepository;
import com.kerupuksda.kerupuksdawebapi.services.BaseService;
import com.kerupuksda.kerupuksdawebapi.utils.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service
public class PutUpdateUserService implements BaseService<UpdateUserRequest, ValidationResponse> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;

    @Transactional
    @Override
    public ValidationResponse execute(UpdateUserRequest request) {

        validationRequest(request);

        User user = userRepository.getUserById(request.getId(), false).orElseThrow(
                () -> new BadRequestException(AppConstants.USER_NOT_FOUND)
        );

        user.setUsername(validationLength("Username", request.getUsername(), user.getUsername(), 4, 100));
        user.setEmail(validationLength("Email", request.getEmail(), user.getEmail(), 4, 200));
        user.setPassword(validationLength("Password", request.getPassword(), user.getPassword(), 8, 200));
        user.setFullname(validationLength("Nama Lengkap", request.getFullname(), user.getFullname(), 4, 200));
        user.setPhoneNo(validationLength("Nomor HP", request.getPhoneNo(), user.getPhoneNo(), 8, 50));
        user.setAddress(validationLength("Alamat", request.getAddress(), user.getAddress(), 4, 200));
        user.setStatus(setStatus(request.getStatus()));
        user.setUpdatedBy(request.getUsername());
        user.setUpdatedDate(LocalDateTime.now());
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

    private void validationRequest(UpdateUserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is already in use!");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Email is already in use!");
        }
    }

    private String validationLength(String field, String content, String defaultContent, Integer minLength, Integer maxLength) {
        if (ObjectUtils.isEmpty(content)){
            return defaultContent;
        } else {
            if (content.length() < minLength || content.length() > maxLength) {
                throw new BadRequestException("Field " + field + " minimal " + minLength + " karakter dan maksimal "
                        + maxLength + " karakter");
            }
            if ("Password".equalsIgnoreCase(field)){
                return encoder.encode(content);
            }
            return content;
        }
    }

    private Integer setStatus(Integer status) {
        if (Objects.equals(AppConstants.USER_STATUS.INACTIVE.getCode(), status)) {
            return AppConstants.USER_STATUS.INACTIVE.getCode();
        }
        return AppConstants.USER_STATUS.ACTIVE.getCode();
    }

}
