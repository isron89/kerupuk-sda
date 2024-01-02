package com.kerupuksda.kerupuksdawebapi.services.users;

import com.kerupuksda.kerupuksdawebapi.models.entity.User;
import com.kerupuksda.kerupuksdawebapi.models.request.BaseRequest;
import com.kerupuksda.kerupuksdawebapi.models.response.users.UserListResponse;
import com.kerupuksda.kerupuksdawebapi.models.response.users.UserResponse;
import com.kerupuksda.kerupuksdawebapi.repository.UserRepository;
import com.kerupuksda.kerupuksdawebapi.services.BaseService;
import com.kerupuksda.kerupuksdawebapi.utils.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GetUserListService implements BaseService<BaseRequest, UserListResponse> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserListResponse execute(BaseRequest request) {
        List<User> userList = userRepository.getAllUser();
        List<UserResponse> userResponseList = userList.stream().map(this::mappingUser).collect(Collectors.toList());

        return UserListResponse.builder().data(userResponseList).build();
    }

    private UserResponse mappingUser(User user) {
        return UserResponse.builder()
                .id(user.getId().toString())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().getRoleName())
                .status(AppConstants.USER_STATUS.valueOf(user.getStatus()).getValue())
                .build();
    }

}
