import java.util.*;
public class ExpressaoAtomica implements ExpressaoLogica  {
	private String variavel;
	
	public String GetVariavel() {
		return this.variavel;
	}
	
	public ExpressaoAtomica(String variavel) {
		this.variavel = variavel;
	}
		
	@Override
	public String toString() {
		return GetVariavel();
	}
	
	@Override
	public boolean eval(Map<String, Boolean> valoresVariaveis) {
		return valoresVariaveis.get(GetVariavel());
	}

}
