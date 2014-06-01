package br.com.copa.juntosnumsoritmo.exception;

public class SequenceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errCode;
    private String errMsg;

    public SequenceException(String errMsg) {
        this.errMsg = errMsg;
    }

    public SequenceException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
    
    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

}
