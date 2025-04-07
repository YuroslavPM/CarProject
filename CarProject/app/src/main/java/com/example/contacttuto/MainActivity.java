package com.example.contacttuto;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contacttuto.DB.DbCar;
import com.example.contacttuto.adapters.ListCarAdapter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    RecyclerView listCars;
    ArrayList<Car> listArrayCars;

    private void loadFragment(Fragment fragment, int containerId){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerId, fragment)
                .commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCars = findViewById(R.id.listCar);

        listCars.setLayoutManager(new LinearLayoutManager(this));

        DbCar dbCar = new DbCar(MainActivity.this);

        listArrayCars = new ArrayList<>();

        ListCarAdapter adapter = new ListCarAdapter(dbCar.showCars());

        listCars.setAdapter(adapter);


    }

/*
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dbHelper = new DbHelper(MainActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if (db!= null){
                    Toast.makeText(MainActivity.this,"DATABASE CREATED", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"ERROR CREATING DATABASE", Toast.LENGTH_LONG).show();

                }
            }
        });
*/




    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.newMenu:
                newRegister();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void newRegister(){
        Intent intent = new Intent(this, NewActivity.class);
        startActivity(intent);
    }


}