package com.kerupuksda.kerupuksdawebapi.controllers;

import com.kerupuksda.kerupuksdawebapi.models.request.BaseIdRequest;
import com.kerupuksda.kerupuksdawebapi.models.request.BaseSearchRequest;
import com.kerupuksda.kerupuksdawebapi.models.request.products.ProductAddRequest;
import com.kerupuksda.kerupuksdawebapi.models.request.products.ProductUpdateRequest;
import com.kerupuksda.kerupuksdawebapi.models.response.Response;
import com.kerupuksda.kerupuksdawebapi.models.response.ValidationResponse;
import com.kerupuksda.kerupuksdawebapi.models.response.products.ProductListResponse;
import com.kerupuksda.kerupuksdawebapi.models.response.products.ProductResponse;
import com.kerupuksda.kerupuksdawebapi.services.product.*;
import com.kerupuksda.kerupuksdawebapi.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
@Api(tags = "Kerupuk SDA Product API")
public class ProductController extends BaseController {

    private final GetProductPageService getProductPageService;
    private final GetProductDetailService getProductDetailService;
    private final PostProductAddService postProductAddService;
    private final PutProductUpdateService putProductUpdateService;
    private final DeleteProductService deleteProductService;


//    @PreAuthorize("hasRole('ADMIN') or hasRole('CREATOR') or hasRole('USER')")
    @ApiOperation(value = "Api to get all products", notes = "Api Get all products")
    @ApiResponses(value = { @ApiResponse(code = 200, message = AppConstants.SUCCESS_MSG_SUBMIT),
            @ApiResponse(code = 400, message = AppConstants.ERROR_MSG_BAD_REQUEST),
            @ApiResponse(code = 401, message = AppConstants.ERROR_MSG_UNAUTHORIZED),
            @ApiResponse(code = 403, message = AppConstants.ERROR_MSG_FORBIDDEN),
            @ApiResponse(code = 404, message = AppConstants.ERROR_MSG_NOT_FOUND),
            @ApiResponse(code = 500, message = AppConstants.ERROR_MSG_SOMETHING_WRONG) })
    @GetMapping("/list")
    public Response<ProductListResponse> getAllProducts(BaseSearchRequest request) {
        ProductListResponse response = getProductPageService.execute(request);
        return Response.success(response, AppConstants.SUCCESS_MSG_SUBMIT);
    }

//    @PreAuthorize("hasRole('ADMIN') or hasRole('CREATOR') or hasRole('USER')")
    @ApiOperation(value = "Api to get detail product", notes = "Api Get detail product")
    @ApiResponses(value = { @ApiResponse(code = 200, message = AppConstants.SUCCESS_MSG_SUBMIT),
            @ApiResponse(code = 400, message = AppConstants.ERROR_MSG_BAD_REQUEST),
            @ApiResponse(code = 401, message = AppConstants.ERROR_MSG_UNAUTHORIZED),
            @ApiResponse(code = 403, message = AppConstants.ERROR_MSG_FORBIDDEN),
            @ApiResponse(code = 404, message = AppConstants.ERROR_MSG_NOT_FOUND),
            @ApiResponse(code = 500, message = AppConstants.ERROR_MSG_SOMETHING_WRONG) })
    @GetMapping("/detail")
    public Response<ProductResponse> getUserDetail(@Valid BaseIdRequest request) {
        ProductResponse response = getProductDetailService.execute(request);
        return Response.success(response, AppConstants.SUCCESS_MSG_SUBMIT);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('CREATOR')")
    @ApiOperation(value = "Api to add product", notes = "Api add product")
    @ApiResponses(value = { @ApiResponse(code = 200, message = AppConstants.SUCCESS_MSG_SUBMIT),
            @ApiResponse(code = 400, message = AppConstants.ERROR_MSG_BAD_REQUEST),
            @ApiResponse(code = 401, message = AppConstants.ERROR_MSG_UNAUTHORIZED),
            @ApiResponse(code = 403, message = AppConstants.ERROR_MSG_FORBIDDEN),
            @ApiResponse(code = 404, message = AppConstants.ERROR_MSG_NOT_FOUND),
            @ApiResponse(code = 500, message = AppConstants.ERROR_MSG_SOMETHING_WRONG) })
    @PostMapping("/tambah")
    public Response<ValidationResponse> postAddProduct(@Valid @RequestBody ProductAddRequest request) {
        ValidationResponse response = postProductAddService.execute(request);
        return Response.success(response, AppConstants.SUCCESS_MSG_SUBMIT);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('CREATOR')")
    @ApiOperation(value = "Api to add product", notes = "Api add product")
    @ApiResponses(value = { @ApiResponse(code = 200, message = AppConstants.SUCCESS_MSG_SUBMIT),
            @ApiResponse(code = 400, message = AppConstants.ERROR_MSG_BAD_REQUEST),
            @ApiResponse(code = 401, message = AppConstants.ERROR_MSG_UNAUTHORIZED),
            @ApiResponse(code = 403, message = AppConstants.ERROR_MSG_FORBIDDEN),
            @ApiResponse(code = 404, message = AppConstants.ERROR_MSG_NOT_FOUND),
            @ApiResponse(code = 500, message = AppConstants.ERROR_MSG_SOMETHING_WRONG) })
    @PutMapping("/update")
    public Response<ValidationResponse> putUpdateProduct(@Valid @RequestBody ProductUpdateRequest request,
                                                         HttpServletRequest servletRequest) {
        String username = getUsername(servletRequest);
        request.setUsername(username);
        ValidationResponse response = putProductUpdateService.execute(request);
        return Response.success(response, AppConstants.SUCCESS_MSG_SUBMIT);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('CREATOR')")
    @ApiOperation(value = "Api to add product", notes = "Api add product")
    @ApiResponses(value = { @ApiResponse(code = 200, message = AppConstants.SUCCESS_MSG_SUBMIT),
            @ApiResponse(code = 400, message = AppConstants.ERROR_MSG_BAD_REQUEST),
            @ApiResponse(code = 401, message = AppConstants.ERROR_MSG_UNAUTHORIZED),
            @ApiResponse(code = 403, message = AppConstants.ERROR_MSG_FORBIDDEN),
            @ApiResponse(code = 404, message = AppConstants.ERROR_MSG_NOT_FOUND),
            @ApiResponse(code = 500, message = AppConstants.ERROR_MSG_SOMETHING_WRONG) })
    @DeleteMapping("/hapus")
    public Response<ValidationResponse> deleteProduct(@Valid BaseIdRequest request, HttpServletRequest servletRequest) {
        String username = getUsername(servletRequest);
        request.setUsername(username);
        ValidationResponse response = deleteProductService.execute(request);
        return Response.success(response, AppConstants.SUCCESS_MSG_SUBMIT);
    }

}
