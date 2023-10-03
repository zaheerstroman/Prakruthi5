package com.prakruthi.ui.ui.profile;

import static android.content.ContentValues.TAG;

import static com.prakruthi.ui.Utility.Constants.REQ_CALL;
import static com.prakruthi.ui.Variables.city;
import static com.prakruthi.ui.Variables.district;

import static com.prakruthi.ui.Variables.id;
import static com.prakruthi.ui.Variables.email;
import static com.prakruthi.ui.Variables.mobile;
import static com.prakruthi.ui.Variables.address;

import static com.prakruthi.ui.Variables.name;
import static com.prakruthi.ui.Variables.state;

import static com.prakruthi.ui.Variables.support_address;
import static com.prakruthi.ui.Variables.support_email;
import static com.prakruthi.ui.Variables.support_mobile;
import static com.prakruthi.ui.Variables.token;
import static com.prakruthi.ui.Variables.userId;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.prakruthi.Manifest;
import com.google.gson.JsonObject;
import com.prakruthi.R;
import com.prakruthi.ui.APIs.GetRecentViewProductsAPI;
import com.prakruthi.ui.APIs.GetUserDataApi;
import com.prakruthi.ui.APIs.SupportApi;
import com.prakruthi.ui.APIs.UserDetailsUpdateApi;
import com.prakruthi.ui.Variables;
import com.prakruthi.ui.misc.Loading;
import com.prakruthi.ui.network.APIClient;
import com.prakruthi.ui.network.ApiInterface;
import com.prakruthi.ui.ui.cart.CartModal;
import com.prakruthi.ui.utils.CommonUtils;
import com.prakruthi.ui.utils.Constants;
import com.prakruthi.ui.utils.SharedPrefs;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SupportActivity extends AppCompatActivity implements SupportApi.OnSupportApiHitFetchedListner {
//public class SupportActivity extends AppCompatActivity implements View.OnClickListener {

//public class SupportActivity extends AppCompatActivity {


//    public static int id;
//    public static String mobile,email,address;

    TextView tvId, tvPhone, tvEmail, tvAddress;

    TextView tvcall1, tvcall2, tvemail, tvlink;

    RelativeLayout ll_phone, ll_email, ll_whatsapp;

//    ArrayList<SupportModel> supportCategoryModalList = new ArrayList<>();

//    ProfileGetUserDataResponse.ProfileGetUserDataModel profileGetUserDataModel_http;

    //HttpURLConnection
    public SupportModel supportCategoryModalList;

    //RETROFIT
    public SupportRetrofitResponse profileDetailsResponse;


//    ProfileSupportResponse.ProfileSupportModel profileSupportModels;

    public SupportModel profileSupportModels;

    AppCompatButton support_back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_support);

        support_back_btn= findViewById(R.id.support_back_btn);

        support_back_btn.setOnClickListener(v -> super.onBackPressed());

        //Retrofit
//        init();
//        fetchHelpDetails();


        //Http

//        tvId = findViewById(R.id.tv_support_id);
//        tvPhone = findViewById(R.id.tv_support_phone);
//        tvEmail = findViewById(R.id.tv_support_email);
//        tvAddress = findViewById(R.id.tv_support_address);

        tvcall1 = findViewById(R.id.tvcall1);
        tvcall2 = findViewById(R.id.tvcall2);
        tvemail = findViewById(R.id.tvemail);
        tvlink = findViewById(R.id.tvlink);

        ll_phone = findViewById(R.id.ll_phone);
        ll_email = findViewById(R.id.ll_email);
        ll_whatsapp = findViewById(R.id.ll_whatsapp);

//        ll_phone.setOnClickListener(this);
//        ll_email.setOnClickListener(this);
//        ll_whatsapp.setOnClickListener(this);


        SetTextViews();

        setdata();

        UserDetailsUpdateApi();

        SetClickListeners();

