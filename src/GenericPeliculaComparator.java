import java.util.Comparator;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GenericPeliculaComparator implements Comparator<Pelicula> {
	Method criterio;
	public GenericPeliculaComparator(Method criterio){
		this.criterio = criterio;
	}

    @Override
    public int compare(Pelicula primeraPeli, Pelicula segundaPeli) {
        Comparable val1, val2;
        try {
            val1 = (Comparable) criterio.invoke(primeraPeli);
            val2 = (Comparable) criterio.invoke(segundaPeli);
        } catch (InvocationTargetException|IllegalAccessException e){
            return 0;
        }
        return val1.compareTo(val2);
    }
}
