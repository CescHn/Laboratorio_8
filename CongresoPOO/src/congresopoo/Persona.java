package congresopoo;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class Persona extends Gobierno{
    //Atributos
    private char Identidad[];
    private String Nombre;
    private short Edad;
    private char Genero;
    private char Celular[];
    private String Correo;
    
    //Constructores
    public Persona() {
        this.Identidad = "".toCharArray();
        this.Nombre = "";
        this.Edad = 0;
        this.Genero = ' ';
        this.Celular = "".toCharArray();
        this.Correo = "";
    }
    
    public Persona(char[] Identidad, String Nombre, short Edad, char Genero, char[] Celular, String Correo) {
        this.Identidad = Identidad;
        this.Nombre = Nombre;
        this.Edad = Edad;
        this.Genero = Genero;
        this.Celular = Celular;
        this.Correo = Correo;
    }
    
    //Procedimientos Setter's - void
    public void setIdentidad(char[] Identidad) {
        this.Identidad = Identidad;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setEdad(short Edad) {
        this.Edad = Edad;
    }

    public void setGenero(char Genero) {
        this.Genero = Genero;
    }

    public void setCelular(char[] Celular) {
        this.Celular = Celular;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }
        
    //Funciones Getter's - return
    public char[] getIdentidad() {
        return Identidad;
    }

    public String getNombre() {
        return Nombre;
    }

    public short getEdad() {
        return Edad;
    }

    public char getGenero() {
        return Genero;
    }

    public char[] getCelular() {
        return Celular;
    }

    public String getCorreo() {
        return Correo;
    }
    
    //Otros Métodos
    @Override
    public String toString(){
        return "Persona{" + "Identidad=" + String.valueOf(Identidad) + ", Nombre=" + Nombre + ", Edad=" + Edad + ", Genero=" + Genero + ", Celular=" + String.valueOf(Celular) + ", Correo=" + Correo + '}';
    }

    @Override
    public void Leer(int i) {
        this.Identidad = JOptionPane.showInputDialog("Ingrese el Número de Identidad con Guiones: ").toCharArray();
        this.Nombre = JOptionPane.showInputDialog("Ingrese el Nombre Completo: ");
        this.Edad = (short)(ValidarInt("Digite la Edad: ") );
        this.Genero = ValidarGen( getNombre() );
        this.Celular = JOptionPane.showInputDialog("Ingrese el Número de Celular con Guiones: ").toCharArray();
        this.Correo = ValidarCorreo( getNombre() );
    }
    
    @Override
    public void ImprimirCLI(int i){
        System.out.printf("|%8d|%15s|%50s|%4d|%6s|%16s|%20s|",(i+1),String.valueOf(Identidad),Nombre,Edad,Genero,String.valueOf(Celular),Correo);
    }
    
    @Override
    public void Borde(){
        System.out.print("+--------+---------------+--------------------------------------------------+----+------+----------------+--------------------+");
    }
    
    @Override
    public void Guardar(PrintWriter Linea){
        Linea.print(String.valueOf(Identidad)+"\t"+Nombre+"\t"+Edad+"\t"+Genero+"\t"+String.valueOf(Celular)+"\t"+Correo);
    }
    
}
