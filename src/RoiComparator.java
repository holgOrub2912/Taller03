public class RoiComparator implements Comparator<Pelicula> {

    @Override
    public int compare(Pelicula primeraPeli, Pelicula segundaPeli) {
       return Double.compare(primeraPeli.getRoi(), segundaPeli.getRoi());
       
    }

}
