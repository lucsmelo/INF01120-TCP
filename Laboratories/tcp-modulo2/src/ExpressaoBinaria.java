import java.util.*;
public abstract class ExpressaoBinaria implements ExpressaoLogica {
	
	private ExpressaoLogica ExpressãoLogica1, ExpressãoLogica2;
	
	public ExpressaoLogica GetExpressãoLogica1() {
		return this.ExpressãoLogica1;
	}
	
	public ExpressaoLogica GetExpressãoLogica2() {
		return this.ExpressãoLogica2;
	}
	
	public ExpressaoBinaria(ExpressaoLogica ExpressãoLogica1, ExpressaoLogica ExpressãoLogica2) {
		this.ExpressãoLogica1= ExpressãoLogica1;
		this.ExpressãoLogica2 = ExpressãoLogica2;
	}
}
