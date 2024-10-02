/* AUTORES: Isabela Arrubla Orozco, Fernando González Rivero */
import java.util.List;
import java.util.Comparator;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

class GraficoAnimado {
	public static void updateDataset(int m, DefaultCategoryDataset dataset, ComparadorPelicula cmp, List<Pelicula> pelis, int upToYear){
		int i = pelis.size();
		while ( pelis.get(--i).getYear() > upToYear ) ;;
		dataset.clear();
		for ( Pelicula p: Pelicula.topMPeliculas(pelis.subList(0,i+1), m, cmp) )
            dataset.setValue(cmp.getValue(p), cmp.getCriterionName(), p.getTitulo());
	}

	public static String titleBuilder(int m, ComparadorPelicula cmp, int upToYear){
		return String.format("Top %d películas por %s (hasta %d)", m, cmp.getCriterionName(), upToYear);
	}

	public static void grafico(int m, List<Pelicula> pelis, ComparadorPelicula cmp) throws InterruptedException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		pelis.sort(Comparator.naturalOrder());
		
		JFreeChart chart = ChartFactory.createBarChart(
			"Top 10 películas por " + cmp.getCriterionName(),
			cmp.getCriterionName(),
			null,
			dataset,
			PlotOrientation.VERTICAL,
			true, true, true
		);

		ChartFrame frame = new ChartFrame("First", chart);
		frame.pack();
		frame.setVisible(true);
		for (int year = pelis.get(0).getYear();
        		year <= pelis.get(pelis.size()-1).getYear();
        		year++){
            updateDataset(m, dataset, cmp, pelis, year);
            chart.setTitle( titleBuilder(m, cmp, year) );
            Thread.sleep(200);
		}
	}
}
