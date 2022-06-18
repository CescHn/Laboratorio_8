package congresopoo;

import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class CSJ extends Persona {
    private String Especialidad;
    
    //Constructores

    public CSJ() {
        super();
        this.Especialidad = "";
    }
    
    public CSJ(char[] Identidad, String Nombre, short Edad, char Genero, char[] Celular, String Correo, String Especialidad) {
        super(Identidad, Nombre, Edad, Genero, Celular, Correo);
        this.Especialidad = Especialidad;
    }

    public String getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(String Especialidad) {
        this.Especialidad = Especialidad;
    }
    
    
    
    // Otros Métodos

    @Override
    public String toString() {
        return "Gabinete{" + super.toString() + "Especialidad=" + Especialidad + '}';
    }
    
    @Override
    public void ImprimirCLI(int i){
        super.ImprimirCLI(i);
        System.out.printf("|%25s|\n",Especialidad);
    }
    
    @Override
    public void Borde(){
        super.Borde();
        System.out.print("+-------------------------+-------------------------+\n");
    }
    
    @Override
    public void Guardar(PrintWriter Linea){
        super.Guardar(Linea);
        Linea.print( "\t"+Especialidad + "\t" +"\n");
    }
    
    @Override
    public void Leer(int i){
        super.Leer(i);
        Especialidad = JOptionPane.showInputDialog("Ingrese la Especialidad del Abogado "+ super.getNombre() +":");
        
    }
}