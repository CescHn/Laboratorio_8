package congresopoo;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class Diputado extends  Persona{
    private String Cargo, Partido;//Declaración de Atributos

    public Diputado() {//Constructor Vacío (Creado por el Programador)
        super();
        this.Cargo = "";
        this.Partido = "";
    }
    
    //Constructor Full (Generado)
    public Diputado(char[] Identidad, String Nombre, short Edad, char Genero, char[] Celular, String Correo, String Cargo, String Partido) {//Constructor Full
        super(Identidad,Nombre,Edad,Genero,Celular,Correo);
        this.Cargo = Cargo;
        this.Partido = Partido;
    }

    //Procedimientos Setter's (Generado)
    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    public void setPartido(String Partido) {
        this.Partido = Partido;
    }

    //Funciones Getter's (Generados)
    public String getCargo() {
        return Cargo;
    }

    public String getPartido() {
        return Partido;
    }
    
    //Otros Métodos (Creados por el Programador)

    @Override
    public String toString() {
        return "Diputado{" + super.toString() + ", Cargo=" + Cargo + ", Partido=" + Partido + '}';
    }
    
    @Override
    public void ImprimirCLI(int i){
        super.ImprimirCLI(i);
        System.out.printf("|%20s|%15s|\n",Cargo,Partido);
    }
    
    @Override
    public void Borde(){
        super.Borde();
        System.out.print("+--------------------+---------------+\n");
    }
    
    @Override
    public void Guardar(PrintWriter Linea){
        super.Guardar(Linea);
        Linea.print( "\t"+Cargo + "\t" + Partido +"\n");
    }
    
    @Override
    public void Leer(int i){
        super.Leer(i);
        Cargo = JOptionPane.showInputDialog("Ingrese el Cargo del Diputado "+ super.getNombre() +":");
        Partido = JOptionPane.showInputDialog("Ingrese el Partido del Diputado "+ super.getNombre() +":");
    }
    
}