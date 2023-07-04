package com.carmen.alumno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.carmen.alumno.DAO.AlumnoDAO;
import com.carmen.alumno.Entity.Alumno;
import com.carmen.alumno.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View vista = binding.getRoot();
        setContentView(vista);

        binding.edtCodigo.requestFocus();

        binding.btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edtCodigo.setText("");
                binding.edtNombre.setText("");
                binding.edtnota1.setText("");
                binding.edtnota2.setText("");
                binding.edtnota3.setText("");
                binding.edtnota4.setText("");
                binding.edtCodigo.requestFocus();
            }
        });
        //
        binding.btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        ListadoAlumno.class);
                //
                startActivity(i);
            }
        });

        binding.btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int nota1 = Integer.parseInt(binding.edtnota1.getText() + "");
                int nota2 = Integer.parseInt(binding.edtnota2.getText() + "");
                int nota3 = Integer.parseInt(binding.edtnota3.getText() + "");
                int nota4 = Integer.parseInt(binding.edtnota4.getText() + "");

                int promedio = (nota1 + nota2 + nota3 + nota4) / 4;

                String mensajee;
                if (promedio >= 13) {
                    mensajee = ": APROBADO";
                } else {
                    mensajee = ": REPROBADO";
                }

                Alumno obj = new Alumno(
                        Integer.parseInt(binding.edtCodigo.getText() + ""),
                        binding.edtNombre.getText().toString(),
                        nota1,
                        nota2,
                        nota3,
                        nota4,
                        promedio,
                        mensajee.toString()
                );
                // DAO
                AlumnoDAO dao = new AlumnoDAO();
                String mensaje = dao.AgregarAlumno(obj);
                dao = null;
                Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
            }
        });




    }



}