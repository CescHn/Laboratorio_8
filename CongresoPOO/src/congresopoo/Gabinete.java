package congresopoo;

import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class Gabinete extends Persona {
    private String Titulo, Secretaria;
    
    //Constructores

    public Gabinete() {
        super();
        this.Titulo = "";
        this.Secretaria = "";
    }
    
    public Gabinete(char[] Identidad, String Nombre, short Edad, char Genero, char[] Celular, String Correo, String Titulo, String Secretaria) {
        super(Identidad, Nombre, Edad, Genero, Celular, Correo);
        this.Titulo = Titulo;
        this.Secretaria = Secretaria;
    }
    
    //Procedimientos Setter's

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public void setSecretaria(String Secretaria) {
        this.Secretaria = Secretaria;
    }
    
    //Funciones Getter's

    public String getTitulo() {
        return Titulo;
    }

    public String getSecretaria() {
        return Secretaria;
    }
    
    // Otros Métodos

    @Override
    public String toString() {
        return "Gabinete{" + super.toString() + "Titulo=" + Titulo + ", Secretaria=" + Secretaria + '}';
    }
    
    @Override
    public void ImprimirCLI(int i){
        super.ImprimirCLI(i);
        System.out.printf("|%25s|%25s|\n",Titulo,Secretaria);
    }
    
    @Override
    public void Borde(){
        super.Borde();
        System.out.print("+-------------------------+-------------------------+\n");
    }
    
    @Override
    public void Guardar(PrintWriter Linea){
        super.Guardar(Linea);
        Linea.print( "\t"+Titulo + "\t" + Secretaria +"\n");
    }
    
    @Override
    public void Leer(int i){
        super.Leer(i);
        Titulo = JOptionPane.showInputDialog("Ingrese el Título del Ministro "+ super.getNombre() +":");
        Secretaria = JOptionPane.showInputDialog("Ingrese la Secretaria de Estado del Ministro "+ super.getNombre() +":");
    }
}
