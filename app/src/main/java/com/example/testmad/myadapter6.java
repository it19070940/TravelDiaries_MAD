package com.example.testmad;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.Model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

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

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(getApplication ImagesActivity.class);
                //startActivity(i);

                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext()).setContentHolder(new ViewHolder(R.layout.activity_book_hotel))
                        .setExpanded(true, 1500).create();
                View myview = dialogPlus.getHolderView();

                final EditText name = myview.findViewById(R.id.hotelName);
                final EditText rate = myview.findViewById(R.id.hotelRate);
                final EditText noOfPerson = myview.findViewById(R.id.person);
                final EditText type = myview.findViewById(R.id.type);

                name.setText(model.getName());
                rate.setText(model.getRate());

                dialogPlus.show();

                Button usubmit = myview.findViewById(R.id.book);

                usubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("name", name.getText().toString());
                        map.put("rate", rate.getText().toString());
                        map.put("noPerson", noOfPerson.getText().toString());
                        map.put("type", type.getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("Booking").child(getRef(position).getKey()).push().setValue(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @SuppressLint("RestrictedApi")
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        rate.setText("");
                                        name.setText("");
                                        noOfPerson.setText("");
                                        type.setText("");
                                        Toast.makeText(getApplicationContext(), "Inserted successfully", Toast.LENGTH_LONG).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });


            }
        });

            }
        });







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
        Button view;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img = (CircleImageView) itemView.findViewById(R.id.img2);
            name = (TextView)itemView.findViewById(R.id.hotelName);
            rate= (TextView)itemView.findViewById(R.id.rate);
            view = (Button)itemView.findViewById(R.id.btnView);
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

