package com.prakruthi.ui.APIs;

import static com.google.firebase.messaging.Constants.TAG;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.prakruthi.ui.OTP_Verification;
import com.prakruthi.ui.Variables;
import com.prakruthi.ui.ui.profile.ProfileSupportResponse;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PaymentCheckApi {

    OnPaymentCheckApiListner mListner;

    public PaymentCheckApi(OnPaymentCheckApiListner mListner) {
        this.mListner = mListner;
    }

    public void HitPaymentCheckApi() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new HitPaymentCheckApiApi());
    }


    private class HitPaymentCheckApiApi implements Runnable {

        @Override
        public void run() {
            //Creating array for parameters
            String[] field = new String[4];
            field[0] = "user_id";
            field[1] = "token";
            field[2] = "invoice_id";
            field[3] = "invoice_num";

            //Creating array for data
            String[] data = new String[4];
            data[0] = String.valueOf(Variables.id);
//            data[0] = String.valueOf(userId);
            data[1] = Variables.token;
            data[2] = Variables.invoice_id;
            data[3] = Variables.invoice_num;

            PutData putData = new PutData(Variables.BaseUrl + "paymentCheck", "POST", field, data);


            if (putData.startPut()) {
                if (putData.onComplete()) {

                    String result = putData.getResult();

                    Log.e(TAG, result);

                    try {
                        // Parse JSON response
                        JSONObject jsonObject = new JSONObject(result);

//                        JSONArray jsonArray = jsonObject.getJSONArray("result");

                        boolean statusCode = jsonObject.getBoolean("status_code");

                        if (statusCode) {

                            String message = jsonObject.getString("message");
                            String Order_No = jsonObject.getString("Order_No");
                            String Amount = jsonObject.getString("Amount");


                        } else {
                            // handle the case where status code is false
                            String message = jsonObject.getString("message");
//                            Toast.makeText(PaymentCheckApi.this, message , Toast.LENGTH_SHORT).show();
//                            btn_otp_submit.setVisibility(View.VISIBLE);
                        }

//                            profileSupportModels.add(new ProfileSupportResponse.ProfileSupportModel(id, mobile,email, address));

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
//                    mListner.OnPaymentCheckApiSuccess(profileSupportModels);
                    mListner.OnPaymentCheckApiSuccess();


                }
//                    }
//                handleResponse(result);
            } else {
                handleError("Failed to fetch data");
            }
        }


    }

    private void handleError(String error) {
        mListner.OnSupportApiGivesError(error);

    }

    public interface OnPaymentCheckApiListner {

        //        void OnPaymentCheckApiSuccess(ArrayList<ProfileSupportResponse.ProfileSupportModel> profileSupportModels);
        void OnPaymentCheckApiSuccess();

        void OnSupportApiGivesError(String error);
    }

}
