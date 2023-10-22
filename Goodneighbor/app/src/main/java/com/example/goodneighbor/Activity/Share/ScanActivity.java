/*
package com.example.goodneighbor.Activity.Share;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.goodneighbor.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanActivity extends AppCompatActivity
{
    //Initialize variable
    Button btScan;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_goods3);

        //Assign variable
        btScan=findViewById(R.id.share_sharing1);

        // btScan.setTextColor(Color.BLUE);

        btScan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Initialize intent integrator
                IntentIntegrator intentIntegrator=new IntentIntegrator(ScanActivity.this);
                //Set prompt text
                intentIntegrator.setPrompt("For flash ues volume up key");
                //Set beep
                intentIntegrator.setBeepEnabled(true);
                //Locked orientation
                intentIntegrator.setOrientationLocked(true);
                //Set capture activity
                intentIntegrator.setCaptureActivity(Capture.class);
                //Initiate scan
                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Initialize intent result
        IntentResult intentResult=IntentIntegrator.parseActivityResult(
                requestCode, resultCode, data);
        //Check condition
        if(intentResult.getContents()!=null)
        {
            //When result content is not null
            //Initialize alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(ScanActivity.this);
            //Set title
            builder.setTitle("Result");
            //Set message
            builder.setMessage(intentResult.getContents());
            //Set positive button
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //Dismiss dialog
                    dialogInterface.dismiss();
                }
            });
            //Show alert dialog
            builder.show();
        }else{
            //When result content is null
            //Display toast
            Toast.makeText(getApplicationContext()
                    ,"OOPS...You did not scan anything",Toast.LENGTH_SHORT
            ).show();
        }
    }
}*/
