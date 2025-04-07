package com.example.contacttuto;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.contacttuto.DB.DbCar;

public class EditActivity extends AppCompatActivity {
    EditText txtBrand, txtModel, txtTypeOfFuel,txtYear,txtHp;
    Button btnSave;
    boolean correct = false;
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

        DbCar dbCar = new DbCar(EditActivity.this);
        car = dbCar.seeCars(id);

        if (car !=null){
            txtBrand.setText(car.getBrand());
            txtModel.setText(car.getModel());
            txtTypeOfFuel.setText(car.getTypeOfFuel());
            txtYear.setText(String.valueOf(car.getYear()));
            txtHp.setText(String.valueOf(car.getHp()));

        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtBrand.getText().toString().equals("") && !txtModel.getText().toString().equals("")){
                    correct = dbCar.editCar(id, txtBrand.getText().toString(), txtModel.getText().toString(), txtTypeOfFuel.getText().toString(),
                            Integer.parseInt(txtYear.getText().toString()),Integer.parseInt(txtHp.getText().toString()));

                if (correct){
                    Toast.makeText(EditActivity.this, "SUCCESSFULLY EDITED CAR", Toast.LENGTH_LONG).show();
                    seeRegister();
                }else{
                    Toast.makeText(EditActivity.this, "ERROR TRYING TO EDIT CAR", Toast.LENGTH_LONG).show();
                }
              }else{
                    Toast.makeText(EditActivity.this, "FILL IN THE REQUIRED FIELDS", Toast.LENGTH_LONG).show();
                }


            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }
    private void seeRegister(){
        Intent intent = new Intent(this, VisualizeActivity.class);
        intent.putExtra("ID",id);
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
