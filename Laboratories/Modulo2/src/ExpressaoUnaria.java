import java.util.*;
public abstract class ExpressaoUnaria implements ExpressaoLogica {
	private ExpressaoLogica ExpressãoLogica;
	
	public ExpressaoLogica GetExpressaoLogica() {
		return this.ExpressãoLogica;
	}
	public ExpressaoUnaria(ExpressaoLogica expressaoLogica) {
		this.ExpressãoLogica = expressaoLogica;
	}
}
