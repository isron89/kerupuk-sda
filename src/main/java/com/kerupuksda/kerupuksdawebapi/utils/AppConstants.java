package com.kerupuksda.kerupuksdawebapi.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class AppConstants {
    public static final String SUCCESS_MSG_SUBMIT = "Data submitted successfully";
    public static final String ERROR_MSG_BAD_REQUEST = "Bad request";
    public static final String ERROR_MSG_UNAUTHORIZED = "Unauthorized access";
    public static final String ERROR_MSG_FORBIDDEN = "Forbidden access";
    public static final String ERROR_MSG_NOT_FOUND = "Data not found";
    public static final String ERROR_MSG_SOMETHING_WRONG = "Something went wrong";

    public static final String USER_NOT_FOUND = "User tidak ditemukan";
    public static final String PRODUCT_NOT_FOUND = "Product tidak ditemukan";

    public static final String ACTIVE = "Active";
    public static final String INACTIVE = "Inactive";

    public static final String ADMIN = "Administrator";
    public static final String CREATOR = "Content Creator";
    public static final String USER = "User";

    @Getter
    public enum USER_STATUS {
        ACTIVE(1, AppConstants.ACTIVE),
        INACTIVE(2, AppConstants.INACTIVE);

        private Integer code;
        private String value;
        private static Map<Integer, USER_STATUS> statusMap = new HashMap<>();

        USER_STATUS(Integer code, String value) {
            this.code = code;
            this.value = value;
        }

        static {
            for (USER_STATUS pageType : USER_STATUS.values()) {
                statusMap.put(pageType.code, pageType);
            }
        }

        public static USER_STATUS valueOf(Integer pageType) {
            return (USER_STATUS) statusMap.get(pageType);
        }
    }

    @Getter
    public enum USER_ROLES {
        ADMIN("ROLE_ADMIN", AppConstants.ADMIN),
        CREATOR("ROLE_CREATOR", AppConstants.CREATOR),
        USER("ROLE_USER", AppConstants.USER);

        private String code;
        private String value;
        private static final Map<String, USER_ROLES> roleMap = new HashMap<>();

        USER_ROLES(String code, String value) {
            this.code = code;
            this.value = value;
        }

        static {
            for (USER_ROLES pageType : USER_ROLES.values()) {
                roleMap.put(pageType.code, pageType);
            }
        }

        public static USER_ROLES valueOfRole(String pageType) {
            return (USER_ROLES) roleMap.get(pageType);
        }
    }
}
