package com.tongcheng.soothsay.bean.mine;

/**
 * Created by 宋家任 on 2016/11/22.
 * 头像实体
 */

public class HeadPicBean {


    /**
     * errorCode : 00000
     * message : 调用成功
     * result : {"headPic":"http://120.76.219.201:8080/file/getLocalFile.do?fileType=cacheImages&fileName=94331479869685900.jpg"}
     * status : 1
     */

    private String errorCode;
    private String message;
    /**
     * headPic : http://120.76.219.201:8080/file/getLocalFile.do?fileType=cacheImages&fileName=94331479869685900.jpg
     */

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
        private String headPic;

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }
    }
}
