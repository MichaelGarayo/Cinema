package garayo.michael.facitec.edu.py.michaelgarayo1ef.entidades;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

public class ResultadoDao extends DBA<Resultado> {
    public ResultadoDao(Context context){
        this.init(context, Resultado.class);
    }

    public Resultado guardar(Resultado resultado){
        try {
            this.getDao().create(resultado);
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Resultado> obtenerTodos(){
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
