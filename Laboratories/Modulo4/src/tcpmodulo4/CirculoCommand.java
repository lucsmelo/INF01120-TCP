package tcpmodulo4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import tcpmodulo4Exceptions.IllegalMeasurementException;

public class CirculoCommand extends AbstractFormaGeometricaCommand {

	
	@Override
	public void Execute() throws IOException {
		PrintGeometricFormData();	
	}

	@Override
	public void PrintGeometricFormData() throws IOException {
		Circulo Circle =InstantiateGeometricForm();
		if (!Circle.DataValidation()) { 
			throw new IllegalMeasurementException("Invalid Input (Input can not be negative or equal to zero(null)"); 
			}
		
		System.out.println(Circle.GetInformation());
	}

	@Override
	public <T> T InstantiateGeometricForm() throws IOException {
		double [] CircleData = ReadGeometricFormData();
		
		Circulo Circle = new Circulo(CircleData[0]);
		return (T) Circle;
	}

	@Override
	public double[] ReadGeometricFormData() throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(" Type the circle radius: ");
		Double radius=Double.parseDouble(reader.readLine()); 
		
	
			double [] CircleData = {radius};
			return CircleData;
		
	}
	


}
