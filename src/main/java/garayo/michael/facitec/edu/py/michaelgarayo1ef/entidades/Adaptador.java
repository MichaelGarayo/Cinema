package garayo.michael.facitec.edu.py.michaelgarayo1ef.entidades;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import garayo.michael.facitec.edu.py.michaelgarayo1ef.R;

public class Adaptador extends BaseAdapter {
    private Context context;
    private Pelicula pelicula;

    public Adaptador(Context context, Pelicula pelicula) {
        this.context = context;
        this.pelicula = pelicula;
    }

    @Override
    public int getCount() {
        return pelicula.getResults().size();
    }

    @Override
    public Object getItem(int position) {
        return pelicula.getResults().get(position);
    }

    @Override
    public long getItemId(int position) {
        return pelicula.getResults().get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.linea, null);
        }
        ImageView imagen = convertView.findViewById(R.id.imagen);
        TextView titulo = convertView.findViewById(R.id.titulo);
        TextView promedio = convertView.findViewById(R.id.promedio);
        TextView fecha = convertView.findViewById(R.id.fecha);
        TextView resumen = convertView.findViewById(R.id.resumen);

        Resultado resultado = (Resultado) getItem(position);

        titulo.setText(resultado.getTitle());
        promedio.setText(resultado.getVote_average());
        fecha.setText(resultado.getRelease_date());
        Picasso.with(context).load("https://image.tmdb.org/t/p/w185_and_h278_bestv2"+resultado.getPoster_path()).into(imagen);
        try {
            resumen.setText(resultado.getOverview().substring(0,50)+"...");
        }catch (Exception e){
        }

        return convertView;
    }
}
