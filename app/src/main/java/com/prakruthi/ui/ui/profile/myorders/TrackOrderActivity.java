package com.prakruthi.ui.ui.profile.myorders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.prakruthi.R;
import com.prakruthi.ui.APIs.InvoiceProductsApi;
import com.prakruthi.ui.APIs.TrackingOrdersApi;
import com.prakruthi.ui.Api.API_class;
import com.prakruthi.ui.Api.Retrofit_funtion_class;
import com.prakruthi.ui.Variables;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//public class TrackOrderActivity extends AppCompatActivity implements TrackingOrdersApi.OnTrackingOrdersApiHitsListener {
public class TrackOrderActivity extends AppCompatActivity {

    ProgressDialog progressDoalog;
    String cart_id;
    AppCompatButton back;
    RelativeLayout my_tracker_orders_RelativeLayout2;

    ImageView viewOrderConfirmPlaced11,viewOrderConfirmPlaced,viewPlaceSupporterConfirmOrder,viewOrderConfirmedSupporterProductPrepared,viewOrderProcessedShipped;
    //    View viewOrderConfirmPlaced, viewPlaceSupporterConfirmOrder;
    TextView textConfirmOrderPlaced, textConfirmOrderPlaced_Date_Time_Des;

    ImageView viewOrderConfirmedProductPrepared;
    //    View viewOrderConfirmedProductPrepared, viewOrderConfirmedSupporterProductPrepared;
    TextView textProductPreparedOrderConfirmed, textProductPreparedOrderConfirmedDate;

    View viewProcessedSupporterShipped;
    //    View viewOrderProcessedShipped, viewProcessedSupporterShipped;
    TextView textOrderProcessedShipped, textOrderProcessedShippedDate;

    View viewOrderReadyOutForDelivery,viewReadySupporterOutForDelivery;
    TextView textReadyToPickupOutForDelivery, textReadyToPickupOutForDeliveryDate;

    View viewOrderPlaced23DeliveredShipped,viewPlaceSupporter23DeliveredShipped;
    //    TextView textShippedDeliveredShipped, textShippedDeliveredShipped_Date3;
    TextView textShippedDeliveredShipped,textShippedDeliveredShipped_Date3,purchases_date_time,txt_order_hash;


    public TrackingOrdersModel trackingOrdersModels;

    String placed, Confirmed, Dispatched, Arrived, Outfordelivery;

//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//    // Assuming you have a date/time string from the API response
//    String apiDateTimeString = "2023-09-14 15:30:00";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_track_orders);
        setContentView(R.layout.activity_track_orders1);


//        cart_id = getIntent().getStringExtra("cart_id");
        cart_id = getIntent().getStringExtra("Tracking_id");


        my_tracker_orders_RelativeLayout2 = findViewById(R.id.my_tracker_orders_RelativeLayout2);
        purchases_date_time = findViewById(R.id.purchases_date_time);
        txt_order_hash = findViewById(R.id.txt_order_hash);

        //ConfirmOrder
        viewOrderConfirmPlaced = findViewById(R.id.viewOrderConfirmPlaced);
        viewOrderConfirmPlaced11 = findViewById(R.id.viewOrderConfirmPlaced11);
        viewPlaceSupporterConfirmOrder = findViewById(R.id.viewPlaceSupporterConfirmOrder);
        textConfirmOrderPlaced = findViewById(R.id.textConfirmOrderPlaced);
        textConfirmOrderPlaced_Date_Time_Des = findViewById(R.id.textConfirmOrderPlaced_Date_Time_Des);

        //ProductPrepared
        viewOrderConfirmedProductPrepared = findViewById(R.id.viewOrderConfirmedProductPrepared);
        viewOrderConfirmedSupporterProductPrepared = findViewById(R.id.viewOrderConfirmedSupporterProductPrepared);
        textProductPreparedOrderConfirmed = findViewById(R.id.textProductPreparedOrderConfirmed);
        textProductPreparedOrderConfirmedDate = findViewById(R.id.textProductPreparedOrderConfirmedDate);

        //Shipped
        viewOrderProcessedShipped = findViewById(R.id.viewOrderProcessedShipped);
        viewProcessedSupporterShipped = findViewById(R.id.viewProcessedSupporterShipped);
        textOrderProcessedShipped = findViewById(R.id.textOrderProcessedShipped);
        textOrderProcessedShippedDate = findViewById(R.id.textOrderProcessedShippedDate);

