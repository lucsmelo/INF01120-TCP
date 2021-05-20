import java.util.*;

public class ExpressaoAnd extends ExpressaoBinaria{
	
	public ExpressaoAnd(ExpressaoLogica ExpressãoLogica1, ExpressaoLogica ExpressãoLogica2) {
		super(ExpressãoLogica1, ExpressãoLogica2);
	}
	
	@Override
	public String toString() {
		return "("+GetExpressãoLogica1().toString() + " and " + GetExpressãoLogica2() + ")";
	}
	
	@Override
	public boolean eval(Map<String, Boolean> valoresVariaveis) {
		return GetExpressãoLogica1().eval(valoresVariaveis) && GetExpressãoLogica2().eval(valoresVariaveis);
	}

}
