package tcpmodulo4;

public class Circulo extends AbstractFormaGeometrica{
	
	private double radius;
	
	
	public double GetRadius() {
		return radius;
	}

	public void SetRadius(double radius) {
		this.radius = radius;
	}
	
	public Circulo(Number radius) {
		this.radius = radius.doubleValue();
	}
	
	@Override
	public double GetArea() {
		return Math.PI * GetRadius() * GetRadius();
	}

	@Override
	public double GetPerimeter() {
		return 2 * Math.PI * GetRadius();
	}
	
	
	@Override
	public String GetInformation() {
		return "Radius is equal: \n"+GetRadius()+ "\nArea is equal:  \n" + GetArea() + " \nPerimeter is equal:  \n" + GetPerimeter();
	}

	@Override
	public boolean DataValidation() {
		if(GetRadius()>0 )
			return true;
		else
			return false;
	}

}
