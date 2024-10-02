import edu.princeton.cs.algs4.*;
import java.util.List;
import java.util.InputMismatchException;

public class Taller3 {

	public static final String RUTA_CSV = "final_dataset.csv";

	public static ComparadorPelicula menuComparadorPelicula(){
		final ComparadorPelicula[] comparators = {
			new BudgetComparator(),
			new PopularityComparator(),
			new ProfitComparator(),
			new RoiComparator()
		};

		System.out.println("Por favor elige el criterio por el que ordenar las películas: ");
		for (int i = 0; i < comparators.length; i++)
			System.out.printf("%2d) %s\n", i+1, comparators[i].getCriterionName());

		do
			try {
				return comparators[StdIn.readInt()-1];
			} catch(Exception e) {
				System.out.println("Selección inválida");
			}
		while (true);
	}

    public static void main(String[] args) throws Exception {
        /*
        for (Pelicula p: Pelicula.topMPeliculas(lista_pelis,
                         10, new PopularityComparator()))
            System.out.println(p);
        */
        List<Pelicula> lista_pelis = Pelicula.leerPelisdeCSV(RUTA_CSV);
		int m;
		do {
			System.out.print("¿Cuántas películas quieres mostrar a la vez? ");
			try {
				m = StdIn.readInt();
				break;
			} catch(InputMismatchException e) {
				System.out.println("Valor inválido.");
			}
		} while (true);
        GraficoAnimado.grafico(m, lista_pelis, menuComparadorPelicula());
    }
}
