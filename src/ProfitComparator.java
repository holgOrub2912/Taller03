public class ProfitComparator implements ComparadorPelicula {

    @Override
    public int compare(Pelicula primeraPeli, Pelicula segundaPeli) {
       return Double.compare(primeraPeli.getProfit(), segundaPeli.getProfit());
    }

	@Override
	public String getCriterionName(){
		return "Ganancias";
	}
}
