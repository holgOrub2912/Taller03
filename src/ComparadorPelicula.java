import java.util.Comparator;

public interface ComparadorPelicula extends Comparator<Pelicula> {
	public String getCriterionName();
	public double getValue(Pelicula p);
}
