/* AUTORES: Isabela Arrubla Orozco, Fernando González Rivero */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
//usar BarChart de la libreia JFreeChart

public class Pelicula implements Comparable<Pelicula> {

    private String titulo; //1
    private int year; // 2
    private int production_budget; // 3
    private double domestic_Gross; // 4
    private double foreign_Gross; // 5
    private double worldwide_Gross; // 6
    private int mes; // 7
    private double profit; // 8
    private double profit_Margin; // 9
    private double roi; // 10
    private double pct_foreign; // 11
    private String match_key; // 12
    private double popularity; // 13
    private String release_date; // 14
    private double vote_average; // 16
    private String genre_list; // 18

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
    public int getYear(){
		return this.year;
    }
    public String getTitulo(){
		return this.titulo;
    }
    
    public Pelicula(String titulo, int year, int production_budget, double domestic_Gross, double foreign_Gross,
    double worldwide_Gross,int mes, double profit, double profit_Margin, double roi, double pct_foreign, String match_key,
    double popularity, String release_date, double vote_average, String genre_List) {

        this.titulo=titulo;
        this.year=year;
        this.production_budget=production_budget;
        this.domestic_Gross=domestic_Gross;
        this.foreign_Gross=foreign_Gross;
        this.worldwide_Gross=worldwide_Gross;
        this.mes= mes;
        this.profit=profit;
        this.profit_Margin=profit_Margin;
        this.roi=roi;
        this.pct_foreign=pct_foreign;
        this.match_key=match_key;
        this.popularity=popularity;
        this.release_date=release_date;
        this.vote_average=vote_average;
        this.genre_list=genre_List;

    }

    @Override
    public int compareTo(Pelicula o) {
        return Integer.compare(this.year, o.year);
    }

    @Override
    public String toString(){
        return String.format("%s (%d)", this.titulo, this.year);
    }
    
    public static List<Pelicula> leerPelisdeCSV(String ruta_archivoCSV)throws IOException{
        //Formato Archivo CSV:
        // index,movie,year,production_budget,domestic_gross,foreign_gross,worldwide_gross,month,profit,
        // profit_margin,roi,pct_foreign,match_key,popularity,release_date,original_language,vote_average,vote_count,genre_list
        //0,Avatar,2009,425000000,760507625,2015837654,2776345279,12,2351345279,0.846921057256582,5.532577127058824,0.7260759925098639,2009 Avatar,26.526,2009-12-18,en,7.4,18676,"['Action', 'Adventure', 'Fantasy', 'Science Fiction']"
        String titulo; //1
        int year; //2
        int production_budget; //3
        double domestic_Gross; //4
        double foreign_Gross; //5
        double worldwide_Gross; //6
        int mes; //7
        double profit; //8
        double profit_Margin; //9
        double roi; //10
        double pct_foreign; //11
        String match_key; //12
        double popularity; //13
        String release_date; //14
        double vote_average; //16
        String genre_list; //18

        Pelicula peliAgregar;

        List<Pelicula> peliculas = new ArrayList<>();
        HashSet<String> titulos = new HashSet<String>();
        FileReader archivo = new FileReader(ruta_archivoCSV);
        BufferedReader datos = new BufferedReader(archivo);
        String linea;
       
        // Leer la primera línea que contiene los headers (campos del archivo CSV)
        linea = datos.readLine(); 
        

        while ((linea=datos.readLine()) != null) {
            // regex tomada de https://stackoverflow.com/questions/15738918/splitting-a-csv-file-with-quotes-as-text-delimiter-using-string-split
            String[] campos = linea.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"); 
            titulo= campos[1];
            // Evitar repetir películas
            if (titulos.contains(titulo))
				continue;
			else
				titulos.add(titulo);
            year=Integer.parseInt(campos[2]);
            production_budget= Integer.parseInt(campos[3]);
            domestic_Gross= Double.parseDouble(campos[4]);
            foreign_Gross=Double.parseDouble(campos[5]);
            worldwide_Gross=Double.parseDouble(campos[6]);
            mes= Integer.parseInt(campos[7]);
            profit=Double.parseDouble(campos[8]);
            profit_Margin = !campos[9].equals("-inf")
                ? Double.parseDouble(campos[9])
                : Double.NEGATIVE_INFINITY;
            roi= Double.parseDouble(campos[10]);
            pct_foreign=Double.parseDouble(campos[11]);
            match_key=campos[12];
            popularity=Double.parseDouble(campos[13]);
            release_date=campos[14];
            vote_average=Double.parseDouble(campos[16]);
            genre_list=campos[18];
            
            peliAgregar= new Pelicula(titulo, year, production_budget, domestic_Gross, foreign_Gross, worldwide_Gross, mes, profit,
             profit_Margin, roi, pct_foreign, match_key, popularity, release_date, vote_average, genre_list);
            
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
