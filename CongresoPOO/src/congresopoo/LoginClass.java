package congresopoo;

import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class LoginClass extends Gobierno {
    public String Usuario, Clave, Rol;
    
    //Constructores

    public LoginClass() {

        this.Usuario = "";
        this.Clave = "";
        this.Rol = "";
    }
    
    public LoginClass(String Usuario, String Clave, String Rol) {
        this.Usuario = Usuario;
        this.Clave = Clave;
        this.Rol = Rol;
    }


    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String Clave) {
        this.Clave = Clave;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String Rol) {
        this.Rol = Rol;
    }
    
    
    
    // Otros Métodos

     @Override
    public String toString(){
        return "LoginClass{" + ", usuario=" + Usuario + ", clave=" + Clave + ", rol=" + Rol  + '}';
    }

    @Override
    public void Leer(int i) {
        this.Usuario = JOptionPane.showInputDialog("Ingrese el Usuario: ");
        this.Clave = JOptionPane.showInputDialog("Ingrese la Clave: ");
        this.Rol = JOptionPane.showInputDialog("Ingrese el Rol: ");
    }
    
    @Override
    public void ImprimirCLI(int i){
        System.out.printf("|%8d|%15s|%50s|",(i+1),Usuario, Clave, Rol);
    }
    
    @Override
    public void Borde(){
        System.out.print("+--------+---------------+---------------");
    }
    
    @Override
    public void Guardar(PrintWriter Linea){
        Linea.print(Usuario+"\t"+Clave+"\t"+Rol);
    }
}