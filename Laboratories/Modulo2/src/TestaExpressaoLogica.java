import java.util.*;
public class TestaExpressaoLogica {
	
	public static void main(String[] args) {
		
		ExpressaoLogica nova_expressao;
		
		ExpressaoAtomica A = new ExpressaoAtomica("A");
		ExpressaoAtomica B = new ExpressaoAtomica("B");
		ExpressaoAtomica C = new ExpressaoAtomica("C");
		ExpressaoAtomica D = new ExpressaoAtomica("D");
		
		nova_expressao = 
				new ExpressaoImplicacao(
				new ExpressaoImplicacao((new ExpressaoOr(A, B)), new ExpressaoAnd(C, A)),new ExpressaoOr(new ExpressaoAnd(new ExpressaoNot(B), C), new ExpressaoAnd(A, D)));
		
		
		Map<String, Boolean> valoresVariaveis = new HashMap<>();
		
		valoresVariaveis.put("A", false);
		valoresVariaveis.put("B", true);
		valoresVariaveis.put("C", true);
		valoresVariaveis.put("D", false);
		
		System.out.println(nova_expressao.toString());
		System.out.println("Resultado da expressão lógica:");
		System.out.println(nova_expressao.eval(valoresVariaveis));
	}

}
