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
	public static void updateDataset(DefaultCategoryDataset dataset, Method criterio, List<Pelicula> pelis, int uptoYear) throws IllegalAccessException,InvocationTargetException{
		int i = pelis.size();
		while ( pelis.get(--i).getYear() > uptoYear ) ;;
		dataset.clear();
		for (Pelicula p: Pelicula.topMPeliculas( pelis.subList(0,i+1), 10,
		     new GenericPeliculaComparator(criterio)))
            dataset.setValue((double) criterio.invoke(p), criterio.getName(), p.getTitulo());
	}
	public static void grafico(List<Pelicula> pelis, Method criterio) throws InterruptedException,IllegalAccessException,InvocationTargetException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		GenericPeliculaComparator comparador = new GenericPeliculaComparator(criterio);
		pelis.sort(Comparator.naturalOrder());
		
		JFreeChart chart = ChartFactory.createBarChart(
			"Sample Bar Chart",
			criterio.getName(),
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
            updateDataset(dataset, criterio, pelis, year);
            System.out.println("year: " + year);
            Thread.sleep(200);
		}
	}
}
