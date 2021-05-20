package tcpmodulo4;

public abstract class AbstractFormaGeometrica implements FormaGeometrica {

	public String GetInformation() {
		return "Area is equal:  \n" + GetArea() + " \nPerimeter is equal:  \n" + GetPerimeter();
	}
	public abstract boolean DataValidation();

}
