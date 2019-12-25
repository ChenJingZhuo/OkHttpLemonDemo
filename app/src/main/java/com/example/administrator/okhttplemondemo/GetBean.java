package com.example.administrator.okhttplemondemo;

public class GetBean {

    /**
     * RESULT : S
     * ERRMSG : 成功
     */

    private String RESULT;
    private String ERRMSG;

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public String getERRMSG() {
        return ERRMSG;
    }

    public void setERRMSG(String ERRMSG) {
        this.ERRMSG = ERRMSG;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\"RESULT\":" +RESULT +
                "\"ERRMSG\":"+ERRMSG +
                "\n}";
    }
}
