package congresopoo;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GUI extends javax.swing.JFrame {
    private ButtonGroup Menu = new ButtonGroup();
    private ButtonGroup SubMenu = new ButtonGroup();
    private DefaultTableModel Tabla = new DefaultTableModel();
    private Gobierno A[] = new Gobierno[0];
    private Gobierno Var;  
    private Programa P = new Programa();
    private char OpM, OpSm=' ', Op;
    private int Cant, Pos;
    private ResultSet Resultado;
    
    public GUI() {
        initComponents();
        
        Menu.add(jRB_Mostrar);
        Menu.add(jRB_Guardar);
        Menu.add(jRB_Registrar);
        Menu.add(jRB_Modificar);
        Menu.add(jRB_Eliminar);
        
        SubMenu.add(jRB_Gabinete);
        SubMenu.add(jRB_Diputados);
        SubMenu.add(jRB_Magistrados);
        
        Habilitar(false);
        

        Op = MenuCargar();
        switch (Op) {
            case '1':
                A = P.Predefinido(A);
                //LimpiarTablas();
                //InsertarBD(A.length);
                break;
            case '2':
                Seleccionar();                
                break;
            case '3':
                try {
                    P.Importar('a');
                } catch (java.io.IOException e) {
                    System.out.println("NO Fue Posible Importar desde el Archivo!!!");
                }
                break;
        }
        
    }
    
    char MenuCargar(){
        Op = JOptionPane.showInputDialog("Carga de Información:\n"
                + "1.-> Desde un Predefinido.\n"
                + "2.-> Desde la Base de Datos Online.\n"
                + "3.-> Desde Archivos de Excel.\n"
                + "Su Elección es: ").charAt(0);
        if(Op<'1' || Op>'3'){
            JOptionPane.showMessageDialog(null, "La Opción Ingresada es Incorrecta!!!\nFavor vuelva a Intentarlo!!!");
            Op = MenuCargar();
        }
        return Op;
    }
    /*
    Created!
    You have successfully created a new database. The details are below.
    Username: pZd68gaOVF
    Database name: pZd68gaOVF
    Password: tscV6wwngW
    Server: remotemysql.com
    Port: 3306
    These are the username and password to log in to your database and phpMyAdmin
    */
    
    /*
    dev.mysql.com/downloads/file/?id=511553
    remotemysql.com

    show databases;
    use pZd68gaOVF;
    create table Gabinete_CRPD(Identidad varchar(15), Nombre varchar(50),Edad smallint, Genero varchar(1), Celular varchar(16), Correo varchar(20), Titulo varchar(25), Secretaria varchar(25));
    insert into Gabinete_CRPD(Identidad, Nombre, Edad, Genero, Celular, Correo, Titulo, Secretaria) values ('0801-1960-10101','Jose Manuel Matheu Amaya',61,'M','88440560','mmatheu@unitec.edu','Medico Cirujano','Secretaria de Salud');
    select *from Gabinete_CRPD;
    
    insert into Diputado_ADe(Identidad,Nombre,Edad,Genero,Celular,Correo,Cargo,Partido) values ('0801-1989-00212','Damaris Flores',33,'F','98959493','dflores@gmail.com','Suplente','PSH');
    select *from Diputado_ADe;
    
    create table Login_CRPD(usuario varchar(20), clave varchar(20),rol smallint);
    insert into Login_CRPD(usuario, clave, rol)values('kapl','jfpl',2);
    select * from Login_CRPD;
    */
    
    
    
    public void InsertarBD(int Cant){
        P.Con = P.Conecta();
        if( P.Con!=null ){
            try {
                P.Pila = P.Con.createStatement();
                for (int i = A.length - Cant ; i < A.length; i++) {
                    if( (OpSm=='a' && A[i] instanceof Gabinete) || (OpSm==' ' && A[i] instanceof Gabinete) ){
                        P.Consulta = "insert into Gabinete_CRPD(Identidad, Nombre, Edad, Genero, Celular, Correo, Titulo, Secretaria) values ('"
                                +String.valueOf( ((Gabinete)A[i]).getIdentidad() )+"','"
                                +((Gabinete)A[i]).getNombre()+"',"
                                +((Gabinete)A[i]).getEdad()+",'"
                                +((Gabinete)A[i]).getGenero()+"','"
                                +String.valueOf( ((Gabinete)A[i]).getCelular() )+"','"
                                +((Gabinete)A[i]).getCorreo()+"','"
                                +((Gabinete)A[i]).getTitulo()+"','"
                                +((Gabinete)A[i]).getSecretaria()+"');";
                        if( !P.Pila.execute(P.Consulta) ){
                            JOptionPane.showMessageDialog(null, "Realizado!!!");
                        }
                    }else if( (OpSm=='b' && A[i] instanceof Diputado) || (OpSm==' ' && A[i] instanceof Diputado) ){
                        P.Consulta = "insert into Diputado_ADe(Identidad,Nombre,Edad,Genero,Celular,Correo,Cargo,Partido) values ('"
                                +String.valueOf(  ((Diputado)A[i]).getIdentidad()  )+"','"
                                +((Diputado)A[i]).getNombre()+"',"
                                +((Diputado)A[i]).getEdad()+",'"
                                +((Diputado)A[i]).getGenero()+"','"
                                +String.valueOf(  ((Diputado)A[i]).getCelular()  )+"','"
                                +((Diputado)A[i]).getCorreo()+"','"
                                +((Diputado)A[i]).getCargo()+"','"
                                +((Diputado)A[i]).getPartido()+"');";
                        P.Pila.execute(P.Consulta);
                    }
                }
                P.Desconecta(P.Con);
                this.jLbl_Titulo.setText("Inserción Exitosa!!!");
            } catch (SQLException e) {
                this.jLbl_Titulo.setText("NO fue posible realizar la inserción en la BD!!!");
            }            
        }
    }
    
    public void Seleccionar(){
        P.Con = P.Conecta();
        if( P.Con != null ){
            try {
                P.Pila = P.Con.createStatement();
                if (OpSm=='a' || OpSm==' ') {
                    P.Consulta = "select * from Gabinete_CRPD;";
                    Resultado = P.Pila.executeQuery(P.Consulta);
                    for (; Resultado.next() ;) {
                        //public Gabinete(char[] Identidad, String Nombre, short Edad, char Genero, char[] Celular, String Correo, String Titulo, String Secretaria) {
                        Var = new Gabinete(Resultado.getString("Identidad").toCharArray(),
                                           Resultado.getString("Nombre"),
                                           Resultado.getShort("Edad"),
                                           Resultado.getString("Genero").charAt(0),
                                           Resultado.getString("Celular").toCharArray(),
                                           Resultado.getString("Correo"),
                                           Resultado.getString("Titulo"),
                                           Resultado.getString("Secretaria")
                        );
                        A = P.Registrar(A, Var);
                    }                        
                } 
                if (OpSm=='b' || OpSm==' '){
                    P.Consulta = "select * from Diputado_ADe;";
                    Resultado = P.Pila.executeQuery(P.Consulta);
                    for (; Resultado.next() ;) {
                        //public Diputado(char[] Identidad, String Nombre, short Edad, char Genero, char[] Celular, String Correo, String Cargo, String Partido) 
                        Var = new Diputado(Resultado.getString("Identidad").toCharArray(),
                                           Resultado.getString("Nombre"),
                                           Resultado.getShort("Edad"),
                                           Resultado.getString("Genero").charAt(0),
                                           Resultado.getString("Celular").toCharArray(),
                                           Resultado.getString("Correo"),
                                           Resultado.getString("Cargo"),
                                           Resultado.getString("Partido")
                        );
                        A = P.Registrar(A, Var);
                    }  
                }              
                this.jLbl_Titulo.setText("Selección Exitosa!!!");
                P.Desconecta(P.Con);
            } catch (SQLException e) {
                this.jLbl_Titulo.setText("NO fue posible realizar la selección desde la BD!!!");
            }
        }
    }
    
    public void LimpiarTablas(){
        P.Con = P.Conecta();
        try {
            P.Pila = P.Con.createStatement();
            P.Consulta = "delete from Gabinete_CRPD;";
            P.Pila.execute(P.Consulta);
            P.Consulta = "delete from Diputado_ADe;";
            P.Pila.execute(P.Consulta);
            System.out.println("Tablas Limpias!!!");
        } catch (SQLException e) {
            System.out.println("NO Fue posible Limpiar las Tablas de la BD!!!");
        }
    }
           
    public void Habilitar(boolean Habilita){
        this.jLbl_Titulo.setText("Personal de Gobierno");
        this.jRB_Gabinete.setEnabled(Habilita);
        this.jRB_Diputados.setEnabled(Habilita);
        this.jRB_Magistrados.setEnabled(Habilita);
        if( (Habilita && (OpSm>='a'&& OpSm<='c')) || !Habilita){
            this.jBtn_Aceptar.setEnabled(Habilita);            
        }
    }
    
    public String[] EtiquetasGabinete(){
        String E[] = {"Posición","Identidad","Nombre del Funcionario","Edad","Género","Celular","Correo","Título","Secretaría"};
        return E;
    }
    
    public String[] EtiquetasDiputados(){
        String E[] = {"Posición","Identidad","Nombre del Diputado","Edad","Género","Celular","Correo","Cargo","Partido"};
        return E;
    }

    public String[] EtiquetasMagistrados(){
        String E[] = {"Posición","Identidad","Nombre del Diputado","Edad","Género","Celular","Correo","Especialidad"};
        return E;
    }
    
    public void Mostrar(Gobierno A[], char Op){
        String Datos[][] = {};
        String Etiquetas[] = {};
        
        switch (Op) {
            case 'a':
                Etiquetas = EtiquetasGabinete();
                break;
            case 'b':
                Etiquetas = EtiquetasDiputados();
                break;
            case 'c':
                Etiquetas = EtiquetasMagistrados();
                break;
        }
        
        Tabla = new DefaultTableModel(Datos,Etiquetas);
        this.jTbl_Mostrar.setModel(Tabla);
        
        for (int i = 0; i < A.length; i++) {
            if(Op=='a' && A[i] instanceof Gabinete){
                Object Linea[] = {(i+1),
                    String.valueOf(((Gabinete)A[i]).getIdentidad()),
                    ((Gabinete)A[i]).getNombre(),
                    ((Gabinete)A[i]).getEdad(),
                    ((Gabinete)A[i]).getGenero(),
                    String.valueOf(((Gabinete)A[i]).getCelular()),
                    ((Gabinete)A[i]).getCorreo(),
                    ((Gabinete)A[i]).getTitulo(),
                    ((Gabinete)A[i]).getSecretaria()
                };
                Tabla.addRow(Linea);
            }
            else if(Op=='b' && A[i] instanceof Diputado){
                Object Linea[] = {(i+1),
                    String.valueOf( ((Diputado)A[i]).getIdentidad() ),
                    ((Diputado)A[i]).getNombre(),
                    ((Diputado)A[i]).getEdad(),
                    ((Diputado)A[i]).getGenero(),
                    String.valueOf( ((Diputado)A[i]).getCelular() ),
                    ((Diputado)A[i]).getCorreo(),
                    ((Diputado)A[i]).getCargo(),
                    ((Diputado)A[i]).getPartido()
                };
                Tabla.addRow(Linea);
            }
            else if(Op=='c' && A[i] instanceof CSJ){
                Object Linea[] = {(i+1),
                    String.valueOf( ((CSJ)A[i]).getIdentidad() ),
                    ((CSJ)A[i]).getNombre(),
                    ((CSJ)A[i]).getEdad(),
                    ((CSJ)A[i]).getGenero(),
                    String.valueOf( ((CSJ)A[i]).getCelular() ),
                    ((CSJ)A[i]).getCorreo(),
                    ((CSJ)A[i]).getEspecialidad(),
                };
                Tabla.addRow(Linea);
            }
            
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTbl_Mostrar = new javax.swing.JTable();
        jLbl_Titulo = new javax.swing.JLabel();
        jRB_Mostrar = new javax.swing.JRadioButton();
        jRB_Guardar = new javax.swing.JRadioButton();
        jRB_Registrar = new javax.swing.JRadioButton();
        jRB_Modificar = new javax.swing.JRadioButton();
        jRB_Eliminar = new javax.swing.JRadioButton();
        jRB_Gabinete = new javax.swing.JRadioButton();
        jRB_Diputados = new javax.swing.JRadioButton();
        jRB_Magistrados = new javax.swing.JRadioButton();
        jBtn_Aceptar = new javax.swing.JButton();
        jLbl_Menu = new javax.swing.JLabel();
        jLbl_SubMenu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTbl_Mostrar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTbl_Mostrar);

        jLbl_Titulo.setFont(new java.awt.Font("Noto Sans", 1, 24)); // NOI18N
        jLbl_Titulo.setForeground(new java.awt.Color(153, 0, 51));
        jLbl_Titulo.setText("Personal de Gobierno");

        jRB_Mostrar.setText("Mostrar");
        jRB_Mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRB_MostrarActionPerformed(evt);
            }
        });

        jRB_Guardar.setText("Guardar");
        jRB_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRB_GuardarActionPerformed(evt);
            }
        });

        jRB_Registrar.setText("Registrar");
        jRB_Registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRB_RegistrarActionPerformed(evt);
            }
        });

        jRB_Modificar.setText("Modificar");
        jRB_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRB_ModificarActionPerformed(evt);
            }
        });

        jRB_Eliminar.setText("Eliminar");
        jRB_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRB_EliminarActionPerformed(evt);
            }
        });

        jRB_Gabinete.setText("Gabinete");
        jRB_Gabinete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRB_GabineteActionPerformed(evt);
            }
        });

        jRB_Diputados.setText("Diputados");
        jRB_Diputados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRB_DiputadosActionPerformed(evt);
            }
        });

        jRB_Magistrados.setText("Magistrados");
        jRB_Magistrados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRB_MagistradosActionPerformed(evt);
            }
        });

        jBtn_Aceptar.setText("Aceptar");
        jBtn_Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn_AceptarActionPerformed(evt);
            }
        });

        jLbl_Menu.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLbl_Menu.setText("Menú Principal:");

        jLbl_SubMenu.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLbl_SubMenu.setText("SubMenú:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(489, Short.MAX_VALUE)
                .addComponent(jLbl_Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLbl_Menu)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRB_Mostrar)
                            .addComponent(jRB_Guardar)
                            .addComponent(jRB_Registrar)
                            .addComponent(jRB_Modificar)
                            .addComponent(jRB_Eliminar))
                        .addGap(82, 82, 82)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRB_Gabinete)
                            .addComponent(jRB_Diputados)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jRB_Magistrados)
                                .addGap(83, 83, 83)
                                .addComponent(jBtn_Aceptar))
                            .addComponent(jLbl_SubMenu))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLbl_Titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLbl_Menu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRB_Mostrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRB_Guardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRB_Registrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRB_Modificar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLbl_SubMenu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRB_Gabinete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRB_Diputados)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRB_Magistrados)
                            .addComponent(jBtn_Aceptar))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRB_Eliminar)
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRB_MostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRB_MostrarActionPerformed
        OpM = 'A';
        Habilitar(true);
    }//GEN-LAST:event_jRB_MostrarActionPerformed

    private void jRB_GabineteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRB_GabineteActionPerformed
        OpSm = 'a';
        Habilitar(true);
    }//GEN-LAST:event_jRB_GabineteActionPerformed

    private void jRB_DiputadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRB_DiputadosActionPerformed
        OpSm = 'b';
        Habilitar(true);
    }//GEN-LAST:event_jRB_DiputadosActionPerformed

    private void jRB_MagistradosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRB_MagistradosActionPerformed
        OpSm = 'c';
        Habilitar(true);
    }//GEN-LAST:event_jRB_MagistradosActionPerformed

    private void jRB_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRB_GuardarActionPerformed
        OpM = 'B';
        Habilitar(true);
    }//GEN-LAST:event_jRB_GuardarActionPerformed

    private void jRB_RegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRB_RegistrarActionPerformed
        OpM = 'C';
        Habilitar(true);
    }//GEN-LAST:event_jRB_RegistrarActionPerformed

    private void jRB_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRB_ModificarActionPerformed
        OpM = 'D';
        Habilitar(true);
    }//GEN-LAST:event_jRB_ModificarActionPerformed

    private void jRB_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRB_EliminarActionPerformed
        OpM = 'E';
        Habilitar(true);
    }//GEN-LAST:event_jRB_EliminarActionPerformed

    private void jBtn_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_AceptarActionPerformed
        switch (OpM) {
            case 'A':
                //P.ImprimirCLI( A, OpSm );
                Mostrar(A,OpSm);                
                break;
            case 'B':
                try{
                    P.Guardar(A, OpSm);                    
                    this.jLbl_Titulo.setText("El Exportar a Excel ha sido Exitoso!!!");
                }catch(java.io.IOException e){
                    JOptionPane.showMessageDialog(null, "NO fue posible exportar a Excel!!!");
                }
                break;
                case 'C':
                    Cant = A[0].ValidarInt("Ingrese la Cantidad de Miembros de Gobierno a Registrar:");
                    A = P.Registrar( Cant, A, OpSm );
                    InsertarBD(Cant);
                    this.jLbl_Titulo.setText("Registro Exitoso!!!");
                    break;
                case 'D':
                   // P.ImprimirCLI( A, OpSm );
                    Mostrar(A,OpSm); 
                    P.Leer = JOptionPane.showInputDialog("Ingrese la Posición a Modificar: ");  
                    Pos = Integer.parseInt(P.Leer)-1;
                    if(A[Pos] instanceof Gabinete){
                        A[Pos] = P.Modificar( (Gabinete)A[Pos] );
                    }else if(A[Pos] instanceof Diputado){
                        A[Pos] = P.Modificar( (Diputado)A[Pos] );
                    }
                    this.jLbl_Titulo.setText("Modificación Exitosa!!!");
                    break;
                case 'E':
                    //P.ImprimirCLI( A, OpSm );
                    Mostrar(A,OpSm);
                    Pos = A[0].ValidarInt("Ingrese la Posición a Eliminar: ")-1;
                    A = P.Eliminar(Pos,A);
                    this.jLbl_Titulo.setText("La Eliminación ha sido Exitosa!!!");                    
                    break;
                case 'F'://Agregar RB_Vaciar
                    A = new Gobierno[0];
                    this.jLbl_Titulo.setText("El Vaciado ha sido Exitoso!!!");
                    break;
        }
        //P.ImprimirCLI( A, OpSm );
        Mostrar(A,OpSm); 
    }//GEN-LAST:event_jBtn_AceptarActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtn_Aceptar;
    private javax.swing.JLabel jLbl_Menu;
    private javax.swing.JLabel jLbl_SubMenu;
    private javax.swing.JLabel jLbl_Titulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRB_Diputados;
    private javax.swing.JRadioButton jRB_Eliminar;
    private javax.swing.JRadioButton jRB_Gabinete;
    private javax.swing.JRadioButton jRB_Guardar;
    private javax.swing.JRadioButton jRB_Magistrados;
    private javax.swing.JRadioButton jRB_Modificar;
    private javax.swing.JRadioButton jRB_Mostrar;
    private javax.swing.JRadioButton jRB_Registrar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTbl_Mostrar;
    // End of variables declaration//GEN-END:variables
}
