package com.example.contacttuto;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.contacttuto.DB.DbCar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class VisualizeActivity extends AppCompatActivity {

    EditText txtBrand, txtModel, txtTypeOfFuel,txtYear,txtHp;
    Button btnSave;
    FloatingActionButton fabEdit, fabDelete;


    Car car;
    int id= 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualize);

        txtBrand = findViewById(R.id.txtBrand);
        txtModel = findViewById(R.id.txtModel);
        txtTypeOfFuel = findViewById(R.id.txtTypeOfFuel);
        txtYear = findViewById(R.id.txtYear);
        txtHp = findViewById(R.id.txtHp);
        btnSave = findViewById(R.id.btSave);
        fabEdit = findViewById(R.id.fabEdit);
        fabDelete = findViewById(R.id.fabDelete);
        Button btnGenerateContactQR = findViewById(R.id.btnGenerateContactQR);
        ImageView imageViewContactQR = findViewById(R.id.imageViewContactQR);

        if(savedInstanceState == null){

            Bundle extra = getIntent().getExtras();
            if (extra == null){

                id=Integer.parseInt(null);
            }
            else{
                id = extra.getInt("ID");
            }

        } else{

            id = (int)savedInstanceState.getSerializable("ID");
        }

        DbCar dbCar = new DbCar(VisualizeActivity.this);
        car = dbCar.seeCars(id);

        if (car !=null){
            txtBrand.setText(car.getBrand());
            txtModel.setText(car.getModel());
            txtTypeOfFuel.setText(car.getTypeOfFuel());
            txtYear.setText(String.valueOf(car.getYear()));
            txtHp.setText(String.valueOf(car.getHp()));

            btnSave.setVisibility(View.INVISIBLE);
            txtBrand.setInputType(InputType.TYPE_NULL);
            txtModel.setInputType(InputType.TYPE_NULL);
            txtTypeOfFuel.setInputType(InputType.TYPE_NULL);
            txtYear.setInputType(InputType.TYPE_NULL);
            txtHp.setInputType(InputType.TYPE_NULL);
        }

        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VisualizeActivity.this, EditActivity.class);
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });

        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(VisualizeActivity.this);
                builder.setMessage("Do want to delete this car?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                               if (dbCar.deleteContact(id)){
                                    list();
                               }
                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });

        btnGenerateContactQR.setOnClickListener(view -> {
            String brandQR = txtBrand.getText().toString();
            String modelQR = txtModel.getText().toString();
            String typeOfFuelQR = txtTypeOfFuel.getText().toString();
            int txtYearQR = Integer.parseInt(txtYear.getText().toString());
            int txtHpQR = Integer.parseInt(txtHp.getText().toString());

            if (!brandQR.isEmpty() && !modelQR.isEmpty() && !typeOfFuelQR.isEmpty() && txtYearQR!=0 && txtHpQR!=0) {
                // Create vCard string
                String vCard = "BEGIN:VCARD\n" +
                        "VERSION:3.0\n" +
                        "BRAND:" + brandQR + "\n" +
                        "MODEL:" + modelQR + "\n" +
                        "TYPEOFFUEL:" + typeOfFuelQR + "\n" +
                        "YEAR:" + txtYearQR + "\n" +
                        "HP:" + txtHpQR + "\n" +
                        "END:VCARD";

                try {
                    // Generate QR Code
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.encodeBitmap(vCard, BarcodeFormat.QR_CODE, 400, 400);
                    imageViewContactQR.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Error generating QR code", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    private void list(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            back();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void back(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}