package congresopoo;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public abstract class Gobierno {
    public abstract void Leer (int i);
    public abstract void ImprimirCLI(int i);
    public abstract void Borde();
    public abstract void Guardar(PrintWriter Linea);
 
    public int ValidarInt(String Texto){
        String Leer;
        int Cant;
        try {
            Leer = JOptionPane.showInputDialog(Texto);
            Cant = Integer.parseInt(Leer);
        } catch (java.lang.NumberFormatException e) {
            Cant = -1;
        }
        if (Cant <= -1) {
            JOptionPane.showMessageDialog(null, "Dato Incorrecto!!!\n"
                    + "Vuelva a Intentarlo!!!");
            Cant = ValidarInt(Texto);//Llamado Recursivo
        }
        return Cant;
    }
    
    public char ValidarGen(String Nombre){
        char Gen = JOptionPane.showInputDialog(null,"F.->Femenino.\nM.->Masculino",".:.Género de "+ Nombre +" .:.",0).toUpperCase().charAt(0);
        
        if(Gen=='F' || Gen=='M'){
            return Gen;
        }else{
            JOptionPane.showMessageDialog(null,"El Género Ingresado NO Existe!!!\n"
                    + "Favor Vuelva a Intentarlo!!!");
            return ValidarGen(Nombre);
        }
    }
    
    public String ValidarCorreo( String Nombre ){//c@2.l
        String Correo = JOptionPane.showInputDialog("Ingrese el Correo de "+Nombre+": ");
        boolean Arroba = false;
        boolean Punto = false;
        if( Correo.length()>=5 ){
            for (int i = 0; i < Correo.length(); i++) {
                if( Correo.charAt(i)=='@' ){
                    Arroba = true;
                }
                if( Arroba && Correo.charAt(i)=='.' ){
                    Punto = true;
                }
            }
        }
        if(!Arroba || !Punto){
            JOptionPane.showMessageDialog(null, "Favor Ingrese un correo Válido!!!");
            Correo = ValidarCorreo(Nombre);
        }
        
        return Correo;
    }
}
