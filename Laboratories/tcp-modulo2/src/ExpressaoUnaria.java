import java.util.*;
public abstract class ExpressaoUnaria implements ExpressaoLogica {
	private ExpressaoLogica Express�oLogica;
	
	public ExpressaoLogica GetExpressaoLogica() {
		return this.Express�oLogica;
	}
	public ExpressaoUnaria(ExpressaoLogica expressaoLogica) {
		this.Express�oLogica = expressaoLogica;
	}
}
