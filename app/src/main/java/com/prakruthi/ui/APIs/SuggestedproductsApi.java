package com.prakruthi.ui.APIs;

import android.util.Log;

import com.prakruthi.ui.Variables;
import com.prakruthi.ui.ui.profile.recentProducts.SuggestedproductsModel;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SuggestedproductsApi {

    String productId;

    private OnSuggestedproductsApiHit mListner;

    public SuggestedproductsApi(OnSuggestedproductsApiHit mListner, String productId) {
        this.mListner = mListner;
        this.productId = productId;
    }

    public void HitSuggestedproductsRecentApi() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new HitSuggestedproductsRecentApis());
    }

    private class HitSuggestedproductsRecentApis implements Runnable {
        @Override
        public void run() {
            String[] field = new String[3];
            field[0] = "user_id";
            field[1] = "token";
            field[2] = "product_id";

            //Creating array for data
            String[] data = new String[3];
            data[0] = String.valueOf(Variables.id);
            data[1] = Variables.token;
            data[2] = productId;

            PutData putData = new PutData(Variables.BaseUrl + "suggestedproducts", "POST", field, data);
            if (putData.startPut()) {
                if (putData.onComplete()) {
                    String result = putData.getResult();
                    //End ProgressBar (Set visibility to GONE)
                    Log.i("PutData", result);
                    try {
                        JSONObject jsonResponse = new JSONObject(result);
                        JSONArray productList = jsonResponse.getJSONArray("products");
                        if (productList.length() != 0) {
                            ArrayList<SuggestedproductsModel> suggestedproductsModels = new ArrayList<>();
                            for (int i = 0; i < productList.length(); i++) {
                                JSONObject product = productList.getJSONObject(i);

                                String id = product.getString("id");
                                String category_id = product.getString("category_id");
                                String subcategory_id = product.getString("subcategory_id");

                                String name = product.getString("name");
                                String description = product.getString("description");

                                String attachment = product.getString("attachment");

                                String actual_price = product.getString("actual_price");
                                String customer_price = product.getString("customer_price");
                                String delar_price = product.getString("delar_price");
                                String mart_price = product.getString("mart_price");

                                String is_trending = product.getString("is_trending");
                                String youtube_link = product.getString("youtube_link");
                                String machine_type = product.getString("machine_type");

                                String color = product.getString("color");
                                String size = product.getString("size");
                                String units = product.getString("units");
                                String type = product.getString("type");
                                String status = product.getString("status");

                                String logged_date = product.getString("logged_date");
                                String created_by = product.getString("created_by");
                                String updated_by = product.getString("updated_by");
                                String updated_date = product.getString("updated_date");

                                String rating = product.getString("rating");
                                String count_rating = product.getString("count_rating");


                                suggestedproductsModels.add(new SuggestedproductsModel(id, category_id, subcategory_id, name, description, attachment, actual_price, customer_price, delar_price, mart_price, is_trending, youtube_link, machine_type, color, size, units, type, status, logged_date, created_by, updated_by, updated_date, rating, count_rating));

                            }

                            mListner.OnsuggestedproductsAPIGivesResult(suggestedproductsModels);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            mListner.OnsuggestedproductsAPIGivesError(jsonObject.getString("message"));
                        } catch (Exception e1) {
                            e1.printStackTrace();
                            mListner.OnsuggestedproductsAPIGivesError("Internal Error");
                        }
                    }

                }
            }
        }
    }

    public interface OnSuggestedproductsApiHit {

        void OnsuggestedproductsAPIGivesResult(ArrayList<SuggestedproductsModel> suggestedproductsModels);

        void OnsuggestedproductsAPIGivesError(String error);
    }

}
