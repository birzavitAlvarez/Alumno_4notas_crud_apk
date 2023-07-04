package com.carmen.alumno.DAO;

import com.carmen.alumno.Entity.Alumno;

import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO {

    private static List<Alumno> lista = new ArrayList<>();

    // método que devuelva los datos de un alumno por su codigo
    // caso contrario devolverá null
    public Alumno BuscarAlumno(int codigo) {
        //foreach de java
        for (Alumno alumno : lista) {
            // si el valor codigo del alumno es igual al buscado
            if (alumno.getCodigo() == codigo) return alumno;
        }
        //si el codigo no es encontrado
        return null;
    }

    // Método AgregarAlumno
    public String AgregarAlumno(Alumno alumno) {
        // si el codigo de alumno no es encontrado
        if (BuscarAlumno(alumno.getCodigo()) == null) {
            lista.add(alumno);
            return "Alumno agregado correctamente";
        } else {
            // si fue encontrado
            return "Eror, Código del Alumno Duplicado";
        }
    }

    //ActualizarAlumno
    public String ActualizarAlumno(Alumno objalu) {

        Alumno alumno = BuscarAlumno(objalu.getCodigo());
        if (alumno != null) {
            // alumno encontrado por su codigo se actualiza
            // de esta forma los sobre-escribe y ya no se usa el add
            alumno.setNombre(objalu.getNombre());
            alumno.setNota1(objalu.getNota1());
            alumno.setNota2(objalu.getNota2());
            alumno.setNota3(objalu.getNota3());
            alumno.setNota4(objalu.getNota4());
            alumno.setPromedio(objalu.getPromedio());
            alumno.setEstado(objalu.getEstado());
            // lista.remove(alumno);
            // lista.add(objalu);
            return "Alumno actualizado correctamente";
        } else {
            // Si el alumno no existe en la lista
            return "Error, el código del alumno no existe";
        }
    }

    // Método EliminarAlumno
    public String EliminarAlumno(int codigo) {
        Alumno alumno = BuscarAlumno(codigo);
        if (alumno != null) {
            lista.remove(alumno);
            return "Alumno eliminado correctamente";
        } else {
            return "Error, código del alumno no existe";
        }

    }

    // método que devuelve la lista de alumnos como una lista de String
    public List<String> ListarAlumnosString() {
        List<String> lista_alumno = new ArrayList<>();
        //foreach
        for (Alumno alumno:lista) {
            lista_alumno.add("Codigo: " + alumno.getCodigo() +"\n" +
                    "Nombre: " + alumno.getNombre() +"\n" +
                    "Nota1: " + alumno.getNota1() +"\n" +
                    "Nota2: " + alumno.getNota2() +"\n" +
                    "Nota3: " + alumno.getNota3() +"\n" +
                    "Nota4: " + alumno.getNota4() +"\n" +
                    "Promedio: " + alumno.getPromedio() +"\n" +
                    "Estado" + alumno.getEstado());
        }
        return lista_alumno;
    }

    // método que devuelva la lista de alumnos
    public List<Alumno> ListarAlumnos() {
        return lista;
    }

}
