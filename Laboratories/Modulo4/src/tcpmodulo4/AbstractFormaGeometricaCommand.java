package tcpmodulo4;

import java.io.IOException;

public abstract class AbstractFormaGeometricaCommand implements FormaGeometricaCommand {

	public abstract void PrintGeometricFormData() throws IOException;
	public abstract <T> T InstantiateGeometricForm() throws IOException;
	public abstract double[] ReadGeometricFormData() throws IOException ;
	
//Métodos Problemáticos 
//	public abstract double CalcArea() throws IOException ;
//public abstract double CalcPerimeter() throws IOException ;
	

}
