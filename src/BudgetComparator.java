public class BudgetComparator implements ComparadorPelicula {

    @Override
    public int compare(Pelicula primeraPeli, Pelicula segundaPeli) {
       return Double.compare(primeraPeli.getBudget(), segundaPeli.getBudget());
    }

	@Override
	public String getCriterionName(){
		return "Presupuesto";
	}

	@Override
	public double getValue(Pelicula p){
		return p.getBudget();
	}
}
