package com.example.testmad;

import android.content.Context;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.Model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter6 extends FirebaseRecyclerAdapter<hotelsModel, myadapter6.myviewholder> {
    //Context context;
    public myadapter6(@NonNull FirebaseRecyclerOptions<hotelsModel> options) {
        super(options);
       // this.context= context;
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final hotelsModel model) {

        holder.name.setText(model.getName());
        holder.rate.setText(model.getRate());
        // holder.details.setText(model.getDetails());

        Glide.with(holder.img.getContext()).load(model.getImageUrl()).into(holder.img);







    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow6, parent, false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView name, rate;
        //Button addToCartTv;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img = (CircleImageView) itemView.findViewById(R.id.img2);
            name = (TextView)itemView.findViewById(R.id.hotelName);
            rate= (TextView)itemView.findViewById(R.id.rate);
            //titleTv = (TextView)itemView.findViewById(R.id.titleTv);
            //descriptionTv = (TextView)itemView.findViewById(R.id.descriptionTv);
            //addToCartTv = (Button)itemView.findViewById(R.id.continueBtn);
            //originalPricetv = (TextView)itemView.findViewById(R.id.fPrice);
            // details= (TextView)itemView.findViewById(R.id.details);
            //Delete= (ImageView)itemView.findViewById(R.id.Delete);
            //edit = (ImageView)itemView.findViewById(R.id.edit);



        }
    }
}

