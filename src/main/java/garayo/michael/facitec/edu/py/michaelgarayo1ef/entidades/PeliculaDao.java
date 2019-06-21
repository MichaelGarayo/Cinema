package garayo.michael.facitec.edu.py.michaelgarayo1ef.entidades;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

public class PeliculaDao extends DBA<Pelicula> {
    public PeliculaDao(Context context){
        this.init(context, Pelicula.class);
    }

    public Pelicula guardar(Pelicula pelicula){
        try {
            this.getDao().create(pelicula);
            return pelicula;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void actualizar(Pelicula pelicula){
        try {
            this.getDao().update(pelicula);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public List<Pelicula> obtenerTodos(){
        try {
            return this.getDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void eliminar(int id){
        try {
            getDao().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
