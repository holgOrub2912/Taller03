import java.util.Comparator;

public class PopularityComparator implements Comparator<Pelicula> {

    @Override
    public int compare(Pelicula primeraPeli, Pelicula segundaPeli) {
       return Double.compare(primeraPeli.getPopularity(), segundaPeli.getPopularity());
    }

}
