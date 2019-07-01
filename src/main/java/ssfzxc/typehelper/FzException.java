package ssfzxc.typehelper;

/**
 * Created by ssf on 2018-04-04.
 */
public class FzException extends Exception {

    private String errCode;
    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public FzException() {
    }

    public FzException(String message, Throwable cause) {
        super(message, cause);
    }

    public FzException(String message) {
        super(message);
    }

    public FzException(Throwable cause) {
        super(cause);
    }

    public FzException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
