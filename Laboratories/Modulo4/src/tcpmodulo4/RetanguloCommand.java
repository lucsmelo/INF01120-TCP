package tcpmodulo4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import tcpmodulo4Exceptions.IllegalMeasurementException;

public class RetanguloCommand extends AbstractFormaGeometricaCommand {

public void Execute() throws IOException {
		
	PrintGeometricFormData();
	}

@Override
	public void PrintGeometricFormData() throws IOException {
	Retangulo Rectangle = InstantiateGeometricForm();
	if (!Rectangle.DataValidation()) { 
		throw new IllegalMeasurementException("Invalid Input (Input can not be negative or equal to zero(null)"); 
		}
	
	 System.out.println(Rectangle.GetInformation());
		
	}

	@Override
	public <T> T  InstantiateGeometricForm () throws IOException {
		double [] RectangleData = ReadGeometricFormData();
		Retangulo Rectangle = new Retangulo(RectangleData[0],RectangleData[1]);
		return (T) Rectangle;
	}

	@Override
	public double[] ReadGeometricFormData() throws  IOException {
		
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(" Type the rectangle length: ");
		Double length=Double.parseDouble(reader.readLine());
		System.out.print(" Type the rectangle width: ");
		Double width=Double.parseDouble(reader.readLine());
			double []RectangleData = {width,length};
			return RectangleData;
	}

/* Percebi que era problemático aplicar o programa  dessa forma , pois precisariam ser lidas 3 vezes do teclado, optei por mudar um pouco para ficar mais legivel
 * 
	@Override
	public double CalcArea() throws IOException {
		// TODO Auto-generated method stub
		Retangulo r = Instancia();
		return r.GetArea();
	}


	@Override
	public double CalcPerimeter() throws IOException {
		// TODO Auto-generated method stub
		Retangulo r = Instancia();
		return r.GetPerimeter();
		
	}
*/
	

}
