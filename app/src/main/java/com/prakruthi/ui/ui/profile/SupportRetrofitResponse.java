package com.prakruthi.ui.ui.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SupportRetrofitResponse {

    @SerializedName("result")
//    private List<SupportRetrofitModel> result;
    private SupportRetrofitModel result;

    @SerializedName("status_code")
    private Boolean statusCode;
    @SerializedName("message")
    private String message;

//    public List<SupportRetrofitModel> getResult() {
//        return result;
//    }
//
//    public void setResult(List<SupportRetrofitModel> result) {
//        this.result = result;
//    }


    public SupportRetrofitModel getResult() {
        return result;
    }

    public void setResult(SupportRetrofitModel result) {
        this.result = result;
    }


    public Boolean getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Boolean statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public class SupportRetrofitModel {

        @SerializedName("id")
        private Integer id;
        @SerializedName("mobile")
        private String mobile;
        @SerializedName("email")
        private String email;
        @SerializedName("address")
        private String address;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

}
