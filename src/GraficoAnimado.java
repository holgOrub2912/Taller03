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
	public static void updateDataset(DefaultCategoryDataset dataset, ComparadorPelicula cmp, List<Pelicula> pelis, int uptoYear){
		int i = pelis.size();
		while ( pelis.get(--i).getYear() > uptoYear ) ;;
		dataset.clear();
		for ( Pelicula p: Pelicula.topMPeliculas(pelis.subList(0,i+1), 10, cmp) )
            dataset.setValue(cmp.getValue(p), cmp.getCriterionName(), p.getTitulo());
	}

	public static void grafico(List<Pelicula> pelis, ComparadorPelicula cmp) throws InterruptedException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		pelis.sort(Comparator.naturalOrder());
		
		JFreeChart chart = ChartFactory.createBarChart(
			"Top 10 películas por " + cmp.getCriterionName(),
			cmp.getCriterionName(),
			"Score",
			dataset,
			PlotOrientation.VERTICAL,
			true, true, false
		);

		ChartFrame frame = new ChartFrame("First", chart);
		frame.pack();
		frame.setVisible(true);
		for (int year = pelis.get(0).getYear();
        		year <= pelis.get(pelis.size()-1).getYear();
        		year++){
            updateDataset(dataset, cmp, pelis, year);
            Thread.sleep(200);
		}
	}
}
