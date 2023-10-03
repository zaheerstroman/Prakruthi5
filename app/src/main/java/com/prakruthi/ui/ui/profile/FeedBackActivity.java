package com.prakruthi.ui.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.prakruthi.R;
import com.prakruthi.ui.network.APIClient;
import com.prakruthi.ui.network.ApiInterface;
import com.prakruthi.ui.utils.CommonUtils;
import com.prakruthi.ui.utils.Constants;
import com.prakruthi.ui.utils.SharedPrefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedBackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_feed_back);
        setContentView(R.layout.feedback_dialog);

//        showFeedbackDilog();

    }

    private void showFeedbackDilog() {

        //1-Gasaver Retofit
//        AlertDialog.Builder builder = new AlertDialog.Builder(FeedBackActivity.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(FeedBackActivity.this);

        //2
//        final Dialog dialog = new Dialog(FeedBackActivity.this);
//        dialog.setTitle("Feedback");
        builder.setTitle("Feedback");

        //2
        // Create the EditText
        final EditText editText = new EditText(FeedBackActivity.this);
        builder.setView(editText);


        //2
        // Add the submit button
        builder.setPositiveButton("Submit", null); // Set the click listener to null for now

        //2
        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();

        //1
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.feedback_dialog);
        dialog.setCancelable(false);

        //2
        Button btn_submit = dialog.findViewById(R.id.btn_submit);
        ImageView iv_close = dialog.findViewById(R.id.iv_close);
        EditText et_feedback = dialog.findViewById(R.id.et_feedback);

        //2
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

//        2
//        Button submitButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
//        Button btn_submit = dialog.getButton(AlertDialog.BUTTON_POSITIVE);

        //1
//        btn_submit.setOnClickListener(new View.OnClickListener() {
        //2
        btn_submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String feedback = et_feedback.getText().toString();


                if (!TextUtils.isEmpty(et_feedback.getText().toString().trim()))
                    postFeedback(dialog, et_feedback.getText().toString());
                else
                    Toast.makeText(FeedBackActivity.this, "Please Enter message to submit feedback", Toast.LENGTH_SHORT).show();

//                FeedBackApi feedBackApi = new FeedBackApi( feedback);
//                feedBackApi.FeedbackHitApi();

                dialog.dismiss(); // Dismiss the dialog after handling the click

            }
        });
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    //    private void postFeedback(Dialog dialog, String des) {
    private void postFeedback(Dialog dialog, String des) {

        CommonUtils.showLoading(FeedBackActivity.this, "Please Wait", false);
        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
        JsonObject jsonObject = new JsonObject();


//        jsonObject.addProperty("name", SharedPrefs.getInstance(this).getString(Constants.USER_NAME));
//        jsonObject.addProperty("email", SharedPrefs.getInstance(this).getString(Constants.USER_EMAIL));
//
//        jsonObject.addProperty("phone", SharedPrefs.getInstance(this).getString(Constants.USER_MOBILE));
//        jsonObject.addProperty("fcm_token", SharedPrefs.getInstance(this).getString(Constants.FCM_TOKEN));

//        jsonObject.addProperty("id", SharedPrefs.getInstance(this).getString(Constants.ID));
        jsonObject.addProperty("user_id", SharedPrefs.getInstance(this).getString(Constants.USER_ID));
        jsonObject.addProperty("token", SharedPrefs.getInstance(this).getString(Constants.TOKEN));

//        jsonObject.addProperty("message", msg);
//        jsonObject.addProperty("description", msg);
        jsonObject.addProperty("description", des);


        Call<FeedBackTResponse> call = apiInterface.fetchfeedBack(jsonObject);

        call.enqueue(new Callback<FeedBackTResponse>() {
            @Override
            public void onResponse(Call<FeedBackTResponse> call, Response<FeedBackTResponse> response) {
                FeedBackTResponse baseResponse = response.body();
                if (baseResponse != null && baseResponse.isStatus_code()) {

                    dialog.dismiss();
                    Toast.makeText(FeedBackActivity.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();

                }
                CommonUtils.hideLoading();
            }

            @Override
            public void onFailure(Call<FeedBackTResponse> call, Throwable t) {
                Toast.makeText(FeedBackActivity.this, "Something went wrong. Please Try again", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
                CommonUtils.hideLoading();
            }
        });


    }


}