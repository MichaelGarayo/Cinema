package garayo.michael.facitec.edu.py.michaelgarayo1ef.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import garayo.michael.facitec.edu.py.michaelgarayo1ef.R;
import garayo.michael.facitec.edu.py.michaelgarayo1ef.actividades.DetailActivity;
import garayo.michael.facitec.edu.py.michaelgarayo1ef.entidades.Adaptador;
import garayo.michael.facitec.edu.py.michaelgarayo1ef.entidades.Pelicula;
import garayo.michael.facitec.edu.py.michaelgarayo1ef.entidades.PeliculaDao;
import garayo.michael.facitec.edu.py.michaelgarayo1ef.entidades.Resultado;
import garayo.michael.facitec.edu.py.michaelgarayo1ef.entidades.ResultadoDao;
import garayo.michael.facitec.edu.py.michaelgarayo1ef.servicios.Servicio;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FragmentData extends Fragment implements Callback<Pelicula> {

    private ListView listView;
    private ProgressBar progressBar;
    private PeliculaDao peliculaDao;
    private TextView textError;
    private ResultadoDao resultadoDao;
    private TextView textMensaje;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        listView = view.findViewById(R.id.listView);
        progressBar = view.findViewById(R.id.progressBar);
        textError = view.findViewById(R.id.textError);
        textMensaje = view.findViewById(R.id.textMensaje);
        progressBar.setVisibility(View.VISIBLE);
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("https://api.themoviedb.org").build();
        Servicio servicio =restAdapter.create(Servicio.class);
        servicio.obtenerPeli(this);

        peliculaDao = new PeliculaDao(getActivity());
        resultadoDao = new ResultadoDao(getActivity());

        return view;
    }

    @Override
    public void success(final Pelicula pelicula, Response response) {
        listView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        textMensaje.setVisibility(View.VISIBLE);
        Adaptador adaptador = new Adaptador(getActivity(), pelicula);
        listView.setAdapter(adaptador);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Resultado resultado = (Resultado) parent.getAdapter().getItem(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("resultado", resultado);
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //List<Pelicula> listaPeli = new ArrayList<>();
                try {
                    //////////////////////////////////////////////////////////////
                    //listaPeli = peliculaDao.obtenerTodos();
                    //Pelicula peli = (Pelicula) listaPeli.get(position);
                    //peliculaDao.guardar(peli);
                    //////////////////////////////////////////////////////////////

                    //Obtengo el resultado que se selecciono en la lista
                    Resultado resultado = pelicula.getResults().get(position);
                    //Creo una lista de Resultado para poder setear a mi objeto Pelicula ya que espera una lista
                    List<Resultado> lista = new ArrayList<>();
                    lista.add(resultado);
                    //Creo una nueva pelicula y le seteo los datos recibidos del success
                    Pelicula peli = new Pelicula();
                    peli.setPage(pelicula.getPage());
                    peli.setTotal_pages(pelicula.getTotal_pages());
                    peli.setTotal_results(pelicula.getTotal_results());
                    peli.setResults(lista);
                    //Lo mantengo en memoria antes de que me devuelva null si existe la peli
                    Pelicula pel = peli;
                    //Se pregunta si ya existe
                    //Guardo en BD local el objeto que acabo de crear
                    peli = peliculaDao.guardar(peli);
                    if (peli==null){
                        //Por suerte lo guarde en memoria ya que lo puso nulo porque ya existe la peli!
                        peliculaDao.actualizar(pel);
                        Toast.makeText(getActivity(), "Se guardo", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "No se pudo guardar", Toast.LENGTH_LONG).show();
                }

                return true;
            }
        });

    }

    @Override
    public void failure(RetrofitError error) {
        //Si no se pudo recuperar datos a traves de internet
        //Se consulta a la BD local
        List<Pelicula> listaPeli = new ArrayList<>();
        try {
            listaPeli = peliculaDao.obtenerTodos();
        }catch (Exception e){
            e.printStackTrace();
        }
        //Si en la BD local no existe ningun dato se muestra la pantalla de error
        if (listaPeli == null) {
            textError.setVisibility(View.VISIBLE);
            textError.setText( "No se pudo cargar la lista por el siguiente error: "+error.getLocalizedMessage());
            Log.e("ERROR", error.getLocalizedMessage());
            return;
        }else {
            for (int i=0; i<listaPeli.size();i++){
                Pelicula peli = listaPeli.get(i);
                Adaptador adaptador = new Adaptador(getActivity(), peli);
                listView.setAdapter(adaptador);
            }
            listView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Resultado resultados = (Resultado) parent.getAdapter().getItem(position);
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra("resultado", resultados);
                    startActivity(intent);
                }
            });
        }
    }

}
