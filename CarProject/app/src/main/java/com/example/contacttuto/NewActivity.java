package com.example.contacttuto;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.contacttuto.DB.DbCar;

public class NewActivity extends AppCompatActivity {

    EditText txtBrand, txtModel, txtTypeOfFuel,txtYear,txtHp;
    Button btSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        txtBrand = findViewById(R.id.txtBrand);
        txtModel = findViewById(R.id.txtModel);
        txtTypeOfFuel = findViewById(R.id.txtTypeOfFuel);
        txtYear = findViewById(R.id.txtYear);
        txtHp = findViewById(R.id.txtHp);
        btSave = findViewById(R.id.btSave);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbCar dbCar = new DbCar(NewActivity.this);

                long id = dbCar.insertCar(txtBrand.getText().toString(), txtModel.getText().toString(), txtTypeOfFuel.getText().toString(),
                        Integer.parseInt(txtYear.getText().toString()),Integer.parseInt(txtHp.getText().toString()));

                if (id>0){
                        Toast.makeText(NewActivity.this, "SAVED CAR", Toast.LENGTH_LONG).show();
                    Clean();
                    back();
                }
                else{
                    Toast.makeText(NewActivity.this, "ERROR TO SAVE CAR", Toast.LENGTH_LONG).show();
                }

            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            back();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Clean(){
        txtBrand.setText("");
        txtModel.setText("");
        txtTypeOfFuel.setText("");
        txtYear.setText("");
        txtHp.setText("");

    }

    private void back(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
