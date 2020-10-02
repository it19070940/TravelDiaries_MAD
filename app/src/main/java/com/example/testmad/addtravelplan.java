package com.example.testmad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class addtravelplan extends AppCompatActivity {

    String sid,sdatefr,sdatet,sdestfr,sdestto,stoT,stoH;
    int snop;
    double sbud;
    FloatingActionButton fbackbtn2;

    EditText tdatefrom,tdateto,tdestifrom,tdestito,ttypeoftravel,ttypeofhotel,tnoofpeople,tbudget,ttravelid;
    Button butadd;
    ProgressDialog pd;
    FirebaseFirestore db;

    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtravelplan);

        ttravelid =findViewById(R.id.txttravelid);
        tdatefrom = findViewById(R.id.txtdatefrom);
        tdateto = findViewById(R.id.txtdateto);
        tdestifrom = findViewById(R.id.txtdestifrom);
        tdestito = findViewById(R.id.txtdestito);
        ttypeoftravel = findViewById(R.id.txttypeoftravel);
        ttypeofhotel = findViewById(R.id.txttypeofhotel);
        tnoofpeople = findViewById(R.id.txtnoofpeople);
        tbudget = findViewById(R.id.txtbudget);

        butadd =(Button)findViewById(R.id.btnadd);
        fbackbtn2 = (FloatingActionButton) findViewById(R.id.nbackbtn1);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this,R.id.txttravelid, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomeValidation.addValidation(this,R.id.txtdatefrom, RegexTemplate.NOT_EMPTY,R.string.invalid_daf);
        awesomeValidation.addValidation(this,R.id.txtdateto, RegexTemplate.NOT_EMPTY,R.string.invalid_dat);
        awesomeValidation.addValidation(this,R.id.txtdestifrom, RegexTemplate.NOT_EMPTY,R.string.invalid_df);
        awesomeValidation.addValidation(this,R.id.txtdestito, RegexTemplate.NOT_EMPTY,R.string.invalid_dt);
        awesomeValidation.addValidation(this,R.id.txttypeoftravel, RegexTemplate.NOT_EMPTY,R.string.invalid_tr);
        awesomeValidation.addValidation(this,R.id.txttypeofhotel, RegexTemplate.NOT_EMPTY,R.string.invalid_th);
        awesomeValidation.addValidation(this,R.id.txtnoofpeople, RegexTemplate.NOT_EMPTY,R.string.invalid_nop);
        awesomeValidation.addValidation(this,R.id.txtbudget, RegexTemplate.NOT_EMPTY,R.string.invalid_bud);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){

            butadd.setText("UPDATE");
            sid =  bundle.getString("pid");
            sdatefr = bundle.getString("pdatefr");
            sdatet =bundle.getString("pdatet");
            sdestfr = bundle.getString("pdestfr");
            sdestto = bundle.getString("pdesto");
            stoT = bundle.getString("ptoT");
            stoH = bundle.getString("ptoH");
            snop = bundle.getInt("pnop");
            sbud = bundle.getDouble("pbud");

            ttravelid.setText(sid);
            tdatefrom.setText(sdatefr);
            tdateto.setText(sdatet);
            tdestifrom.setText(sdestfr);
            tdestito.setText(sdestto);
            ttypeoftravel.setText(stoT);
            ttypeofhotel.setText(stoH);
            tnoofpeople.setText(String.valueOf(snop));
            tbudget.setText(String.valueOf(sbud));

        }
        else {

        }

        pd=new ProgressDialog(this);
        db = FirebaseFirestore.getInstance();

        butadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(awesomeValidation.validate()){
                    Bundle bundle1 = getIntent().getExtras();

                    if(bundle1 != null){
                    String IDtravel=ttravelid.getText().toString().trim();
                    String datefrom=tdatefrom.getText().toString().trim();
                    String dateto=tdateto.getText().toString().trim();
                    String destinationfrom=tdestifrom.getText().toString().trim();
                    String destinationto=tdestito.getText().toString().trim();
                    String Ttravel=ttypeoftravel.getText().toString().trim();
                    String Htravel=ttypeofhotel.getText().toString().trim();
                    int nopeople=Integer.parseInt(tnoofpeople.getText().toString().trim());
                    double budget=Integer.parseInt(tbudget.getText().toString().trim());

                    updateData(IDtravel,datefrom,dateto,destinationfrom,destinationto,Ttravel,Htravel,nopeople,budget);
                } else{
                    String IDtravel=ttravelid.getText().toString().trim();
                    String datefrom=tdatefrom.getText().toString().trim();
                    String dateto=tdateto.getText().toString().trim();
                    String destinationfrom=tdestifrom.getText().toString().trim();
                    String destinationto=tdestito.getText().toString().trim();
                    String Ttravel=ttypeoftravel.getText().toString().trim();
                    String Htravel=ttypeofhotel.getText().toString().trim();
                    int nopeople=Integer.parseInt(tnoofpeople.getText().toString().trim());
                    double budget=Integer.parseInt(tbudget.getText().toString().trim());

                    uploadData(IDtravel,datefrom,dateto,destinationfrom,destinationto,Ttravel,Htravel,nopeople,budget);
                }
                    Toast.makeText(getApplicationContext(),"Successfully Validated",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Validation Failed",Toast.LENGTH_SHORT).show();
                }

            }
        });

        fbackbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(addtravelplan.this, travelplanmenu.class));
                finish();
            }
        });
    }



    private void updateData(String iDtravel, String datefrom, String dateto, String destinationfrom, String destinationto, String ttravel, String htravel, int nopeople, double budget) {
        pd.setTitle("Updating Information");
        pd.show();

        db.collection("Documents").document(iDtravel).update("ID",iDtravel,"DateFrom",datefrom,"DateTo",dateto,"DestinationFrom",destinationfrom,"DestinationTo",destinationto,"TypeOfTravel",ttravel,"TypeOfHotel",htravel,"NumberOfPeople",nopeople,"Budget",budget)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pd.dismiss();
                        Toast.makeText(addtravelplan.this,"Successfully Updated",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(addtravelplan.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadData(String iDtravel, String datefrom, String dateto, String destinationfrom, String destinationto, String ttravel, String htravel, int nopeople, double budget) {
        pd.setTitle("Adding Information");
        pd.show();

        Map<String,Object> doc = new HashMap<>();
        doc.put("ID",iDtravel);
        doc.put("DateFrom",datefrom);
        doc.put("DateTo",dateto);
        doc.put("DestinationFrom",destinationfrom);
        doc.put("DestinationTo",destinationto);
        doc.put("TypeOfTravel",ttravel);
        doc.put("TypeOfHotel",htravel);
        doc.put("NumberOfPeople",nopeople);
        doc.put("Budget",budget);

        db.collection("Documents").document(iDtravel).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                pd.dismiss();
                Toast.makeText(addtravelplan.this,"Successfully Inserted",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(addtravelplan.this,e.getMessage(),Toast.LENGTH_SHORT).show();            }
        });


    }
}