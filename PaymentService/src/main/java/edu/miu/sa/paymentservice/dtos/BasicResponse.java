package edu.miu.sa.paymentservice.dtos;

public class BasicResponse {
    private Boolean isSuccessful;

    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(Boolean successful) {
        isSuccessful = successful;
    }

    public BasicResponse(Boolean success)
    {
        isSuccessful = success;
    }
}



