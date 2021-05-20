package ui;

import java.util.Scanner;

import enums.UIEnum;

public class TextUI {
	
	GradeReviewCommand gradeReviewCommand;
	ResearchBinderCommand researchBinderCommand;
	ResearchSelectionCommand researchSelectionCommand;
	
	public TextUI() {
		this.gradeReviewCommand = new GradeReviewCommand();
		this.researchBinderCommand = new ResearchBinderCommand();
		this.researchSelectionCommand = new ResearchSelectionCommand();
	}
	public void init() {
		this.showMenu();
	}
	
	public void showMenu() {
		int stateController = 0;
		Scanner kb = new java.util.Scanner(System.in);
		System.out.println("Bem-vindo ao Trabalho Parte II");
		do {
			System.out.println("\n\n[Selecione uma op��o]");
			System.out.println("0 - Atribui��o de nota");
			System.out.println("1 - Aloca��o");
			System.out.println("2 - Sele��o de artigos");
			System.out.print("Digite sua escolha: ");
			stateController = kb.nextInt();
			this.handleOptionSelection(stateController);
		} while (stateController != -1);
	}
	
	public void handleOptionSelection(int option) {
		switch (option) {
			case UIEnum.GRADE_REVIEW:
				this.gradeReviewCommand.execute();
			break;
			case UIEnum.RESEARCH_BINDER:
				this.researchBinderCommand.execute();
			break;
			case UIEnum.RESEARCH_SELECTION:
				this.researchSelectionCommand.execute();
			break;
			
			case UIEnum.EXIT:
				System.out.println("[Programa encerrado]");
			break;
			
			default:
				System.out.println("[Comando n�o reconhecido. Tente novamente]");
		}
	}
}
