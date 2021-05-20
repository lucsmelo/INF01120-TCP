package tcpmodulo4;

public class Quadrado extends AbstractFormaGeometrica {

	private double side;
	
	public double GetSide() {
		return side;
	}

	public void SetSide(double side) {
		this.side = side;
	}
	
	public Quadrado (Number side) {
		this.side = side.doubleValue();
	}
	
	@Override
	public double GetArea() {
		return GetSide() * GetSide();
	}

	@Override
	public double GetPerimeter() {
		return 4 * GetSide();
	}
	
@Override
	public String GetInformation() {
		return "Side is equal: \n"+GetSide()+ "\nArea is equal:  \n" + GetArea() + " \nPerimeter is equal:  \n" + GetPerimeter();
	}

@Override
public boolean DataValidation() {
	if(GetSide()>0)
		return true;
	else
		return false;
}
	
	
	



}