        //OutForDelivery
        viewOrderReadyOutForDelivery = findViewById(R.id.viewOrderReadyOutForDelivery);
        viewReadySupporterOutForDelivery = findViewById(R.id.viewReadySupporterOutForDelivery);
        textReadyToPickupOutForDelivery = findViewById(R.id.textReadyToPickupOutForDelivery);
        textReadyToPickupOutForDeliveryDate = findViewById(R.id.textReadyToPickupOutForDeliveryDate);

        //ShippedDelivered
        viewOrderPlaced23DeliveredShipped = findViewById(R.id.viewOrderPlaced23DeliveredShipped);
        viewPlaceSupporter23DeliveredShipped = findViewById(R.id.viewPlaceSupporter23DeliveredShipped);
        textShippedDeliveredShipped = findViewById(R.id.textShippedDeliveredShipped);
        textShippedDeliveredShipped_Date3 = findViewById(R.id.textShippedDeliveredShipped_Date3);

        back = findViewById(R.id.tracking_order_back_btn);


        SetViews();
        CallApi();

        //Sriniwas
        paymetstaus();

    }

//        TrackingOrdersModel trackingOrdersModel = new TrackingOrdersModel();


// Get the current date and time
//        Calendar calendar = Calendar.getInstance();
//        Date dateTime = calendar.getTime();
//
//// Now you have a Date object representing the current date and time
//
//        // Create a SimpleDateFormat instance to format the date and time
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//
//        // Assuming you have a date and time in a variable named "dateTime" of type Date
//        String yourDateTimeString = sdf.format(dateTime);
//
//        // Update the TextView with the formatted date and time
//        textConfirmOrderPlaced_Date_Time_Des.setText("Date/Time: " + yourDateTimeString);
//
//
//        // Assuming you have a date/time string from the API response
//        String apiDateTimeString = "2023-09-14 15:30:00";
//
//// Parse the API date/time string into a Date object
////        Date dateTime = null;
//
//        try {
//            SimpleDateFormat apiSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//            dateTime = apiSdf.parse(apiDateTimeString);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // Check if parsing was successful before formatting
//        if (dateTime != null) {
//            // Format the Date object into the desired format
////            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
////            String yourDateTimeString = sdf.format(dateTime);
//// Update the TextView with the formatted date and time
//            textConfirmOrderPlaced_Date_Time_Des.setText("Date/Time: " + yourDateTimeString);
//        } else {
//            // Handle parsing error
//            // You can set a default value or display an error message
//            textConfirmOrderPlaced_Date_Time_Des.setText("Date/Time: N/A");
//        }
//
//    }

    private void CallApi() {
//        TrackingOrdersApi trackingOrdersApi = new TrackingOrdersApi(this, cart_id);
//        trackingOrdersApi.HitTrackingOrdersApi();

//        recyclerView_MyPaymentsInvoiceProducts.showShimmerAdapter();

    }

    private void SetViews() {
//        recyclerView_MyPaymentsInvoiceProducts = findViewById(R.id.my_payments_invoice_products_recyclerview_List);

        back = findViewById(R.id.tracking_order_back_btn);


    }


