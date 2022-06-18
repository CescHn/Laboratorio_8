package congresopoo;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class Programa {
    public String Leer, Consulta;
    public Connection Con;
    public Statement Pila;

    public char Menu(){
        char Op;
        
        try {
            Op = JOptionPane.showInputDialog("***Menú Principal***\n"
                    + "A.-> Mostrar.\n"
                    + "B.-> Guardar.\n"
                    + "C.-> Registrar.\n"
                    + "D.-> Modificar.\n"
                    + "E.-> Eliminar.\n"
                    + "F.-> Vaciar.\n"
                    + "G.-> Salir.\n"
                    + "Su Elección Es:").toUpperCase().charAt(0);
        } catch (java.lang.NullPointerException e) {
            Op = 'G';
        } catch (java.lang.StringIndexOutOfBoundsException e){
            Op = ' ';
        }
        
        if(Op<'A' || Op>'G'){
            JOptionPane.showMessageDialog(null, "La Opción Ingresada es Incorrecta!!!\n"
                    + "Favor vuelva a intentarlo!!!");
            Op = Menu();//Llamado Recursivo
        }        
        return Op;
    }
    
    public char SubMenu(){
        char Op = JOptionPane.showInputDialog("a.-> Gabinete de Gobierno.\n"
                + "b.-> Diputados del Congreso Nacional.\n"
                + "c.-> Magistrados de la Corte Suprema de Justicia.\n"
                + "d.-> Regresar al Menú Anterior.\n"
                + "Su Elección Es:").toLowerCase().charAt(0);
        
        if(Op<'a' || Op>'d'){
            JOptionPane.showMessageDialog(null, "La Opción Ingresada es Incorrecta!!!\n"
                    + "Favor vuelva a intentarlo!!!");
            Op = SubMenu();
        }
        return Op;
    }
    
    public char MenuModificarDiputado() {//Alessandra
        char Op;

        Op = JOptionPane.showInputDialog("***Menú Modificar***\n"
                + "a.-> Identidad.\n"
                + "b.-> Nombre.\n"
                + "c.-> Edad.\n"
                + "d.-> Genero.\n"
                + "e.-> Celular.\n"
                + "f.-> Correo.\n"
                + "g.-> Cargo.\n"
                + "h.-> Partido.\n"
                + "Su Elección Es:").toLowerCase().charAt(0);

        if (Op < 'a' || Op > 'h') {
            JOptionPane.showMessageDialog(null, "La Opción Ingresada NO Existe!!!\n"
                    + "Favor Vuelva a Intentarlo!!!");

            Op = MenuModificarDiputado();//Llamado Recursivo
        }

        return Op;
    }
    
    public char MenuModificarGabinete() {//Ariel
        char Op;
        
        Op = JOptionPane.showInputDialog("**Menú Modificar**\n"
                + "a.-> Identidad.\n"
                + "b.-> Nombre.\n"
                + "c.-> Edad.\n"
                + "d.-> Genero.\n"
                + "e.-> Celular.\n"
                + "f.-> Correo.\n"
                + "g.-> Titulo.\n"
                + "h.-> Secretaría.\n"
                + "Su Elección Es:").toLowerCase().charAt(0);

        if (Op < 'a' || Op > 'h') {
            JOptionPane.showMessageDialog(null, "La Opción Ingresada NO Existe!!!\n"
                    + "Favor Vuelva a Intentarlo!!!");
            Op = MenuModificarGabinete();//Llamado Recursivo
        }

        return Op;
    }
    
    public Gobierno[] Predefinido(Gobierno A[]){
        A = new Gobierno [7];
        //public Diputado(char[] Identidad, String Nombre, short Edad, char Genero, char[] Celular, String Correo, String Cargo, String Partido) {//Constructor Full
        A[0] = new Diputado("0801-1973-12345".toCharArray(),"Luis Rolando Redondo Guifarro",(short)49,'M',"32322323".toCharArray(),"lredondo@unitec.edu", "Presidente", "PSH");
        A[1] = new Diputado("0801-1948-12345".toCharArray(),"Mauricio Villeda Bermudez",(short)74,'M',"87877878".toCharArray(),"mvilleda@unitec.edu", "N/A", "Liberal");
        A[2] = new Diputado("0801-1972-12345".toCharArray(),"Rasel Antonio Tomé Flores",(short)49,'M',"91911919".toCharArray(),"rtome@unitec.edu", "III Vicepresidente", "Libre"); 
        A[3] = new Diputado("0801-1991-12345".toCharArray(),"Iroshka Lindaly Elvir",(short)30,'F',"73733737".toCharArray(),"i.elvir@unitec.edu", "V Vicepresidente", "PSH"); 
        A[4] = new Diputado("0101-1985-12345".toCharArray(),"Kritza Pérez",(short)37,'F',"33410976".toCharArray(),"kperez@unitec.edu", "II Vicepresidente A", "Liberal"); 
        A[5] = new Gabinete("0801-1960-10101".toCharArray(),"Jose Manuel Matheu Amaya",(short)61,'M',"88440560".toCharArray(),"mmatheu@unitec.edu","Medico Cirujano","Secretaria de Salud");
        A[6] = new Gabinete("0801-1960-12345".toCharArray(),"Ramón Sabillón Pineda",(short)61,'M',"99434145".toCharArray(),"rsabillon@unitec.edu","Comisionado de Policia","Secretaria de Seguridad");
        return A;
    }
    
    public void Guardar(Gobierno A[],char Op)throws IOException{
        String Nombre = "Gobierno.xls";
        switch (Op) {
            case 'a':
                Nombre = "GabineteDiaArbol.xls";
                break;
            case 'b':
                Nombre = "DiputadoDiaArbol.xls";
                break;
            case 'c':
                Nombre = "CSJ.xls";
                break;
        }
        FileWriter Archivo = new FileWriter(Nombre);
        PrintWriter Linea = new PrintWriter(Archivo);
        
        switch (Op) {
            case 'a':
                Linea.print("Identidad\tNombre del Miembro\tEdad\tGénero\tCelular\tCorreo\tTítulo\tSecretaría de Estado\n");                
                break;
            case 'b':
                Linea.print("Identidad\tNombre del Diputado\tEdad\tGénero\tCelular\tCorreo\tCargo\tPartido Político\n");
                break;
            case 'c':
                Linea.print("Identidad\tNombre del Magistrado\tEdad\tGénero\tCelular\tCorreo\tEspecialidad\n");                
                break;
        }
        for (int i = 0; i < A.length; i++) {
            if(A[i] instanceof Gabinete && Op=='a' ||
               A[i] instanceof Diputado && Op=='b'){
                A[i].Guardar(Linea);
            }
        }
        Archivo.close();
    }
    
    public Gobierno[] Importar(char Op)throws IOException{
        String Nombre = "Gobierno.xls";
        switch (Op) {
            case 'a':
                Nombre = "GabineteDiaArbol.xls";
                break;
            case 'b':
                Nombre = "DiputadoDiaArbol.xls";
                break;
            case 'c':
                Nombre = "CSJ.xls";
                break;
        }
        FileReader Archivo = new FileReader(Nombre);
        Scanner Linea = new Scanner(Archivo);
        Gobierno A[] = new Gobierno[0];
        
        for(Iterator I = Linea; I.hasNext(); ){
            System.out.println(Linea.next());
        }
        
        return A;
    }

    public Gobierno[] Eliminar(int Pos, Gobierno A[]){
        Gobierno tmpA[];//Declaración de Arreglos
        
        //1
        tmpA = new Gobierno[A.length-1];
          
        for (int i = 0; i < A.length; i++) {//2
            if(i<Pos){
                tmpA[i] = A[i];
            }else if(i>Pos){
                tmpA[i-1] = A[i];
            }
        }
        
        //3
        return tmpA;
    }
    
    public Gobierno[] Registrar(int Cant, Gobierno A[], char Op){
        Gobierno tmpA[];
        
        //1
        tmpA = new Gobierno[A.length + Cant];
        
        for (int i = 0; i < tmpA.length; i++) {
            //2
            if( i<A.length ){
                tmpA[i] = A[i];
            }else{//3
                switch (Op) {
                    case 'a'://Gabinete
                        tmpA[i] = new Gabinete();
                        break;
                    case 'b'://Diputados
                        tmpA[i] = new Diputado();//Llamado al Constructor Vacío
                        break;
                    case 'c'://Magistrado
                        
                        break;
                }
                tmpA[i].Leer(i);
            }
        }
        
        //4
        return tmpA;
    }
    
    public Gobierno[] Registrar(Gobierno A[], Gobierno Var){
        Gobierno tmpA[];
        
        //1
        tmpA = new Gobierno[A.length + 1];
        
        for (int i = 0; i < tmpA.length; i++) {
            //2
            if( i<A.length ){
                tmpA[i] = A[i];
            }else{//3
                tmpA[i] = Var;
            }
         }
        //4
        return tmpA;
    }
    
    public Diputado Modificar(Diputado P) {//Abel
        char OpMod;
        
        OpMod = MenuModificarDiputado();
        switch (OpMod) {
            case 'a'://Johat
                P.setIdentidad(JOptionPane.showInputDialog("Ingrese la Nueva Indentidad de " + P.getNombre() + ": ").toCharArray());
                Consulta = "update Diputado_ADe set Identidad = '"+String.valueOf(P.getIdentidad())+"' where Nombre = '"+P.getNombre()+"';";
                break;
            case 'b'://Jorge
                P.setNombre(JOptionPane.showInputDialog("Ingrese el Nuevo Nombre de " + P.getNombre() + ": "));
                Consulta = "update Diputado_ADe set Nombre = '"+P.getNombre()+"' where Identidad = '"+String.valueOf(P.getIdentidad())+"';";
                break;
            case 'c'://Katherine
                P.setEdad(Short.parseShort(JOptionPane.showInputDialog("Ingrese la Nueva Edad de " + P.getNombre() + ": ")));
                Consulta = "update Diputado_ADe set Edad = '"+String.valueOf(P.getIdentidad())+"' where Identidad = '"+String.valueOf(P.getIdentidad())+"';";
                break;
            case 'd'://Osman
                P.setGenero(P.ValidarGen(P.getNombre()));
                break;
            case 'e'://Ricardo
                P.setCelular(JOptionPane.showInputDialog("Ingrese el Nuevo Celular de " + P.getNombre() + ": ").toCharArray());
                 Consulta = "update Diputado_ADe set Celular = '"+String.valueOf(P.getCelular())+"' where Identidad = '"+String.valueOf(P.getIdentidad())+"';";
                break;
            case 'f'://Rommell
                P.setCorreo(P.ValidarCorreo(P.getNombre()));
                break;
            case 'g'://Jonathan
                P.setCargo(JOptionPane.showInputDialog("Ingrese el Nuevo Cargo de " + P.getNombre() + ": "));
                Consulta = "update Diputado_ADe set Cargo = '"+P.getCargo()+"' where Identidad = '"+String.valueOf(P.getIdentidad())+"';";
                break;
            case 'h'://Allan o Jery
                P.setPartido(JOptionPane.showInputDialog("Ingrese el Nuevo Partido de " + P.getNombre() + ": "));
                break;
        }
        return P;
    }
     
    public Gabinete Modificar(Gabinete P) {//David
        char OpMod;

        OpMod = MenuModificarGabinete();
        Con = Conecta();
        if( Con!=null ){
            try {
                Pila = Con.createStatement();
                switch (OpMod) {
                    case 'a':
                        P.setIdentidad(JOptionPane.showInputDialog("Ingrese el Nuevo Número de Identidad de " + P.getNombre()+ ": ").toCharArray());
                        Consulta = "update Gabinete_CRPD set Identidad = '"+String.valueOf(P.getIdentidad())+"' where Nombre = '"+P.getNombre()+"';";
                        //JOptionPane.showMessageDialog(null, "Modificados: "+Pila.executeUpdate(Consulta));
                        break;
                    case 'b'://Abel Luque
                        P.setNombre(JOptionPane.showInputDialog("Ingrese el Nuevo Nombre de " + P.getNombre() + ": "));
                        Consulta = "update Gabinete_CRPD set Nombre = '"+ P.getNombre() +"' where Identidad = '"+String.valueOf(P.getIdentidad())+"';";
                        break;
                    case 'c'://Alessandra
                      P.setEdad(Short.parseShort(JOptionPane.showInputDialog("Ingrese la Nueva Edad de " + P.getNombre()+ ": ")));
                      Consulta = "update Gabinete_CRPD set Edad = '"+ P.getEdad() + "' where Identidad = '"+String.valueOf(P.getIdentidad())+"';";
                      break;
                    case 'd'://Ariel
                        P.setGenero( P.ValidarGen( P.getNombre() ) ) ;
                        Consulta = "update Gabinete_CRPD set Genero = '"+String.valueOf(P.getGenero())+"' where Identidad = '"+String.valueOf(P.getIdentidad())+"';";
                      break;
                    case 'e'://Bryan
                      P.setCelular(JOptionPane.showInputDialog("Ingrese el Nuevo Celular de " + P.getNombre()+ ":").toCharArray());
                      Consulta = "update Gabinete_CRPD set Celular = '"+String.valueOf(P.getCelular())+"' where Identidad = '"+String.valueOf(P.getIdentidad())+"';";
                      break;
                    case 'f'://Cesar
                      P.setCorreo(P.ValidarCorreo(P.getNombre()));
                      Consulta = "update Gabinete_CRPD set Correo = '"+String.valueOf(P.getCorreo())+"' where Identidad = '"+String.valueOf(P.getIdentidad())+"';";
                      break;
                    case 'g'://Chris
                        P.setTitulo(JOptionPane.showInputDialog("Ingrese el Nuevo Titulo de " + P.getNombre()+ ":"));
                        Consulta = "update Gabinete_CRPD set Titulo = '"+String.valueOf(P.getTitulo())+"' where Identidad = '"+String.valueOf(P.getIdentidad())+"';";
                      break;
                    case 'h'://David
                      P.setSecretaria(JOptionPane.showInputDialog("Ingrese la Nueva Secretaria de " + P.getNombre()+ ":"));
                      break;
                }
                JOptionPane.showMessageDialog(null, "Modificado: "+Pila.executeUpdate(Consulta));
                Desconecta(Con);
            }catch(java.sql.SQLException e){
                        
            }
        }
    return P;
  }
        
    public void ImprimirCLI(Gobierno A[], char Op){
         switch (Op) {
            case 'a':
                System.out.print("+--------+---------------+--------------------------------------------------+----+------+----------------+--------------------++-------------------------+-------------------------+\n"
                                +"|Posición|   Identidad   |               Nombre del Diputado                |Edad|Género|     Celular    |       Correo       ||          Título         |         Secretaría      |\n"
                                +"+--------+---------------+--------------------------------------------------+----+------+----------------+--------------------++-------------------------+-------------------------+\n");                           
                break;
            case 'b':
                System.out.print("+--------+---------------+--------------------------------------------------+----+------+----------------+--------------------++--------------------+---------------+\n"
                                +"|Posición|   Identidad   |               Nombre del Diputado                |Edad|Género|     Celular    |       Correo       ||       Cargo:       |    Partido    |\n"
                                +"+--------+---------------+--------------------------------------------------+----+------+----------------+--------------------++--------------------+---------------+\n");                
                break;
            case 'c':
                
                break;
        }
        for (int i = 0; i < A.length; i++) {
            if(A[i] instanceof Gabinete && Op=='a' ||
               A[i] instanceof Diputado && Op=='b'
                    ){
                A[i].ImprimirCLI(i);
                A[i].Borde();
            }
        }
    }
    
    public Connection Conecta(){
        Connection Con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Con = DriverManager.getConnection("jdbc:mysql://remotemysql.com/pZd68gaOVF","pZd68gaOVF","tscV6wwngW");
            return Con;
        } catch (java.lang.ClassNotFoundException e) {
            return Con;
        } catch (SQLException e) {
            return Con;
        }
    }
    
    public boolean Desconecta(Connection Con){
        try {
            Con.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}