//        SupportApi supportApi = new SupportApi(this);
//        supportApi.HitSupportApi();

    }

    //Retrofit

    private void fetchHelpDetails() {
        CommonUtils.showLoading(this, "Please Wait", false);

        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                .addFormDataPart("post_type", "profile_details")
                .addFormDataPart("user_id", SharedPrefs.getInstance(SupportActivity.this).getString(Constants.USER_ID))
//                                .addFormDataPart("user_id", SharedPrefs.getInstance(SupportActivity.this).getString(Constants.ID))
                .addFormDataPart("token", SharedPrefs.getInstance(SupportActivity.this).getString(Constants.TOKEN))
//                .addFormDataPart("api_key", SharedPrefs.getInstance(getActivity()).getString(Constants.API_KEY))
                .build();


        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("post_type", "help");
        jsonObject.addProperty("user_id", "user_id");
        jsonObject.addProperty("token", "token");

//        Call<ProfileSupportResponse> call = apiInterface.fetchHelp(jsonObject);
        Call<SupportRetrofitResponse> call = apiInterface.fetchHelp(jsonObject);

//        Call<SupportRetrofitResponse> call = apiInterface.fetchHelp(requestBody);

        call.enqueue(new Callback<SupportRetrofitResponse>() {
            @Override
            public void onResponse(Call<SupportRetrofitResponse> call, Response<SupportRetrofitResponse> response) {

                profileDetailsResponse = response.body();

//                SupportRetrofitResponse helpResponse = response.body();

//                if (helpResponse != null && helpResponse.getResult() != null) {
//                    SupportRetrofitResponse.SupportRetrofitModel helpModel = helpResponse.getResult();

                if (profileDetailsResponse != null && profileDetailsResponse.getResult() != null) {

//                        SupportRetrofitResponse.SupportRetrofitModel helpModel = (SupportRetrofitResponse.SupportRetrofitModel) helpResponse.getResult();
//                        SupportRetrofitResponse.SupportRetrofitModel helpModel =  profileDetailsResponse.getResult();

//                    tvcall1.setText(helpModel.getMobile());
//                    tvemail.setText(helpModel.getEmail());
//                    tvlink.setText(helpModel.getAddress());

                    tvcall1.setText(profileDetailsResponse.getResult().getMobile());
                    tvemail.setText(profileDetailsResponse.getResult().getEmail());
                    tvlink.setText(profileDetailsResponse.getResult().getAddress());

                }
                CommonUtils.hideLoading();
            }

            @Override
            public void onFailure(Call<SupportRetrofitResponse> call, Throwable t) {
                t.printStackTrace();
                CommonUtils.hideLoading();
            }
        });

    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.ll_email:
//                Intent intent = new Intent(Intent.ACTION_SEND);
//                intent.setType("plain/text");
//                intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{tvemail.getText().toString()});
//                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
//                intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
//                /* Send it off to the Activity-Chooser */
//                startActivity(Intent.createChooser(intent, "Send Email"));
//                break;
//            case R.id.ll_whatsapp:
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                try {
//                    String url = "https://api.whatsapp.com/send?phone=" + tvcall2.getText().toString() + "&text=" + URLEncoder.encode("", "UTF-8");
//                    i.setPackage("com.whatsapp");
//                    i.setData(Uri.parse(url));
//                    if (i.resolveActivity(getPackageManager()) != null) {
//                        startActivity(i);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                break;
//            case R.id.ll_phone:
//                calltoNo(tvcall1.getText().toString());
//                break;
//        }
//    }

    String phoneNumberToDail = " ";

    private void calltoNo(String phoneNumber) {
        if ((Build.VERSION.SDK_INT >= 23) && ActivityCompat.checkSelfPermission(SupportActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            phoneNumberToDail = phoneNumber;
            ActivityCompat.requestPermissions(SupportActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQ_CALL);
        } else {
            try {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //permissions result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_CALL) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                try {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumberToDail));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }


    //HttpUtrlConnection
    public void SetTextViews() {

        try {

//        tvcall1,tvcall2, tvemail, tvlink

//        tvId.setText("");
//        tvId.append(String.valueOf(id));

//        SupportModel supportModel = new SupportModel();
//        SupportModel supportModel = supportCategoryModalList.get();


//        tvcall1.setText("");
//        tvcall1.append(String.valueOf(id));
//        tvcall1.append(String.valueOf(support_mobile));

//        tvcall1.setText(String.valueOf(supportModel.getMobile()));


//        tvPhone.setText("");
//        tvPhone.append(String.valueOf(Variables.mobile));

//        tvcall2.setText("");
//        tvcall2.append(String.valueOf(support_mobile));

//        tvcall2.setText(String.valueOf(supportModel.getMobile()));


//        tvEmail.setText("");
//        tvEmail.append(String.valueOf(Variables.email));

//        tvemail.setText("");
//        tvemail.append(String.valueOf(support_email));

//        tvemail.setText(String.valueOf(supportModel.getEmail()));


//        tvAddress.setText("");
//        tvAddress.append(String.valueOf(Variables.address));

//        tvlink.setText("");
//        tvlink.append(String.valueOf(support_address));

//        tvlink.setText(String.valueOf(supportModel.getAddress()));

//            tvcall1.setText((CharSequence) supportCategoryModalList.getSupport_mobile());
//            tvcall1.setText((CharSequence) profileGetUserDataModel_http.getMobile());

            tvcall1.setText((CharSequence) profileDetailsResponse.getResult().getMobile());


//            tvcall2.setText((CharSequence) supportCategoryModalList.getSupport_mobile());

            tvcall2.setText((CharSequence) profileDetailsResponse.getResult().getMobile());


//            tvemail.setText((CharSequence) supportCategoryModalList.getSupport_email());

            tvemail.setText((CharSequence) profileDetailsResponse.getResult().getEmail());


//            tvlink.setText((CharSequence) supportCategoryModalList.getSupport_address());

            tvlink.setText((CharSequence) profileDetailsResponse.getResult().getAddress());


//            SupportApi getRecentViewProductsAPI = new SupportApi(this);
//            getRecentViewProductsAPI.HitSupportApi();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setdata() {

        try {

            tvcall1.setText((CharSequence) supportCategoryModalList.getSupport_mobile());

            tvcall2.setText((CharSequence) supportCategoryModalList.getSupport_mobile());

            tvemail.setText((CharSequence) supportCategoryModalList.getSupport_email());

            tvlink.setText((CharSequence) supportCategoryModalList.getSupport_address());

//            spinner_district_http.setText((CharSequence) profileGetUserDataModel_http.getDistrict());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void SetClickListeners() {

        tvlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(i);

                //-----

//                launchDialer2();
//                if (ActivityCompat.checkSelfPermission(SupportActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//
//                    return;
//                }

                //---

//                String address = "123 Main St, City"; // Replace with the actual address

//                tvlink.setOnClickListener(v -> openInMaps(address));
//                tvlink.setOnClickListener(v -> openInApp("com.ubercab", address));
//                tvlink.setOnClickListener(v -> openInApp("com.olacabs.customer", address));
//                tvlink.setOnClickListener(v -> openInApp("com.rapido.rider", address));
//                tvlink.setOnClickListener(v -> openInApp("com.application.zomato", address));
//                tvlink.setOnClickListener(v -> openInApp("com.application.zoom", address));

                openInMaps(address);
                openInApp("com.ubercab", address);
                openInApp("com.olacabs.customer", address);
                openInApp("com.rapido.rider", address);
                openInApp("com.application.zomato", address);
                openInApp("com.application.zoom", address);


                //---

                // Open location in Google Maps
                Uri gmmIntentUri = Uri.parse("geo:latitude,longitude?q=place_name");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                } else {
                    // Google Maps app is not installed, handle accordingly
                    // You can open the location in a web browser or display a message to the user
                }

// Open location in Uber
                Uri uberUri = Uri.parse("uber://?client_id=YOUR_CLIENT_ID&action=setPickup&pickup[latitude]=latitude&pickup[longitude]=longitude&pickup[nickname]=pickup_location");
                Intent uberIntent = new Intent(Intent.ACTION_VIEW, uberUri);

                if (uberIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(uberIntent);
                } else {
                    // Uber app is not installed, handle accordingly
                    // You can prompt the user to install the Uber app or provide alternative options
                }


            }

            private void openInMaps(String address) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps"); // Google Maps
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }

            private void openInApp(String packageName, String address) {
                Intent appIntent = getPackageManager().getLaunchIntentForPackage(packageName);
                if (appIntent != null) {
                    Uri uri = Uri.parse("geo:0,0?q=" + Uri.encode(address));
                    appIntent.setAction(Intent.ACTION_VIEW);
                    appIntent.setData(uri);
                    startActivity(appIntent);
                } else {
                    // The app is not installed, handle accordingly
                }
            }

        });


        tvemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@prakruthiepl.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");

                startActivity(Intent.createChooser(intent, "Email via..."));
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);

            }
        });


        tvcall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDialer();
                if (ActivityCompat.checkSelfPermission(SupportActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }

            }
        });

        tvcall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDialer2();
                if (ActivityCompat.checkSelfPermission(SupportActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }

            }
        });


    }

    private void launchDialer() {
        // No permisison needed
        Intent callIntent = new Intent(Intent.ACTION_DIAL);

        callIntent.setData(Uri.parse("tel:" + getPhoneNumber()));
        startActivity(callIntent);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);

    }

    private void launchDialer2() {
        // No permisison needed
        Intent callIntent = new Intent(Intent.ACTION_DIAL);

        callIntent.setData(Uri.parse("tel:" + getPhoneNumber2()));
        startActivity(callIntent);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);

    }


    private String getPhoneNumber() {

        tvcall1 = (TextView) findViewById(R.id.tvcall1);

        return tvcall1.getText().toString();
    }

    private String getPhoneNumber2() {
        tvcall2 = (TextView) findViewById(R.id.tvcall2);

        return tvcall2.getText().toString();
    }


    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.bottom_down, R.anim.bottom_down);
    }


    public void UserDetailsUpdateApi() {

        SupportApi supportApi = new SupportApi(this);
        supportApi.HitSupportApi();


    }


    @Override