//    Dynamic Tracking Order(Retrofit/Sriniwas):-
    private void paymetstaus() {
        progressDoalog = new ProgressDialog(TrackOrderActivity.this);
        progressDoalog.setCancelable(false);
        progressDoalog.setMessage("Loading....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
        String ContentType = "application/json";
        String Accept = "application/json";
        final API_class service = Retrofit_funtion_class.getClient().create(API_class.class);
        Call<JsonElement> callRetrofit = null;
//        callRetrofit = service.AKRUTHTEST1(String.valueOf(Variables.id),Variables.token,invoice_num);

        //paymentCheck
        callRetrofit = service.TrackOrderStatus(String.valueOf(Variables.id),Variables.token,cart_id);

        callRetrofit.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                System.out.println("----------------------------------------------------");
                Log.d("Call request", call.request().toString());
                Log.d("Call request header", call.request().headers().toString());
                Log.d("Response raw header", response.headers().toString());
                Log.d("Response raw", String.valueOf(response.raw().body()));
                Log.d("Response code", String.valueOf(response.code()));

                System.out.println("----------------------------------------------------");

                if (response.isSuccessful()) {
                    progressDoalog.dismiss();
                    String searchResponse = response.body().toString();
                    Log.d("paymetstaus", searchResponse);
                    try {
//                        JSONObject lObj = new JSONObject(searchResponse);
                        try {
                            JSONObject lObj = new JSONObject(searchResponse);
                            JSONObject lobj1=lObj.getJSONObject("satges");
                            String Placed1=lobj1.getString("0");
                            textConfirmOrderPlaced.setText(Placed1);
                            String Confirmed_=lobj1.getString("1");
                            textProductPreparedOrderConfirmed.setText(Confirmed_);
                            String Dispatched_=lobj1.getString("2");
                            textOrderProcessedShipped.setText(Dispatched_);
                            String Arrived_=lobj1.getString("3");
                            textReadyToPickupOutForDelivery.setText(Arrived_);
                            String delivery_=lobj1.getString("4");
                            textShippedDeliveredShipped.setText(delivery_);
                            String Placed=lObj.getString("Placed");
                            if (!Placed.equalsIgnoreCase("false")) {
                                viewOrderConfirmPlaced11.setVisibility(View.VISIBLE);
                            } else {
                                viewOrderConfirmPlaced11.setVisibility(View.GONE);
                            }
                            String Confirmed=lObj.getString("Confirmed");
                            String Dispatched=lObj.getString("Dispatched");
                            String Arrived=lObj.getString("Arrived");
                            String Outfordelivery=lObj.getString("Out for delivery");
                            String last_updated_date=lObj.getString("last_updated_date");
                            String order_number=lObj.getString("order_number");

                            purchases_date_time.setText("Last updated date : "+last_updated_date);
                            txt_order_hash.setText("ORDER #"+order_number);

                            if (!Confirmed.equalsIgnoreCase("false")) {
//                                viewOrderConfirmPlaced11.setVisibility(View.VISIBLE);
                                viewOrderConfirmedProductPrepared.setVisibility(View.VISIBLE);
//                                viewOrderConfirmPlaced.setBackgroundResource(R.drawable.confirmed_product_prepared_ellipse29);
                            } else {
                                viewOrderConfirmedProductPrepared.setVisibility(View.GONE);

//                                viewOrderConfirmPlaced.setBackgroundResource(R.drawable.confirmed_product_prepared_ellipse277);
                            }
                            if (!Dispatched.equalsIgnoreCase("false")) {
                                viewOrderProcessedShipped.setVisibility(View.VISIBLE);
                            } else {
                                viewOrderProcessedShipped.setVisibility(View.GONE);
                            }
                            if (!Arrived.equalsIgnoreCase("false")) {
                                viewOrderReadyOutForDelivery.setVisibility(View.VISIBLE);
                            } else {
                                viewOrderReadyOutForDelivery.setVisibility(View.GONE);
                            }
                            if (!Outfordelivery.equalsIgnoreCase("false")) {
                                viewOrderPlaced23DeliveredShipped.setVisibility(View.VISIBLE);
                            } else {
                                viewOrderPlaced23DeliveredShipped.setVisibility(View.GONE);
                            }
                            //if (status_code.equalsIgnoreCase("true")) {



                        } catch (Exception e) {
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    progressDoalog.dismiss();
                    if (!response.isSuccessful()) {
                        InputStream i = response.errorBody().byteStream();
                        BufferedReader r = new BufferedReader(new InputStreamReader(i));
                        StringBuilder errorResult = new StringBuilder();
                        String line;
                        try {
                            while ((line = r.readLine()) != null) {

                                errorResult.append(line).append('\n');
                                try {
                                    JSONObject jsonObject = new JSONObject(line);
                                    jsonObject.getString("message");
                                    Log.d("lineappende >>>>  ", "lineapends  >>> " + jsonObject.getString("message"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                Log.d("line", "line" + errorResult.append("message" + line));
                                Log.d("searchResponse", "searchResponse" + errorResult.append(line).append('\n'));


                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }


            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.d("Error Call", ">>>>" + call.toString());
                Log.d("Error", ">>>>" + t.toString());
            }
        });

    }

    //-----------------------------------------------------------------------------------------------

    //    Static Tracking Order(Retrofit/Sriniwas):------------------------

//    private void paymetstaus() {
//        progressDoalog = new ProgressDialog(TrackOrderActivity.this);
//        progressDoalog.setCancelable(false);
//        progressDoalog.setMessage("Loading....");
//        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDoalog.show();
//        String ContentType = "application/json";
//        String Accept = "application/json";
//        final API_class service = Retrofit_funtion_class.getClient().create(API_class.class);
//        Call<JsonElement> callRetrofit = null;
////        callRetrofit = service.AKRUTHTEST1(String.valueOf(Variables.id),Variables.token,invoice_num);
//
//        //paymentCheck
//        callRetrofit = service.TrackOrderStatus(String.valueOf(Variables.id),Variables.token,cart_id);
//
//        callRetrofit.enqueue(new Callback<JsonElement>() {
//            @Override
//            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
//
//                System.out.println("----------------------------------------------------");
//                Log.d("Call request", call.request().toString());
//                Log.d("Call request header", call.request().headers().toString());
//                Log.d("Response raw header", response.headers().toString());
//                Log.d("Response raw", String.valueOf(response.raw().body()));
//                Log.d("Response code", String.valueOf(response.code()));
//
//                System.out.println("----------------------------------------------------");
//
//                if (response.isSuccessful()) {
//                    progressDoalog.dismiss();
//                    String searchResponse = response.body().toString();
//                    Log.d("Regisigup", searchResponse);
//                    try {
////                        JSONObject lObj = new JSONObject(searchResponse);
//                        try {
//                            JSONObject lObj = new JSONObject(searchResponse);
//                            String Placed=lObj.getString("Placed");
//                            if (Placed.equalsIgnoreCase("false")) {
//                                viewPlaceSupporterConfirmOrder.setVisibility(View.INVISIBLE);
//                            } else {
//                                viewPlaceSupporterConfirmOrder.setVisibility(View.GONE);
//                            }
//                            String Confirmed=lObj.getString("Confirmed");
//                            String Dispatched=lObj.getString("Dispatched");
//                            String Arrived=lObj.getString("Arrived");
//                            String Outfordelivery=lObj.getString("Out for delivery");
//
//                            if (Confirmed.equalsIgnoreCase("false")) {
//                                viewPlaceSupporterConfirmOrder.setVisibility(View.INVISIBLE);
//                            } else {
//                                viewPlaceSupporterConfirmOrder.setVisibility(View.GONE);
//                            }
//                            if (Dispatched.equalsIgnoreCase("false")) {
//                                viewPlaceSupporterConfirmOrder.setVisibility(View.INVISIBLE);
//                            } else {
//                                viewPlaceSupporterConfirmOrder.setVisibility(View.GONE);
//                            }
//                            if (Arrived.equalsIgnoreCase("false")) {
//                                viewPlaceSupporterConfirmOrder.setVisibility(View.INVISIBLE);
//                            } else {
//                                viewPlaceSupporterConfirmOrder.setVisibility(View.GONE);
//                            }
//                            if (Outfordelivery.equalsIgnoreCase("false")) {
//                                viewPlaceSupporterConfirmOrder.setVisibility(View.INVISIBLE);
//                            } else {
//                                viewPlaceSupporterConfirmOrder.setVisibility(View.GONE);
//                            }
//                            //if (status_code.equalsIgnoreCase("true")) {
//
//
//
//                        } catch (Exception e) {
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                } else {
//                    progressDoalog.dismiss();
//                    if (!response.isSuccessful()) {
//                        InputStream i = response.errorBody().byteStream();
//                        BufferedReader r = new BufferedReader(new InputStreamReader(i));
//                        StringBuilder errorResult = new StringBuilder();
//                        String line;
//                        try {
//                            while ((line = r.readLine()) != null) {
//
//                                errorResult.append(line).append('\n');
//                                try {
//                                    JSONObject jsonObject = new JSONObject(line);
//                                    jsonObject.getString("message");
//                                    Log.d("lineappende >>>>  ", "lineapends  >>> " + jsonObject.getString("message"));
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//
//                                Log.d("line", "line" + errorResult.append("message" + line));
//                                Log.d("searchResponse", "searchResponse" + errorResult.append(line).append('\n'));
//
//
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//
//
//            @Override
//            public void onFailure(Call<JsonElement> call, Throwable t) {
//                Log.d("Error Call", ">>>>" + call.toString());
//                Log.d("Error", ">>>>" + t.toString());
//            }
//        });
//
//    }



    //----------------------------------------------------------------------------------------------------

//    Static TrackOrderActivity HttpUrlConnection:------------------------------------------------
//    @Override
//    public void OnTrackingOrdersApiGivesFetched(List<TrackingOrdersModel> trackingOrdersModel) {
//        runOnUiThread(() -> {
//            //------
//
////            if (trackingOrdersModels.isSuccessful()) {
//            progressDoalog.dismiss();
////            String searchResponse = response.body().toString();
////            Log.d("Regisigup", searchResponse);
//            Log.d("Regisigup", trackingOrdersModels.toString());
//
//
//            try {
////                        JSONObject lObj = new JSONObject(searchResponse);
//                try {
////                    JSONObject lObj = new JSONObject(searchResponse);
//                    JSONObject lObj = new JSONObject((Map) trackingOrdersModels);
//
//
//                    String Placed = lObj.getString("Placed");
//                    if (Placed.equalsIgnoreCase("false")) {
//                        viewPlaceSupporterConfirmOrder.setVisibility(View.INVISIBLE);
//                    } else {
//                        viewPlaceSupporterConfirmOrder.setVisibility(View.GONE);
//                    }
//                    String Confirmed = lObj.getString("Confirmed");
//                    String Dispatched = lObj.getString("Dispatched");
//                    String Arrived = lObj.getString("Arrived");
//                    String Outfordelivery = lObj.getString("Out for delivery");
//
//                    if (Confirmed.equalsIgnoreCase("false")) {
//                        viewPlaceSupporterConfirmOrder.setVisibility(View.INVISIBLE);
//                    } else {
//                        viewPlaceSupporterConfirmOrder.setVisibility(View.GONE);
//                    }
//                    if (Dispatched.equalsIgnoreCase("false")) {
//                        viewPlaceSupporterConfirmOrder.setVisibility(View.INVISIBLE);
//                    } else {
//                        viewPlaceSupporterConfirmOrder.setVisibility(View.GONE);
//                    }
//                    if (Arrived.equalsIgnoreCase("false")) {
//                        viewPlaceSupporterConfirmOrder.setVisibility(View.INVISIBLE);
//                    } else {
//                        viewPlaceSupporterConfirmOrder.setVisibility(View.GONE);
//                    }
//                    if (Outfordelivery.equalsIgnoreCase("false")) {
//                        viewPlaceSupporterConfirmOrder.setVisibility(View.INVISIBLE);
//                    } else {
//                        viewPlaceSupporterConfirmOrder.setVisibility(View.GONE);
//                    }
//                    //if (status_code.equalsIgnoreCase("true")) {
//
//
//                } catch (Exception e) {
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }




//            }
//            else {
//                progressDoalog.dismiss();
//                if (!trackingOrdersModels.isSuccessful()) {
//                    InputStream i = trackingOrdersModels.errorBody().byteStream();
//                    BufferedReader r = new BufferedReader(new InputStreamReader(i));
//                    StringBuilder errorResult = new StringBuilder();
//                    String line;
//                    try {
//                        while ((line = r.readLine()) != null) {
//
//                            errorResult.append(line).append('\n');
//                            try {
//                                JSONObject jsonObject = new JSONObject(line);
//                                jsonObject.getString("message");
//                                Log.d("lineappende >>>>  ", "lineapends  >>> " + jsonObject.getString("message"));
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                            Log.d("line", "line" + errorResult.append("message" + line));
//                            Log.d("searchResponse", "searchResponse" + errorResult.append(line).append('\n'));
//
//
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }


            //------------------------------HttpUrlConnection
//            TrackOrderActivity

//            Placed
//            Confirmed
//            Dispatched
//            Arrived
//            Out for delivery

            // Check if trackingOrdersModels is not empty (assuming you only have one set of tracking data)


//            Outfordelivery
//            Out_for_delivery

//            TrackingOrdersModel trackingData = new TrackingOrdersModel(placed,Confirmed,Dispatched,Arrived,Outfordelivery);

//            if (!trackingOrdersModels.isEmpty()) {
//                TrackingOrdersModel trackingData = trackingOrdersModels.get(0); // Assuming you have only one set of tracking data

//            if (trackingData != null) {
//            if (trackingOrdersModels != null) {
//
//
//
////            TrackingOrdersModel trackingData = trackingOrdersModels.getPlaced(); // Assuming you have only one set of tracking data
////            TrackingOrdersModel trackingData = trackingOrdersModels.getConfirmed(); // Assuming you have only one set of tracking data
////            TrackingOrdersModel trackingData = trackingOrdersModels.getDispatched(); // Assuming you have only one set of tracking data
////            TrackingOrdersModel trackingData = trackingOrdersModels.getArrived(); // Assuming you have only one set of tracking data
////            TrackingOrdersModel trackingData = trackingOrdersModels.getOutfordelivery(); // Assuming you have only one set of tracking data
//
//
//                // Update UI components based on tracking data
////                updateUIComponents(trackingData);
////                updateUIWithTrackingData(trackingData);
//                updateUIWithTrackingData(trackingOrdersModels);
//
//
//
//            }

            //---------------------

//            String TextUtils = AvtQtyRemainingEditText.getText().toString();
//            String trackingData = textConfirmOrderPlaced.getText().toString();


//            if (!trackingData.isEmpty()) {
//                TrackingOrdersModel trackingData = trackingOrdersModels.get(0); // Assuming you have only one set of tracking data
//
//
////            TrackingOrdersModel trackingData = trackingOrdersModels.getPlaced(); // Assuming you have only one set of tracking data
////            TrackingOrdersModel trackingData = trackingOrdersModels.getConfirmed(); // Assuming you have only one set of tracking data
////            TrackingOrdersModel trackingData = trackingOrdersModels.getDispatched(); // Assuming you have only one set of tracking data
////            TrackingOrdersModel trackingData = trackingOrdersModels.getArrived(); // Assuming you have only one set of tracking data
////            TrackingOrdersModel trackingData = trackingOrdersModels.getOutfordelivery(); // Assuming you have only one set of tracking data
//
//
//                // Update UI components based on tracking data
////                updateUIComponents(trackingData);
//                updateUIWithTrackingData(trackingData);
//
//            }

//            else {
//                displayErrorMessage("No tracking data available.");
//            }






//        });
//    }

    private void updateUIComponents(TrackingOrdersModel trackingData) {
        // Update "Placed" view
        if (trackingData.getPlaced()) {
            viewOrderConfirmPlaced.setBackgroundColor(Color.GREEN); // Set a green background or another indicator
        } else {
            viewOrderConfirmPlaced.setBackgroundColor(Color.RED); // Set a red background or another indicator
        }

        // Update "Confirmed" view
        if (trackingData.getConfirmed()) {
            viewOrderConfirmedProductPrepared.setBackgroundColor(Color.GREEN); // Set a green background or another indicator
        } else {
            viewOrderConfirmedProductPrepared.setBackgroundColor(Color.RED); // Set a red background or another indicator
        }

        // Update other views (Dispatched, Arrived, Out for delivery) in a similar way

        // You can also update the TextViews with date/time information
//        textConfirmOrderPlaced_Date_Time_Des.setText("Date/Time: " + yourDateTimeString);
//        textProductPreparedOrderConfirmedDate.setText("Date/Time: " + yourDateTimeString);
        // Update date/time TextViews for other tracking states

        // Add logic to hide or show views based on the tracking data if needed


    }

    //------
    private void updateUIWithTrackingData(TrackingOrdersModel trackingData) {
        // Update UI components with tracking data
        textConfirmOrderPlaced.setText("Placed: " + trackingData.getPlaced());
        textProductPreparedOrderConfirmed.setText("Confirmed: " + trackingData.getConfirmed());
        textOrderProcessedShipped.setText("Dispatched: " + trackingData.getDispatched());
        textReadyToPickupOutForDelivery.setText("Arrived: " + trackingData.getArrived());
        textShippedDeliveredShipped_Date3.setText("Out for delivery: " + trackingData.getOutfordelivery());
    }

    private void displayErrorMessage(String errorMessage) {
        // Display an error message using a dialog or toast
        PopupDialog.getInstance(this)
                .setStyle(Styles.ALERT)
                .setDismissButtonBackground(R.color.Primary)
                .setHeading("Error")
                .setDescription(errorMessage)
                .setCancelable(false)
                .showDialog(new OnDialogButtonClickListener() {
                    @Override
                    public void onDismissClicked(Dialog dialog) {
                        super.onDismissClicked(dialog);
                        // Handle dismiss action if needed
                    }
                });
        // You can also use Toast to display the error message
        // Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void OnTrackingOrdersApiGivesError(String error) {
//        runOnUiThread(() -> {
////            recyclerView_MyPaymentsInvoiceProducts.hideShimmerAdapter();
//
//            displayErrorMessage(error);
//
//
//            PopupDialog.getInstance(this)
//                    .setStyle(Styles.ALERT)
//                    .setDismissButtonBackground(R.color.Primary)
//                    .setHeading("Uh-Oh")
//                    .setDescription("There Are No Products" +
//                            " In Your Account.")
//                    .setCancelable(false)
//                    .showDialog(new OnDialogButtonClickListener() {
//                        @Override
//                        public void onDismissClicked(Dialog dialog) {
//                            super.onDismissClicked(dialog);
//                        }
//                    });
//        });
//
//    }

}