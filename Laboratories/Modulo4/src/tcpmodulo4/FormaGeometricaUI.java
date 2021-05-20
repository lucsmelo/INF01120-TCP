package tcpmodulo4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import  tcpmodulo4Exceptions.IllegalMeasurementException;
public class FormaGeometricaUI {
	
	Map<String, FormaGeometricaCommand> mapa;
	
	public FormaGeometricaUI() {
		mapa = new  HashMap<String, FormaGeometricaCommand>();
		mapa.put("circle", new CirculoCommand());
		mapa.put("square", new QuadradoCommand());
		mapa.put("rectangle", new RetanguloCommand());
	}
	
	public void ShowUI() throws IOException {

			
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(" Type a command(circle,square or rectangle). Don't digit anything if wanna exit");
		String option = reader.readLine();
		
		while (!option.equals("")) {
			try {
				FormaGeometricaCommand ChosenForm = mapa.get(option);
				ChosenForm.Execute();
			}
		 catch (IllegalMeasurementException e) {
			 	System.out.println(e.getMessage());
		 	}
		catch (Exception e) {
				System.out.println("Invalid Option");
			}
			
				System.out.print("Type a command: ");
				option = reader.readLine();
			
		}

	}
	
	

}
