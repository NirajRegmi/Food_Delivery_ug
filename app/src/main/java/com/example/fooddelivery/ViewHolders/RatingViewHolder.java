package com.example.fooddelivery.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.example.fooddelivery.R;
import com.example.mylibrary.ReviewItem;

import static com.example.mylibrary.SharedClass.fooddelivery_PATH;

public class RatingViewHolder extends RecyclerView.ViewHolder {
    private TextView name, comment;
    private RatingBar ratingBar;
    private ImageView img;
    private View view;


    public RatingViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        name = itemView.findViewById(R.id.rating_item_name);
        comment  = itemView.findViewById(R.id.rating_item_comment);
        ratingBar = itemView.findViewById(R.id.ratingbaritem);
        img = itemView.findViewById(R.id.rating_item_img);
    }
    public View getView() {
        return view;
    }

    public void setData (ReviewItem ri) {
        name.setText(ri.getName());
        if (ri.getComment() != null) {
            comment.setText(ri.getComment());
        } else {
            comment.setVisibility(View.GONE);
        }

        Query query = FirebaseDatabase.getInstance().getReference(fooddelivery_PATH).child(ri.getUser_key()).child("fooddelivery_info").child("photoPath");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 if (dataSnapshot.exists()){
                     Glide.with(itemView).load(dataSnapshot.getValue()).into(img);
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         }
        );
        ratingBar.setRating(ri.getStars());
    }
}
