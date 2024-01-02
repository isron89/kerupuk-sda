package com.kerupuksda.kerupuksdawebapi.services.users;

import com.kerupuksda.kerupuksdawebapi.exception.BadRequestException;
import com.kerupuksda.kerupuksdawebapi.models.entity.User;
import com.kerupuksda.kerupuksdawebapi.models.request.BaseIdRequest;
import com.kerupuksda.kerupuksdawebapi.models.response.ValidationResponse;
import com.kerupuksda.kerupuksdawebapi.repository.UserRepository;
import com.kerupuksda.kerupuksdawebapi.services.BaseService;
import com.kerupuksda.kerupuksdawebapi.utils.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class DeleteUserService implements BaseService<BaseIdRequest, ValidationResponse> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ValidationResponse execute(BaseIdRequest request) {
        User user = userRepository.findById(UUID.fromString(request.getId())).orElseThrow(
                () -> new BadRequestException(AppConstants.USER_NOT_FOUND)
        );

        user.setIsDeleted(true);
        user.setUpdatedBy(request.getUsername());
        user.setUpdatedDate(LocalDateTime.now());
        userRepository.save(user);

        return ValidationResponse.builder().result(true).build();
    }

}
