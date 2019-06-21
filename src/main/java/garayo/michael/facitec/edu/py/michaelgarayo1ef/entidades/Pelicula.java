package garayo.michael.facitec.edu.py.michaelgarayo1ef.entidades;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.List;


@DatabaseTable
public class Pelicula implements Serializable {
    @DatabaseField
    private int page;
    @DatabaseField
    private int total_results;
    @DatabaseField
    private int total_pages;
    @ForeignCollectionField(orderAscending = false)
    private List<Resultado> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<Resultado> getResults() {
        return results;
    }

    public void setResults(List<Resultado> results) {
        this.results = results;
    }
}
