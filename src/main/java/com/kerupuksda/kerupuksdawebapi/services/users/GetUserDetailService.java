package com.kerupuksda.kerupuksdawebapi.services.users;

import com.kerupuksda.kerupuksdawebapi.exception.BadRequestException;
import com.kerupuksda.kerupuksdawebapi.models.entity.User;
import com.kerupuksda.kerupuksdawebapi.models.request.BaseIdRequest;
import com.kerupuksda.kerupuksdawebapi.models.response.users.UserResponse;
import com.kerupuksda.kerupuksdawebapi.repository.UserRepository;
import com.kerupuksda.kerupuksdawebapi.services.BaseService;
import com.kerupuksda.kerupuksdawebapi.utils.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class GetUserDetailService implements BaseService<BaseIdRequest, UserResponse> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse execute(BaseIdRequest request) {
        User user = userRepository.findById(UUID.fromString(request.getId())).orElseThrow(
                () -> new BadRequestException(AppConstants.USER_NOT_FOUND)
        );

        return mappingUser(user);
    }

    private UserResponse mappingUser(User user) {
        return UserResponse.builder()
                .id(user.getId().toString())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().getRoleName())
                .status(AppConstants.USER_STATUS.valueOf(user.getStatus()).getValue())
                .fullname(user.getFullname())
                .address(user.getAddress())
                .phoneNo(user.getPhoneNo())
                .build();
    }

}
