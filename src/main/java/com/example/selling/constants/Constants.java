package com.example.selling.constants;

import com.example.selling.common.CommonUtil;

public class Constants {
    public static class RESPONSE_TYPE {
        public static final String SUCCESS = "SUCCESS";
        public static final String ERROR = "ERROR";
        public static final String WARNING = "WARNING";
        public static final String CONFIRM = "CONFIRM";
        public static final String invalidPermission = "invalidPermission";

        public static final String doesNotExist = "doesNotExist";
        public static final String OK = "OK";
        public static final String NOK = "NOK";
        public static final String EXIST = "EXIST";

    }

    public interface COMMON {
        String FONT_FOLDER = CommonUtil.getConfig("fontFolder");
        String MARKET_COMPANY_ID = "MARKET_COMPANY_ID";
        String EXPORT_FOLDER = CommonUtil.getConfig("exportFolder");
        //Thu muc chua file tam de import
        String IMPORT_FOLDER = "/share/import/";
        String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    }

    public static class RESPONSE_CODE {
        public static final String SUCCESS = "success";
        public static final String DELETE_SUCCESS = "deleteSuccess";
        public static final String UPDATE_STATUS_SUCCESS = "updateStatusSuccess";
        public static final String UPDATE_SUCCESS = "updateSuccess";
        public static final String ERROR = "error";
        public static final String WARNING = "warning";
        public static final String RECORD_DELETED = "record.deleted";
        public static final String RECORD_INUSED = "record.inUsed";
        public static final String DOCUMENT_TYPE_EXISTED = "documentTypeExits";
        public static final String NO_RECORDS = "evaluation.noRecords";
        public static final String ROLE_EXIST = "permission.role.exist";
        public static final String ERROR_COMPOSITE = "error.composite";
        public static final String SUCCESS_COMPOSITE = "success.composite";
        public static final String ERROR_SEND = "error.send";
        public static final String SUCCESS_SEND = "success.send";
        public static final String SUCCESS_SAVE = "success.save";
        public static final String DELETE_ERROR = "error.delete";
        public static final String Existed_Username = "Existed Username";
        public static final String Existed_Email = "Existed Email";
        public static final String Incorrect = "Incorrect Username or Password";
    }

}
