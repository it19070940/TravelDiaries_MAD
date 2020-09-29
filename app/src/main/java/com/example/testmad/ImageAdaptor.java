package com.example.testmad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public  class ImageAdaptor extends RecyclerView.Adapter<ImageAdaptor.ImageViewHolder>{

    private Context mContext;
    private List<Pack> mUploads;
 
    public ImageAdaptor(Context context, List<Pack> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_view, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Pack uploadCurrent = mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.gettName());
        Picasso.get()
                .load(uploadCurrent.getKey())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {

        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder  {
        public TextView textViewName;
        public ImageView imageView;
        public ImageViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            imageView = itemView.findViewById(R.id.image_view_upload);

            //implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener

            //itemView.setOnClickListener(this);
            //itemView.setOnCreateContextMenuListener(this);
        }


        /*@Override
        public void onClick(View view) {
            if (mListner != null){
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    mListner.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Select Action");
            MenuItem doWhatever = contextMenu.add(contextMenu.NONE,1,1,"Do whatever");
            MenuItem delete = contextMenu.add(contextMenu.NONE, 2,2,"Delete");

           doWhatever.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (mListner != null){
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){

                    switch (item.getItemId()) {
                        case 1:
                            mListner.onWhatEverClicked(position);
                            return true;
                        case 2:
                            mListner.onDeleteClick(position);
                            return true;
                    }
                }
            }
            return false;
        }*/
    }
    /*public interface OnItemClickListner{
            void onItemClick(int position);

            void onWhatEverClicked(int position);

            void onDeleteClick(int position);

    }
    public void setOnItemClickListner(OnItemClickListner listner){
    mListner = listner;
    }*/
}