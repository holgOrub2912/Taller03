import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
//usar BarChart de la libreia JFreeChart

public class Pelicula implements Comparable<Pelicula> {

    private String titulo;
    
    private int year;
    private double production_budget;
    private double domestic_Gross;
    private double foreign_Gross;
    private double worldwide_Gross;
    private double profit;
    private double mes;
    private double profit_Margin;
    private double popularity;
    private double roi;

    // populariy, profit, roi, budget
    
    public double getBudget(){
        return this.production_budget;
    }

    public double getPopularity(){
        return this.popularity;
    }
    
    public double getProfit(){
        return this.profit;
    }
    public double getRoi(){
        return this.roi;
    }
    
    public Pelicula(String titulo, int year,double production_budget, double domesticGross, double foreign_Gross, double worldwide_Gross,
    double profit, double mes, double profin_Margin, double popularity, double roi) {
        this.titulo = titulo;
        this.year = year;
        this.production_budget=production_budget;
        this.domestic_Gross = domesticGross;
        this.foreign_Gross=foreign_Gross;
        this.worldwide_Gross=worldwide_Gross;
        this.profit = profit;
        this.mes = mes;
        this.profit_Margin = profin_Margin;
        this.popularity=popularity;
        this.roi=roi;
    }

    @Override
    public int compareTo(Pelicula o) {
        return Integer.compare(this.year, o.year);
    }
    
    public static List<Pelicula> leerPelisdeCSV(String ruta_archivoCSV)throws IOException{

        String titulo;
        int year;
        double domestic_Gross;
        double foreign_Gross;
        double worldwide_Gross;
        double mes;
        double profit;
        double profit_Margin;
        Pelicula peliAgregar;

        List<Pelicula> peliculas = new ArrayList<>();
        FileReader archivo = new FileReader(ruta_archivoCSV);
        BufferedReader datos = new BufferedReader(archivo);
        String linea;
       
        // Leer la primera línea que contiene los headers (campos del archivo CSV)
        linea = datos.readLine(); 
        

        while ((linea=datos.readLine()) != null) {
            String[] campos = linea.split(","); 
            titulo= campos[0];
            year=Integer.parseInt(campos[1]);
            domestic_Gross= Double.parseDouble(campos[2]);
            foreign_Gross=Double.parseDouble(campos[3]);
            worldwide_Gross=Double.parseDouble(campos[4]);
            mes= Double.parseDouble(campos[5]);
            profit=Double.parseDouble(campos[6]);
            profit_Margin=Double.parseDouble(campos[7]);

            peliAgregar= new Pelicula(titulo, year, year, domestic_Gross, foreign_Gross, worldwide_Gross, profit_Margin, mes, profit);
            peliculas.add(peliAgregar);

        }

        datos.close();
        return peliculas;
    }


    public static List<Pelicula> topMPeliculas(List<Pelicula> peliculas, int M, Comparator<Pelicula> comparator) {
        // Ordenar usando el Comparator proporcionado
        peliculas.sort(comparator.reversed()); // Reversed para orden descendente

        // Devolver las primeras M películas
        return peliculas.subList(0, Math.min(M, peliculas.size()));
        /*
         * peliculas.subList(0, Math.min(M, peliculas.size())) toma una sublista de las primeras M películas ordenadas.
El uso de Math.min(M, peliculas.size()) asegura que no intentemos acceder a más películas de las que hay en la lista.
 Si M es mayor que el tamaño de la lista, simplemente devuelve todas las películas disponibles.
         */
    }
}

public class PopulariyComparator implements Comparator<Pelicula> {

    @Override
    public int compare(Pelicula primeraPeli, Pelicula segundaPeli) {
       return Integer.compare(primeraPeli.getPopularity(), segundaPeli.getPopularity());
    }

}
public class BudgetComparator implements Comparator<Pelicula> {

    @Override
    public int compare(Pelicula primeraPeli, Pelicula segundaPeli) {
       return Integer.compare(primeraPeli.getBudget(), segundaPeli.getBudget());
    }

}
public class RoiComparator implements Comparator<Pelicula> {

    @Override
    public int compare(Pelicula primeraPeli, Pelicula segundaPeli) {
       return Integer.compare(primeraPeli.getRoi(), segundaPeli.getRoi());
    }

}
public class ProfitComparator implements Comparator<Pelicula> {

    @Override
    public int compare(Pelicula primeraPeli, Pelicula segundaPeli) {
       return Integer.compare(primeraPeli.getProfit(), segundaPeli.getProfit());
    }

}
