import java.util.Comparator;

public class ProfitComparator implements Comparator<Pelicula> {

    @Override
    public int compare(Pelicula primeraPeli, Pelicula segundaPeli) {
       return Double.compare(primeraPeli.getProfit(), segundaPeli.getProfit());
    }

}
