package Factories;

import com.epam.jmp.hibernate.DAO.DAO;
import com.epam.jmp.hibernate.DAO.JavaDBImpl;


public class DAOFactory {
	public static DAO getClassFromFactory() {
		return new JavaDBImpl();
	}
}