package com.example.testmad;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Customadapter extends RecyclerView.Adapter<Viewholder> {

    viewplan viewplan;
    List<Model> modelList;
    Context context;

    public Customadapter(com.example.testmad.viewplan viewplan, List<Model> modelList) {
        this.viewplan = viewplan;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.model, parent, false);

        Viewholder viewholder = new Viewholder(itemview);

        viewholder.setOnClickListener(new Viewholder.ClickListener() {
            @Override
            public void onitemclick(View view, int position) {

                String id = modelList.get(position).getIDtravel();
                String datefr = modelList.get(position).getDatefrom();
                String destto = modelList.get(position).getDestinatioto();
                Toast.makeText(viewplan, id +"\n"+ datefr, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onitemlongclick(View view, final int position) {

                AlertDialog.Builder builder = new AlertDialog.Builder(viewplan);

                String[] option = {"Update","Delete"};
                builder.setItems(option, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){
                            String id =  modelList.get(position).getIDtravel();
                            String datefr = modelList.get(position).getDatefrom();
                            String datet = modelList.get(position).getDateto();
                            String destfr = modelList.get(position).getDestinationfrom();
                            String destto = modelList.get(position).getDestinatioto();
                            String toT = modelList.get(position).getTtravel();
                            String toH = modelList.get(position).getHtravel();
                            int nop = modelList.get(position).getNopeople();
                            double bud = modelList.get(position).getBudget();

                            Intent intent = new Intent(viewplan, addtravelplan.class);

                            intent.putExtra("pid", id);
                            intent.putExtra("pdatefr", datefr);
                            intent.putExtra("pdatet", datet);
                            intent.putExtra("pdestfr", destfr);
                            intent.putExtra("pdesto", destto);
                            intent.putExtra("ptoT", toT);
                            intent.putExtra("ptoH", toH);
                            intent.putExtra("pnop", nop);
                            intent.putExtra("pbud", bud);

                            viewplan.startActivity(intent);
                        }
                        else if(which == 1){
                            viewplan.deletedata(position);
                        }

                    }
                }).create().show();

            }
        });
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        holder.mTitletv.setText(modelList.get(position).getIDtravel());
        holder.mDateFromtv.setText(modelList.get(position).getDatefrom());
        holder.mDestiTotv.setText(modelList.get(position).getDestinatioto());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
