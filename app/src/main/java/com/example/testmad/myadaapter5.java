package com.example.testmad;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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

public class myadaapter5 extends FirebaseRecyclerAdapter<hotelsModel, myadaapter5.myviewholder> {
    public myadaapter5(@NonNull FirebaseRecyclerOptions<hotelsModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final hotelsModel model) {
        holder.name.setText(model.getName());
        holder.rate.setText(model.getRate());
       // holder.details.setText(model.getDetails());

        Glide.with(holder.img.getContext()).load(model.getImageUrl()).into(holder.img);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext()).setContentHolder(new ViewHolder(R.layout.dialogcontent1))
                        .setExpanded(true, 1100).create();

                View myview = dialogPlus.getHolderView();
                final EditText name = myview.findViewById(R.id.uname);
                final EditText rate = myview.findViewById(R.id.urate);
                final EditText imageUrl = myview.findViewById(R.id.uimgurl);


                Button usubmit = myview.findViewById(R.id.usubmit);

                imageUrl.setText(model.getImageUrl());
                name.setText(model.getName());
                rate.setText(model.getRate());

                dialogPlus.show();

                usubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("imageUrl", imageUrl.getText().toString());
                        map.put("name", name.getText().toString());
                        map.put("rate", rate.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("uploads").child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
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

        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.img.getContext());
                builder.setTitle("Delete Hotel");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("uploads").child(getRef(position).getKey()).removeValue();

                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });



    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow7, parent, false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView name, rate;
        ImageView Delete, edit;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img = (CircleImageView) itemView.findViewById(R.id.img2);
            name = (TextView)itemView.findViewById(R.id.hotelName);
            rate= (TextView)itemView.findViewById(R.id.rate);
           // details= (TextView)itemView.findViewById(R.id.details);
            Delete= (ImageView)itemView.findViewById(R.id.Delete);
            edit = (ImageView)itemView.findViewById(R.id.edit);



        }
    }
}
