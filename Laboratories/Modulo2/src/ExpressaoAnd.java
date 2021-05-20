import java.util.*;

public class ExpressaoAnd extends ExpressaoBinaria{
	
	public ExpressaoAnd(ExpressaoLogica Express�oLogica1, ExpressaoLogica Express�oLogica2) {
		super(Express�oLogica1, Express�oLogica2);
	}
	
	@Override
	public String toString() {
		return "("+GetExpress�oLogica1().toString() + " and " + GetExpress�oLogica2() + ")";
	}
	
	@Override
	public boolean eval(Map<String, Boolean> valoresVariaveis) {
		return GetExpress�oLogica1().eval(valoresVariaveis) && GetExpress�oLogica2().eval(valoresVariaveis);
	}

}
