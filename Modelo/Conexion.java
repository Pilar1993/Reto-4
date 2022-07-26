package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    Connection connection;
    
    String driver = "com.mysql.cj.jdbc.Driver";
    String cadenaConexion = "jdbc:mysql://localhost:3306/reto1_g53";
    String usuario = "root";
    String contrasena = "";
	    
    public Conexion(){
	try {
	    Class.forName(driver);
	    connection = DriverManager.getConnection(cadenaConexion, usuario, contrasena);
	    if(connection != null){
		System.out.println("Conexion exitosa con la base de datos");
	    }    
	} catch (ClassNotFoundException | SQLException e) {
	    System.out.println("No se puede establecer conexion  con la base de datos");
	}
    }

    public Connection getConnection() {
	return connection;
    }	
}
	
    
