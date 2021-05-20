import java.util.*;
public class ExpressaoOr extends ExpressaoBinaria {
	
	public ExpressaoOr(ExpressaoLogica ExpressãoLogica1, ExpressaoLogica ExpressãoLogica2) {
		super(ExpressãoLogica1, ExpressãoLogica2);
	}
	
	@Override
	public String toString() {
		return "("+GetExpressãoLogica1().toString() + " or " + GetExpressãoLogica2() + ")";
	}

	@Override
	public boolean eval(Map<String, Boolean> valoresVariaveis) {
		return GetExpressãoLogica1().eval(valoresVariaveis) || GetExpressãoLogica2().eval(valoresVariaveis);
	}

}
