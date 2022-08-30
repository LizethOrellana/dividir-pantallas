package com.example.appearthqueke;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appearthqueke.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MainViewModel viewmodel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.eqRecyclear.setLayoutManager(new LinearLayoutManager(this));

        //modf
        EqAdapter adapter = new EqAdapter();
        adapter.setOnItemClickListener(earthquake -> {
            Toast.makeText(MainActivity.this, earthquake.getPlace(), Toast.LENGTH_SHORT).show();
            String id = earthquake.getId();
            String place = earthquake.getPlace();
            double magnitude = earthquake.getMagnitude();
            long tiempo = earthquake.getTime();
            double latitud = earthquake.getLatitude();
            double longitud = earthquake.getLongitude();
            abrirEarthquake_Monitor(id,place,magnitude,tiempo,latitud,longitud);
                }
        );


        binding.eqRecyclear.setAdapter(adapter);
        viewmodel.getEqList().observe(this, eqList->{
            adapter.submitList(eqList);
            Earthquake eqq=eqList.stream().max(Comparator.comparingDouble(Earthquake::getMagnitude)).get();
            binding.setEarthquakeMayor(eqq);
            if (eqList.isEmpty()){
                binding.emptyView.setVisibility(View.VISIBLE);
            }else{
                binding.emptyView.setVisibility(View.GONE);
            }
        });
         viewmodel.getEarthquakes();


    }
    private void abrirEarthquake_Monitor(String id, String place, double magnitude, long time, double latitud, double longitud) {
        Intent intent = new Intent(this, Earthquake_Monitor.class);
        Earthquake usuario = new Earthquake(id,place,magnitude,time, latitud,longitud);
        intent.putExtra(Earthquake_Monitor.EARTHQUAKE_KEY, usuario);
        startActivity(intent);
    }
}