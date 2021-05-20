package tcpmodulo4;

public class Retangulo extends AbstractFormaGeometrica {

		private double width;
		private double length;
		
	
	public double GetWidth() {
		return width;
	}

	public void SetWidth(double width) {
		this.width =width;
	}

	public double GetLength() {
		return length;
	}

	public void SetLength(double length) {
		this.length= length;
	}
	
	public Retangulo(Number width, Number length) {
		this.width  = width.doubleValue();
		this.length = length.doubleValue();
	}
	
	@Override
	public double GetArea() {
		return GetWidth() * GetLength();
	}

	@Override
	public double GetPerimeter() {
		return (2 * GetWidth()) + (2 * GetLength());
	}
	
	@Override
	public String GetInformation() {
		return "Length is equal: \n"+GetLength()+"\nWidth is equal: \n"+GetWidth()+ "\nArea is equal:  \n" + GetArea() + " \nPerimeter is equal:  \n" + GetPerimeter();
	}

	@Override
	public boolean DataValidation() {
		if(GetLength() > 0 && GetWidth() > 0)
			return true;
		else
			return false;
	}


}
