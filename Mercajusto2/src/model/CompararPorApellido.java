package model;
import java.util.Comparator;
 
public class CompararPorApellido implements Comparator<Employee>{

	@Override
	public int compare(Employee p1, Employee p2) {
		if(p1.getLastName().compareTo(p2.getLastName())<0)
		return -1;
		
		if(p1.getLastName().compareTo(p2.getLastName())>0)
			return 1;
		
		return 0;
	}
}
