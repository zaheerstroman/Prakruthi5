package com.prakruthi.ui.ui;

import static com.prakruthi.ui.Variables.id;
import static com.prakruthi.ui.Variables.message;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.prakruthi.R;
import com.prakruthi.ui.APIs.PaymentCheckApi;
import com.prakruthi.ui.APIs.SupportApi;
import com.prakruthi.ui.Variables;

import pl.droidsonroids.gif.GifImageView;

public class WebView_Verification_AnimationHttpUrlConnection extends AppCompatActivity implements PaymentCheckApi.OnPaymentCheckApiListner {

    GifImageView successGif;
    ImageView gif_webview_verify_image;

    TextView text_OrderId,text_Amount,txt_24_hour;

    private SuccessPaymentCheckModel responseProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web_view_verification_animation_http_url_connection);

        successGif = findViewById(R.id.successGif);
        gif_webview_verify_image = findViewById(R.id.gif_webview_verify_image);

        text_OrderId = findViewById(R.id.text_OrderId);
        text_Amount = findViewById(R.id.text_Amount);
        txt_24_hour = findViewById(R.id.txt_24_hour);

        SetTextViews();


    }

    public void SetTextViews() {

        text_OrderId.setText("");
        text_OrderId.append(String.valueOf(message));

        text_Amount.setText("");
        text_Amount.append(String.valueOf(Variables.Order_No));

        txt_24_hour.setText("");
        txt_24_hour.append(String.valueOf(Variables.Amount));

//        tvAddress.setText("");
//        tvAddress.append(String.valueOf(Variables.address));


        PaymentCheckApi getRecentViewProductsAPI = new PaymentCheckApi(this);
        getRecentViewProductsAPI.HitPaymentCheckApi();

    }

    @Override
    public void OnPaymentCheckApiSuccess() {

    }

    @Override
    public void OnSupportApiGivesError(String error) {

    }
}