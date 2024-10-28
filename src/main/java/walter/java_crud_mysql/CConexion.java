/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package walter.java_crud_mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class CConexion {
    
    // Variables para conexión a la base de datos
    
    Connection conectar = null;
    
    String usuario ="root";
    String contraseña ="1929ana";
    String bd ="bdescuela";
    String ip ="localhost";
    String puerto ="3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    // Método para establecer la conexión con la base de datos
    
    public Connection estableceConexion (){
     
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,contraseña);
            //JOptionPane.showMessageDialog(null, "La conexion se ha realizado con exito");
                    
                    
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al conectarse ala base de datos, error: "+e.toString());
        }
        return conectar; 
    } 
}
