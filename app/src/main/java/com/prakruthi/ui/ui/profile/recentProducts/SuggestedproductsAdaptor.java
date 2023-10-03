package com.prakruthi.ui.ui.profile.recentProducts;

import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prakruthi.R;
import com.prakruthi.ui.Variables;
import com.prakruthi.ui.ui.productPage.ProductPage;
import com.willy.ratingbar.RotationRatingBar;

import java.util.ArrayList;

public class SuggestedproductsAdaptor extends RecyclerView.Adapter<SuggestedproductsAdaptor.ViewHolder> {

//    ArrayList<SuggestedproductsModel> suggestedproductsModels;

    public  static ArrayList<SuggestedproductsModel> suggestedproductsModels;
//    public  static List<SuggestedproductsModel> suggestedproductsModels;


    public SuggestedproductsAdaptor(ArrayList<SuggestedproductsModel> suggestedproductsModels)
    {
        this.suggestedproductsModels = suggestedproductsModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create your view and inflate it from the layout XML
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productdetailspage_product_recycle_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SuggestedproductsModel suggestedproductsModel = suggestedproductsModels.get(position);

        holder.productTextView.setText(suggestedproductsModel.getName());

        Glide.with(holder.itemView.getContext())
                .load(suggestedproductsModel.getAttachment())
                .placeholder(R.drawable.baseline_circle_24) // Optional placeholder image to show while loading the actual image.
                .into(holder.productImageView);

        holder.itemView.setOnClickListener(v -> {
            holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), ProductPage.class)
                    .putExtra("category_id", String.valueOf(suggestedproductsModel.getCategory_id())));
        });

        //--or

//        holder.itemView.setOnClickListener(v -> {
//            holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), ProductPage.class)
//                    .putExtra("subcategory_id", String.valueOf(suggestedproductsModel.getSubcategory_id())));
//        });

        holder.itemView.setOnClickListener(v -> {
            holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), ProductPage.class)
                    .putExtra("productId", String.valueOf(suggestedproductsModel.getId())));
        });



        holder.HomeRatingBar.setRating(Float.parseFloat(suggestedproductsModel.getRating()));
        holder.HomeRatingCount.setText(suggestedproductsModel.getCount_rating());

        if (Variables.departmentId.equals(2))
        {
//            holder.Home_Product_Price.setText(suggestedproductsModel.getCustomer_price());

            holder.Home_Product_Price.setText("₹ ");
            holder.Home_Product_Price.append(suggestedproductsModel.getCustomer_price());

        }

        else if (Variables.departmentId.equals(3))
        {
//            holder.Home_Product_Price.setText(suggestedproductsModel.getDelar_price());
            holder.Home_Product_Price.setText("₹ ");
            holder.Home_Product_Price.append(suggestedproductsModel.getDelar_price());
        } else if (Variables.departmentId.equals(4)) {
//            holder.Home_Product_Price.setText(suggestedproductsModel.getMart_price());
            holder.Home_Product_Price.setText("₹ ");
            holder.Home_Product_Price.append(suggestedproductsModel.getMart_price());
        }
//        holder.Home_Product_Price_real.setText(suggestedproductsModel.getActual_price());
        holder.Home_Product_Price_real.setText("₹ ");
        holder.Home_Product_Price_real.append(suggestedproductsModel.getActual_price());

        holder.Home_Product_Price_real.setPaintFlags(holder.Home_Product_Price_real.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return suggestedproductsModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView productImageView;
        public TextView productTextView , HomeRatingCount , Home_Product_Price , Home_Product_Price_real;
        public RotationRatingBar HomeRatingBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.productdetailspage_product_recycle_view_imageview);
            productTextView = itemView.findViewById(R.id.productdetailspage_product_recycle_view_name_text);
            HomeRatingBar = itemView.findViewById(R.id.productdetailspageRatingBar);
            HomeRatingCount = itemView.findViewById(R.id.productdetailspageRatingCount);
            Home_Product_Price = itemView.findViewById(R.id.productdetailspage_Product_Price);
            Home_Product_Price_real = itemView.findViewById(R.id.productdetailspage_Product_Price_real);
        }
    }
}
