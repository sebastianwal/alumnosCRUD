/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package walter.java_crud_mysql;

// Importación de clases necesarias para interfaces gráficas y SQL

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTable;


public class CAlumnos {
    // Atributos de la clase CAlumnos
    int codigo;
    String nombreAlumnos;
    String apelidoAlumnos;
    
    // Getters y Setters

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreAlumnos() {
        return nombreAlumnos;
    }

    public void setNombreAlumnos(String nombreAlumnos) {
        this.nombreAlumnos = nombreAlumnos;
    }

    public String getApelidoAlumnos() {
        return apelidoAlumnos;
    }

    public void setApelidoAlumnos(String apelidoAlumnos) {
        this.apelidoAlumnos = apelidoAlumnos;
    }
    
    // Método para insertar un nuevo alumno en la base de datos
    
    public void InsertarAlumno(JTextField paramNombres, JTextField paramApellidos) {
       
        setNombreAlumnos(paramNombres.getText());
        setApelidoAlumnos(paramApellidos.getText());

        CConexion objetoConexion = new CConexion();
        
        
        String consulta = "insert into Alumnos (nombres, apellidos) values(?, ?);";

        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setString(1, getNombreAlumnos());
            cs.setString(2, getApelidoAlumnos());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se inserto correctamente el alumno");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se inserto correctamente el alumno, error: " + e.toString());
        }
    }
    
    // Método para mostrar los alumnos en una tabla
    
    public void MostrarAlumnos(JTable paramTablaTotalAlumnos){ 
    
        CConexion objetoConexion = new CConexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        paramTablaTotalAlumnos.setRowSorter(OrdenarTabla);
        
        String sql="";
        
        // Definición de columnas en el modelo
        
        modelo.addColumn("Id");
        modelo.addColumn("Nombres");
        modelo.addColumn("apellidos");
        
        paramTablaTotalAlumnos.setModel (modelo);
        
        
        sql = "select * from Alumnos;";
        
        
        String[] datos = new String[3];
        Statement st;
        
        
        try {
            st= objetoConexion.estableceConexion().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            // Llenado de datos en la tabla
            
            while (rs.next()) {
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                
               modelo.addRow(datos);
             
            }
            
            paramTablaTotalAlumnos.setModel (modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrsr los registros, error: "+e.toString());
        }
    
    }
    
    // Método para seleccionar un alumno de la tabla y mostrarlo en campos de texto
    
    public static void SeleccionarAlumno(JTable paramTablaAlumnos, JTextField paramId, JTextField paramNombres, JTextField paramApellidos) 
    {
        
        try {
            
            int fila = paramTablaAlumnos.getSelectedRow();
            
            if (fila >=0){
                paramId.setText((String) (paramTablaAlumnos.getValueAt(fila, 0)));
                paramNombres.setText((String) (paramTablaAlumnos.getValueAt(fila, 1)));
                paramApellidos.setText((String) (paramTablaAlumnos.getValueAt(fila, 2)));
            }
            
            else
            {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
            
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error de seleccion, error: "+e.toString());
        }
  
        
        
    }
    
    // Método para modificar datos de un alumno
    
    public void ModificarAlumnos (JTextField paramCodigo, JTextField paramNombres, JTextField paramApellidos){
    
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        setNombreAlumnos(paramNombres.getText());
        setApelidoAlumnos(paramApellidos.getText());
        
        
        CConexion objeConexion = new CConexion();
        
        String consulta = "update Alumnos set alumnos.nombres =?, alumnos.apellidos =? where alumnos.id=?";
        
        try {
            
            CallableStatement cs = objeConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1, getNombreAlumnos());
            cs.setString(2, getApelidoAlumnos());
            cs.setInt(3, getCodigo());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "modificacion exitosa.");
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "No se modifico, error: "+e.toString());
            
        }
        
    }
    
    // Método para eliminar un alumno
    
    public void EliminarAlumnos(JTextField paramCodigo){
    
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "DELETE FROM Alumnos where alumnos.id=?;";
        
        try {
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setInt(1, getCodigo());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente.");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se elimino, error: "+e.toString());
        }
    
    }
}
