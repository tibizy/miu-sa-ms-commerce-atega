package edu.miu.sa.cardservice.dto;

public class BasicResponse {
    public Boolean isSuccessful;

    public BasicResponse(){
        isSuccessful = false;
    }

    public BasicResponse(Boolean success)
    {
        isSuccessful = success;
    }
}


