package garayo.michael.facitec.edu.py.michaelgarayo1ef.servicios;

import garayo.michael.facitec.edu.py.michaelgarayo1ef.entidades.Pelicula;
import retrofit.Callback;
import retrofit.http.GET;

public interface Servicio {
    @GET("/3/discover/movie?sort_by=popularity.desc&api_key=4db36c2f48ed384918a6e02a99eb9e5e&language=es&page=1")
    void obtenerPeli(Callback<Pelicula> pelicula);
}
