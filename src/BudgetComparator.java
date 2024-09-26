import java.util.Comparator;

public class BudgetComparator implements Comparator<Pelicula> {

    @Override
    public int compare(Pelicula primeraPeli, Pelicula segundaPeli) {
       return Double.compare(primeraPeli.getBudget(), segundaPeli.getBudget());
    }

}
