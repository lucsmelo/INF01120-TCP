package tcpmodulo4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import tcpmodulo4Exceptions.IllegalMeasurementException;


public class QuadradoCommand extends AbstractFormaGeometricaCommand {

	public void Execute() throws IOException {	
		PrintGeometricFormData();
	}

	
	@Override
	public void PrintGeometricFormData() throws IOException {
		Quadrado Square = InstantiateGeometricForm();
		if (!Square.DataValidation()) { 
			throw new IllegalMeasurementException("Invalid Input (Input can not be negative or equal to zero(null)"); 
			}
		System.out.println(Square.GetInformation());	
	}

	@Override
	public <T> T InstantiateGeometricForm() throws IOException {
		double [] SquareData = ReadGeometricFormData();
		Quadrado Square = new Quadrado(SquareData[0]);
		 return  (T) Square;
	}
	
	@Override
	
	public double[] ReadGeometricFormData() throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Type the square side: ");
		Double lado=Double.parseDouble(reader.readLine());
			double []  SquareData = {lado};
			return  SquareData;
		}
	
/* Percebi que era problemático aplicar o programa  dessa forma , pois precisariam ser lidas 3 vezes do teclado, optei por mudar um pouco para ficar mais legivel
	@Override
	public double CalcArea() throws IOException {
		Quadrado q = Instancia();
		return q.GeArea();
	}

	@Override
	public double CalcPerimeter() throws IOException {
		Quadrado q = Instancia();
		return q.GetPerimeter();
	}
	*/
}

	

