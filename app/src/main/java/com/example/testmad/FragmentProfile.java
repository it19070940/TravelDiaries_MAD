package com.example.testmad;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import static com.example.testmad.R.*;

public class FragmentProfile extends Fragment {

   private FirestoreRecyclerAdapter adapter;
   private FirebaseFirestore firebaseFirestore;
   private RecyclerView mFirestoreList;
    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

       firebaseFirestore = FirebaseFirestore.getInstance();
       mFirestoreList = null;
       try{
           mFirestoreList = mFirestoreList.findViewById(R.id.firestore_list);
       }
       catch (NullPointerException ignored){

       }


        //Query
        CollectionReference query = firebaseFirestore.collection("Users");

        //RecyclerOptions
        FirestoreRecyclerOptions<ProductsModel> options = new FirestoreRecyclerOptions.Builder<ProductsModel>().setQuery(query, ProductsModel.class).build();

         adapter = new FirestoreRecyclerAdapter<ProductsModel, ProductsViewHolder>(options) {
            @NonNull
            @Override
            public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view;
                view = LayoutInflater.from(parent.getContext()).inflate(layout.list_item_single, parent, false);
                return new ProductsViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position, @NonNull ProductsModel model) {
                holder.list_name.setText(model.getName());
                holder.list_email.setText(model.getEmail());
                holder.list_mobile.setText(model.getMobile());
            }
        };

         try {
             mFirestoreList.setHasFixedSize(true);
             mFirestoreList.setLayoutManager(new LinearLayoutManager(mFirestoreList.getContext()));
             mFirestoreList.setAdapter(adapter);
         }
         catch (NullPointerException ignored){

         }


         return inflater.inflate(layout.fragment_profile, container, false);


    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }



   private class ProductsViewHolder extends RecyclerView.ViewHolder {
        private TextView list_name;
        private TextView list_email;
        private TextView list_mobile;

        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);

            list_name = itemView.findViewById(id.list_name);
            list_email = itemView.findViewById(id.list_email);
            list_mobile = itemView.findViewById(id.list_mobile);

        }


    }

    public void onStop(){
        super.onStop();
        adapter.stopListening();
    }

    public void onStart(){
        super.onStart();
        adapter.startListening();
    }
  /*  RecyclerView re;
    View v;
    ProgressDialog progress;

    private List<ProductsModel> dataset = new ArrayList<>();
    private List<String> mDatakey = new ArrayList<>();
  public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
      View view = inflater.inflate(R.layout.fragment_profile, container, false);
      return view;
  }

  public void onViewCreated(View view, Bundle savedInstanceState){
      super.onViewCreated(view, savedInstanceState);
      this.v= view;
      init();

      progress = new ProgressDialog(getActivity());
      progress.setTitle("Loading");
      progress.setMessage("Syncing");
      progress.setCancelable(false);
      progress.show();
      loaddata();
  }

  private void loaddata(){
      DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Users");
      db.addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {
            dataset.clear();
            mDatakey.clear();
            for(DataSnapshot single: snapshot.getChildren()){
                dataset.add(single.getValue(ProductsModel.class));
                mDatakey.add(single.getKey().toString());
            }

            progress.dismiss();
          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {

          }
      });
  }

  private void init(){
    re = (RecyclerView)v.findViewById(id.firestore_list);
    re.setLayoutManager(new LinearLayoutManager(getContext()));
  }*/


}

