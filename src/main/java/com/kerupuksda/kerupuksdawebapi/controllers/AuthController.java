package com.kerupuksda.kerupuksdawebapi.controllers;

import com.kerupuksda.kerupuksdawebapi.models.request.users.UserLoginRequest;
import com.kerupuksda.kerupuksdawebapi.models.request.users.UserRegisterRequest;
import com.kerupuksda.kerupuksdawebapi.models.response.*;
import com.kerupuksda.kerupuksdawebapi.models.response.users.UserLoginResponse;
import com.kerupuksda.kerupuksdawebapi.services.auth.PostSignInService;
import com.kerupuksda.kerupuksdawebapi.services.auth.PostSignUpService;
import com.kerupuksda.kerupuksdawebapi.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Api(tags = "Kerupuk SDA Auth API")
public class AuthController {

    @Autowired
    private PostSignInService postSignInService;
    @Autowired
    private PostSignUpService postSignUpService;

    @ApiOperation(value = "Api to post sign-in users", notes = "Api sign-in users")
    @ApiResponses(value = { @ApiResponse(code = 200, message = AppConstants.SUCCESS_MSG_SUBMIT),
            @ApiResponse(code = 400, message = AppConstants.ERROR_MSG_BAD_REQUEST),
            @ApiResponse(code = 401, message = AppConstants.ERROR_MSG_UNAUTHORIZED),
            @ApiResponse(code = 403, message = AppConstants.ERROR_MSG_FORBIDDEN),
            @ApiResponse(code = 404, message = AppConstants.ERROR_MSG_NOT_FOUND),
            @ApiResponse(code = 500, message = AppConstants.ERROR_MSG_SOMETHING_WRONG) })
    @PostMapping("/sign-in")
    public Response<UserLoginResponse> authenticateUserLogin(@Valid @RequestBody UserLoginRequest request) {
        UserLoginResponse userLoginResponse = postSignInService.execute(request);
        return Response.success(userLoginResponse, AppConstants.SUCCESS_MSG_SUBMIT);
    }

    @ApiOperation(value = "Api to post sign-up users", notes = "Api sign-up users")
    @ApiResponses(value = { @ApiResponse(code = 200, message = AppConstants.SUCCESS_MSG_SUBMIT),
            @ApiResponse(code = 400, message = AppConstants.ERROR_MSG_BAD_REQUEST),
            @ApiResponse(code = 401, message = AppConstants.ERROR_MSG_UNAUTHORIZED),
            @ApiResponse(code = 403, message = AppConstants.ERROR_MSG_FORBIDDEN),
            @ApiResponse(code = 404, message = AppConstants.ERROR_MSG_NOT_FOUND),
            @ApiResponse(code = 500, message = AppConstants.ERROR_MSG_SOMETHING_WRONG) })
    @PostMapping("/sign-up")
    public Response<ValidationResponse> registerUser(@Valid @RequestBody UserRegisterRequest request,
                                                     HttpServletRequest httpRequest) {
        ValidationResponse userLoginResponse = postSignUpService.execute(request);
        return Response.success(userLoginResponse, AppConstants.SUCCESS_MSG_SUBMIT);
    }

}
