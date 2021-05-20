import java.util.*;
public class ExpressaoNot extends ExpressaoUnaria{
	
	public ExpressaoNot(ExpressaoLogica exp_logica) {
		super(exp_logica);
	}
	
	@Override
	public String toString() {
		return "(not " + GetExpressaoLogica().toString() + ")";
	}

	@Override
	public boolean eval(Map<String, Boolean> valoresVariaveis) {
		return !GetExpressaoLogica().eval(valoresVariaveis);
	}

}