//    public void OnSupportApiGivesSuccess(ArrayList<ProfileSupportResponse.ProfileSupportModel> profileSupportModels) {
    public void OnSupportApiGivesSuccess(ArrayList<SupportModel> supportModels) {

        try {
            SupportActivity.this.runOnUiThread(() -> {
//                UserDetailsUpdateApi();
                Log.e("TAG", String.valueOf(supportModels));

//                UserDetailsUpdateApi();

                SupportApi getUserDataApi = new SupportApi(this);
                getUserDataApi.HitSupportApi();

            });
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }

    }

    @Override
    public void OnSupportApiGivesError(String error) {
        try {
            SupportActivity.this.runOnUiThread(() -> {


                try {
//                    UserDetailsUpdateApi();

                    Toast.makeText(SupportActivity.this, error, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                PopupDialog.getInstance(SupportActivity.this).setStyle(Styles.FAILED).setHeading("Uh-Oh").setDescription("Unexpected error occurred." + " Try again later.").setCancelable(false).showDialog(new OnDialogButtonClickListener() {
                    @Override
                    public void onDismissClicked(Dialog dialog) {
                        super.onDismissClicked(dialog);
                    }
                });

            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressLint("StaticFieldLeak")
    public void ApiTaskPro() {
        Loading.show(SupportActivity.this);
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                //Creating array for parameters
                String[] field = new String[2];
                field[0] = "user_id";
                field[1] = "token";

                //Creating array for data
                String[] data = new String[2];
                data[0] = userId;
                data[1] = token;
                PutData putData = new PutData(Variables.BaseUrl + "support", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        // result = Api Result
                        String result = putData.getResult();
                        return result;
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);


                if (result != null) {
                    try {
                        Log.e(TAG, result);
                        JSONObject json = new JSONObject(result);
                        boolean statusCode = json.getBoolean("status_code");
                        String message = json.getString("message");
                        if (statusCode) {
                            Toast.makeText(SupportActivity.this, message, Toast.LENGTH_SHORT).show();
                            getUserData(json);
                        } else {
                            Toast.makeText(SupportActivity.this, message, Toast.LENGTH_SHORT).show();

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(SupportActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SupportActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                }
                Loading.hide();
            }
        }.execute();

    }


    public void getUserData(JSONObject ResultJson) {
        try {
            String status_code = ResultJson.getString("status_code");
            String message = ResultJson.getString("message");

            JSONArray userDetailsArray = ResultJson.getJSONArray("result");

            JSONObject userDetails = userDetailsArray.getJSONObject(0);

            String id = String.valueOf(userDetails.getInt("id"));
            String name = userDetails.optString("support_mobile", "");
            String email = userDetails.optString("support_email", "");
            String address = userDetails.optString("support_address", "");


            // Store values in static variables
            Variables.clear();

            Variables.status_code = status_code;
            Variables.message = message;

            Variables.id = Integer.valueOf(String.valueOf(id));
            Variables.support_mobile = support_mobile;
            Variables.support_email = support_email;
            Variables.support_address = support_address;

            Loading.hide();

            startActivity(new Intent(this, SupportActivity.class));
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
            Toast.makeText(this, "Internal Error", Toast.LENGTH_SHORT).show();

        }

    }


}