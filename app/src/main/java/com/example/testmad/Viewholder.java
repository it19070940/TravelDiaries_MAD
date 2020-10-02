package com.example.testmad;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Viewholder extends RecyclerView.ViewHolder {

    TextView mTitletv,mDateFromtv,mDestiTotv;
    View mView;

    public Viewholder(@NonNull View itemView) {
        super(itemView);

        mView=itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onitemclick(v, getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onitemlongclick(v, getAdapterPosition());
                return true;
            }
        });

        mTitletv = itemView.findViewById(R.id.rTitletv);
        mDateFromtv = itemView.findViewById(R.id.rDateFromtv);
        mDestiTotv = itemView.findViewById(R.id.rDestiTotv);
    }

    private Viewholder.ClickListener mClickListener;

    public interface ClickListener{
        void onitemclick(View view,int position);
        void onitemlongclick(View view,int position);
    }
    public void setOnClickListener(Viewholder.ClickListener clickListener){
        mClickListener =clickListener;
    }
}
