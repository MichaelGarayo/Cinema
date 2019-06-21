package garayo.michael.facitec.edu.py.michaelgarayo1ef.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import garayo.michael.facitec.edu.py.michaelgarayo1ef.R;
import garayo.michael.facitec.edu.py.michaelgarayo1ef.entidades.Resultado;

public class FragmentDetail extends Fragment {

    private ImageView imagen;
    private TextView titulo;
    private TextView año;
    private TextView resumen;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        imagen = view.findViewById(R.id.imagenDetail);
        titulo = view.findViewById(R.id.tituloDetail);
        año = view.findViewById(R.id.fechaDetail);
        resumen = view.findViewById(R.id.resumenDetail);

        Intent intent = getActivity().getIntent();
        Resultado resultados = (Resultado)intent.getSerializableExtra("resultado");
        titulo.setText(resultados.getTitle());
        año.setText(resultados.getRelease_date());
        resumen.setText(resultados.getOverview());
        Picasso.with(getContext()).load("https://image.tmdb.org/t/p/w185_and_h278_bestv2"+resultados.getPoster_path()).into(imagen);

        return view;
    }

}
