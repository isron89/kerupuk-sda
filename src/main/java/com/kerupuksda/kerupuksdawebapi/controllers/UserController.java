package com.kerupuksda.kerupuksdawebapi.controllers;

import com.kerupuksda.kerupuksdawebapi.models.request.BaseIdRequest;
import com.kerupuksda.kerupuksdawebapi.models.request.BaseRequest;
import com.kerupuksda.kerupuksdawebapi.models.request.users.UpdateUserRequest;
import com.kerupuksda.kerupuksdawebapi.models.response.*;
import com.kerupuksda.kerupuksdawebapi.models.response.users.UserListResponse;
import com.kerupuksda.kerupuksdawebapi.models.response.users.UserResponse;
import com.kerupuksda.kerupuksdawebapi.services.users.DeleteUserService;
import com.kerupuksda.kerupuksdawebapi.services.users.GetUserDetailService;
import com.kerupuksda.kerupuksdawebapi.services.users.GetUserListService;
import com.kerupuksda.kerupuksdawebapi.services.users.PutUpdateUserService;
import com.kerupuksda.kerupuksdawebapi.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@Api(tags = "Kerupuk SDA Users API")
public class UserController extends BaseController {

    @Autowired
    private GetUserListService getUserListService;
    @Autowired
    private GetUserDetailService getUserDetailService;
    @Autowired
    private PutUpdateUserService putUpdateUserService;
    @Autowired
    private DeleteUserService deleteUserService;

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Api to get all users", notes = "Api Get all users")
    @ApiResponses(value = { @ApiResponse(code = 200, message = AppConstants.SUCCESS_MSG_SUBMIT),
            @ApiResponse(code = 400, message = AppConstants.ERROR_MSG_BAD_REQUEST),
            @ApiResponse(code = 401, message = AppConstants.ERROR_MSG_UNAUTHORIZED),
            @ApiResponse(code = 403, message = AppConstants.ERROR_MSG_FORBIDDEN),
            @ApiResponse(code = 404, message = AppConstants.ERROR_MSG_NOT_FOUND),
            @ApiResponse(code = 500, message = AppConstants.ERROR_MSG_SOMETHING_WRONG) })
    @GetMapping("/list")
    public Response<UserListResponse> getAllUsers() {
        UserListResponse response = getUserListService.execute(new BaseRequest());
        return Response.success(response, AppConstants.SUCCESS_MSG_SUBMIT);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('CREATOR') or hasRole('USER')")
    @ApiOperation(value = "Api to get detail user", notes = "Api Get detail user")
    @ApiResponses(value = { @ApiResponse(code = 200, message = AppConstants.SUCCESS_MSG_SUBMIT),
            @ApiResponse(code = 400, message = AppConstants.ERROR_MSG_BAD_REQUEST),
            @ApiResponse(code = 401, message = AppConstants.ERROR_MSG_UNAUTHORIZED),
            @ApiResponse(code = 403, message = AppConstants.ERROR_MSG_FORBIDDEN),
            @ApiResponse(code = 404, message = AppConstants.ERROR_MSG_NOT_FOUND),
            @ApiResponse(code = 500, message = AppConstants.ERROR_MSG_SOMETHING_WRONG) })
    @GetMapping("/detail")
    public Response<UserResponse> getUserDetail(@Valid BaseIdRequest request) {
        UserResponse response = getUserDetailService.execute(request);
        return Response.success(response, AppConstants.SUCCESS_MSG_SUBMIT);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Api to get detail user", notes = "Api Get detail user")
    @ApiResponses(value = { @ApiResponse(code = 200, message = AppConstants.SUCCESS_MSG_SUBMIT),
            @ApiResponse(code = 400, message = AppConstants.ERROR_MSG_BAD_REQUEST),
            @ApiResponse(code = 401, message = AppConstants.ERROR_MSG_UNAUTHORIZED),
            @ApiResponse(code = 403, message = AppConstants.ERROR_MSG_FORBIDDEN),
            @ApiResponse(code = 404, message = AppConstants.ERROR_MSG_NOT_FOUND),
            @ApiResponse(code = 500, message = AppConstants.ERROR_MSG_SOMETHING_WRONG) })
    @PutMapping("/update")
    public Response<ValidationResponse> putUpdateUser(@Valid @RequestBody UpdateUserRequest request, HttpServletRequest servletRequest) {
        String username = getUsername(servletRequest);
        request.setUsernameLoggedUser(username);
        ValidationResponse response = putUpdateUserService.execute(request);
        return Response.success(response, AppConstants.SUCCESS_MSG_SUBMIT);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Api to get detail user", notes = "Api Get detail user")
    @ApiResponses(value = { @ApiResponse(code = 200, message = AppConstants.SUCCESS_MSG_SUBMIT),
            @ApiResponse(code = 400, message = AppConstants.ERROR_MSG_BAD_REQUEST),
            @ApiResponse(code = 401, message = AppConstants.ERROR_MSG_UNAUTHORIZED),
            @ApiResponse(code = 403, message = AppConstants.ERROR_MSG_FORBIDDEN),
            @ApiResponse(code = 404, message = AppConstants.ERROR_MSG_NOT_FOUND),
            @ApiResponse(code = 500, message = AppConstants.ERROR_MSG_SOMETHING_WRONG) })
    @DeleteMapping("/delete")
    public Response<ValidationResponse> deleteUser(@Valid BaseIdRequest request, HttpServletRequest servletRequest) {
        String username = getUsername(servletRequest);
        request.setUsername(username);
        ValidationResponse response = deleteUserService.execute(request);
        return Response.success(response, AppConstants.SUCCESS_MSG_SUBMIT);
    }

}
