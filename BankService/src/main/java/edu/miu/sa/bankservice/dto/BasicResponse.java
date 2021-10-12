package edu.miu.sa.bankservice.dto;

public class BasicResponse {
    private Boolean isSuccessful;
    private String responseCode;
    private String ResponseDescription;

    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(Boolean successful) {
        isSuccessful = successful;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDescription() {
        return ResponseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        ResponseDescription = responseDescription;
    }

    public BasicResponse(){
        isSuccessful = false;
    }

    public BasicResponse(Boolean success)
    {
        isSuccessful = success;
    }
}


