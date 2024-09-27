import edu.princeton.cs.algs4.*;
import java.util.List;

public class Taller3 {

	public static final String RUTA_CSV = "final_dataset.csv";
	
    public static void main(String[] args) throws Exception {
        /*
        for (Pelicula p: Pelicula.topMPeliculas(lista_pelis,
                         10, new PopularityComparator()))
            System.out.println(p);
        */
        List<Pelicula> lista_pelis = Pelicula.leerPelisdeCSV(RUTA_CSV);
        GraficoAnimado.grafico(lista_pelis, Pelicula.class.getMethod("getPopularity"));
    }
}
