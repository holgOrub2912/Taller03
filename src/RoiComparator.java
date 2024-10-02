/* AUTORES: Isabela Arrubla Orozco, Fernando González Rivero */
public class RoiComparator implements ComparadorPelicula {

    @Override
    public int compare(Pelicula primeraPeli, Pelicula segundaPeli) {
       return Double.compare(primeraPeli.getRoi(), segundaPeli.getRoi());
       
    }

	@Override
	public String getCriterionName(){
		return "Roi";
	}

	@Override
	public double getValue(Pelicula p){
		return p.getRoi();
	}
}
