import java.util.*;
public abstract class ExpressaoBinaria implements ExpressaoLogica {
	
	private ExpressaoLogica Express�oLogica1, Express�oLogica2;
	
	public ExpressaoLogica GetExpress�oLogica1() {
		return this.Express�oLogica1;
	}
	
	public ExpressaoLogica GetExpress�oLogica2() {
		return this.Express�oLogica2;
	}
	
	public ExpressaoBinaria(ExpressaoLogica Express�oLogica1, ExpressaoLogica Express�oLogica2) {
		this.Express�oLogica1= Express�oLogica1;
		this.Express�oLogica2 = Express�oLogica2;
	}
}
