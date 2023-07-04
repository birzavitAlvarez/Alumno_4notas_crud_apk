package com.carmen.alumno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.carmen.alumno.DAO.AlumnoDAO;
import com.carmen.alumno.Entity.Alumno;
import com.carmen.alumno.databinding.ActivityActualizarAlumnoBinding;

public class ActualizarAlumno extends AppCompatActivity {

    ActivityActualizarAlumnoBinding binding;
    AlumnoDAO dao = null;

    void MostrarAlumno() {
        // recuperamos el Intent
        Intent i = getIntent();
        if (i!=null){
            int pos = i.getIntExtra("Indice",0);
            // recuperar y mostrar los datos del alumno
            dao = new AlumnoDAO();
            Alumno alu = dao.ListarAlumnos().get(pos);
            binding.edtCodigoUpd.setText(alu.getCodigo()+"");
            binding.edtNombreUpd.setText(alu.getNombre());
            binding.edtnota1Upd.setText(alu.getNota1()+"");
            binding.edtnota2Upd.setText(alu.getNota2()+"");
            binding.edtnota3Upd.setText(alu.getNota3()+"");
            binding.edtnota4Upd.setText(alu.getNota4()+"");
            dao = null;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_actualizar_alumno);
        binding = ActivityActualizarAlumnoBinding.inflate(getLayoutInflater());
        View vista = binding.getRoot();
        setContentView(vista);

        MostrarAlumno();

        binding.btnCerrarUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(ActualizarAlumno.this,
                        MainActivity.class);
                //
                startActivity(i);
            }
        });

        binding.btnActualizarUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int nota1 = Integer.parseInt(binding.edtnota1Upd.getText() + "");
                int nota2 = Integer.parseInt(binding.edtnota2Upd.getText() + "");
                int nota3 = Integer.parseInt(binding.edtnota3Upd.getText() + "");
                int nota4 = Integer.parseInt(binding.edtnota4Upd.getText() + "");

                int promedio = (nota1 + nota2 + nota3 + nota4) / 4;

                String mensajee;
                if (promedio >= 13) {
                    mensajee = ": APROBADO";
                } else {
                    mensajee = ": REPROBADO";
                }

                Alumno obj = new Alumno(
                        Integer.parseInt(binding.edtCodigoUpd.getText() + ""),
                        binding.edtNombreUpd.getText().toString(),
                        nota1,
                        nota2,
                        nota3,
                        nota4,
                        promedio,
                        mensajee.toString()
                );
                //
                dao = new AlumnoDAO();
                String mensaje = dao.ActualizarAlumno(obj);
                dao = null;
                Toast.makeText(ActualizarAlumno.this, mensaje, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(ActualizarAlumno.this,
                        ListadoAlumno.class);
                //
                startActivity(i);

            }
        });


    }
}