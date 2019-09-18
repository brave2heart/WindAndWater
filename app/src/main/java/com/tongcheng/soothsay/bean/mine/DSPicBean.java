package com.tongcheng.soothsay.bean.mine;

/**
 * Created by 宋家任 on 2016/12/6
 * 大师申请涉及图片的实体bean
 */

public class DSPicBean {


    /**
     * errorCode : 00000
     * message : 调用成功
     * result : {"fileUrl":"http://120.76.219.201:8080/file/getLocalFile.do?fileType=cacheImages&fileName=96021480990357062.jpg"}
     * status : 1
     */

    private String errorCode;
    private String message;
    private ResultBean result;
    private int status;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class ResultBean {
        /**
         * fileUrl : http://120.76.219.201:8080/file/getLocalFile.do?fileType=cacheImages&fileName=96021480990357062.jpg
         */

        private String fileUrl;

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }
    }
}
