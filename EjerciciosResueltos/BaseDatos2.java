package laboratorio08;

import java.awt.*; import
java.awt.event.*; import
javax.swing.*; import java.sql.*;
import java.util.*; class Ventana
extends JFrame
{
JLabel LblId;
JLabel LblNombre;
JLabel LblDescripcion;
JTextField TxtId;   
JTextField TxtNombre;
JTextField TxtDescripcion;
JButton BtnPrimero;
JButton BtnSiguiente;
JButton BtnAnterior;
JButton BtnUltimo; ResultSet
resultado;
public Ventana(String titulo)
{
super(titulo);
// setSize(150,150);
LblId = new JLabel("Id Categoria");
LblNombre = new JLabel("Nombre");
LblDescripcion = new JLabel("Descripcion");
TxtId = new JTextField();
TxtNombre = new JTextField();
TxtDescripcion = new JTextField();
BtnPrimero =new JButton("Primero"); BtnPrimero.addActionListener(new
EventoBoton(this));
BtnAnterior =new JButton("Anterior");
BtnAnterior.addActionListener(new EventoBoton(this));
BtnSiguiente =new JButton("Siguiente");
BtnSiguiente.addActionListener(new EventoBoton(this));
BtnUltimo =new JButton("Ultimo");
BtnUltimo.addActionListener(new EventoBoton(this));
JPanel Panel1 = new JPanel();
JPanel Panel11 = new JPanel();
Panel11.setLayout(new BoxLayout(Panel11,BoxLayout.Y_AXIS));
Panel11.add(Box.createRigidArea(new Dimension(10,10)));
Panel11.add(LblId);
Panel11.add(Box.createRigidArea(new Dimension(10,10)));
Panel11.add(LblNombre);
Panel11.add(Box.createRigidArea(new Dimension(10,10)));
Panel11.add(LblDescripcion);
Panel11.add(Box.createRigidArea(new Dimension(10,20)));
JPanel Panel12 = new JPanel();
Panel12.setLayout(new BoxLayout(Panel12,BoxLayout.Y_AXIS));
Panel12.add(Box.createRigidArea(new Dimension(10,10)));
Panel12.add(TxtId);
Panel12.add(Box.createRigidArea(new Dimension(10,10)));
Panel12.add(TxtNombre);
Panel12.add(Box.createRigidArea(new Dimension(10,10)));
Panel12.add(TxtDescripcion);
Panel12.add(Box.createRigidArea(new Dimension(10,20))); Panel1.setLayout(new
BoxLayout(Panel1,BoxLayout.X_AXIS));
Panel1.add(Box.createRigidArea(new Dimension(5,10)));
Panel1.add(Panel11);
Panel1.add(Box.createRigidArea(new Dimension(5,10)));
//PanelDatos.add(areaResultados);
Panel1.add(Panel12);
JPanel Panel2 = new JPanel();
//SubPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20,20));
//SubPanel.setLayout(new BorderLayout());
Panel2.setLayout(new BoxLayout(Panel2,BoxLayout.X_AXIS));
//SubPanel.add(PanelDatos);
Panel2.add(Box.createRigidArea(new Dimension(10,10)));
Panel2.add(BtnPrimero);
//SubPanel.add(scrollPanel,"South");
Panel2.add(Box.createRigidArea(new Dimension(10,10)));
Panel2.add(BtnAnterior);
Panel2.add(Box.createRigidArea(new Dimension(10,10)));
Panel2.add(BtnSiguiente);
Panel2.add(Box.createRigidArea(new Dimension(10,10)));
Panel2.add(BtnUltimo);
Panel2.add(Box.createRigidArea(new Dimension(10,10)));
Panel2.setBackground( Color.orange );
JPanel Panel = new JPanel();
//SubPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20,20));
//SubPanel.setLayout(new BorderLayout());
Panel.setLayout(new BoxLayout(Panel,BoxLayout.Y_AXIS));
//SubPanel.add(PanelDatos);
Panel.add(Panel1);
Panel.add(Box.createRigidArea(new Dimension(0,20)));
//SubPanel.add(scrollPanel,"South");
Panel.add(Panel2);
//Panel.setBackground( Color.orange );
Panel.setBackground( new Color(255,0,0) );
getContentPane().add(Panel,BorderLayout.CENTER); addWindowListener(new
EventosVentana(this));
Conexion();
}
private void Conexion()
{ try
{
//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Class.forName("com.mysql.jdbc.Driver").newInstance();
Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmpresaMSQL?useUnicode=true&characterEncoding=UTF-8",
    "n4nd0",
    "chacha");

//Connection conexion =
DriverManager.getConnection("jdbc:odbc:Empresa");
Statement sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); boolean tieneResultados=sentencia.execute("Select * from Categorias");
if(tieneResultados)
{
resultado = sentencia.getResultSet();
if (resultado != null)
{
mostrarResultados(resultado);
} else {
//areaResultados.setText("");
}
//conexion.close();
}
}
catch(ClassNotFoundException e)
{
System.out.println("Controlador no Encontrado: "+e);
}
catch(Exception e)
{
System.out.println("Error de Conexión "+e);
}
}
public void mostrarResultados(ResultSet r) throws SQLException
{
ResultSetMetaData res = r.getMetaData(); int
numCampos = res.getColumnCount();
String texto="";
}
public void IrPrimero()
        {
System.out.println("Primero 2222"); try {
String var; resultado.first();
var=resultado.getString("IdCategoria");
TxtId.setText(var);
var=resultado.getString("Nombre");
TxtNombre.setText(var);
var=resultado.getString("Descripcion");
TxtDescripcion.setText(var);
}
catch(Exception e)
{
System.out.println("Error de Conexión "+e);
}
}
public void IrAnterior()
{ try {
String var; if(!resultado.previous())
resultado.first();
var=resultado.getString("IdCategoria");
TxtId.setText(var);
var=resultado.getString("Nombre");
TxtNombre.setText(var); var=resultado.getString("Descripcion");
TxtDescripcion.setText(var);
}
catch(Exception e)
{
System.out.println("Error de Conexión "+e);
}
}
public void IrSiguiente()
{ try {
String var; if(!resultado.next()) resultado.last();
var=resultado.getString("IdCategoria");
TxtId.setText(var);
var=resultado.getString("Nombre");
TxtNombre.setText(var);
var=resultado.getString("Descripcion");
TxtDescripcion.setText(var);
}
catch(Exception e)
{
System.out.println("Error de Conexión "+e);
}
}
public void IrUltimo()
{ try {
String var; resultado.last();
var=resultado.getString("IdCategoria");
TxtId.setText(var);
var=resultado.getString("Nombre");
TxtNombre.setText(var);
var=resultado.getString("Descripcion");
TxtDescripcion.setText(var);
}
catch(Exception e)
{
System.out.println("Error de Conexión "+e);
}
}
}
class EventoBoton implements ActionListener
{
Ventana fuente; public EventoBoton(Ventana
pWnd)
{
fuente=pWnd;
}
public void actionPerformed(ActionEvent evento)
{
String texto = evento.getActionCommand(); if(texto=="Primero")
{
System.out.println("Primero");
fuente.IrPrimero();
}
if(texto=="Anterior")
{
fuente.IrAnterior();
}
if(texto=="Siguiente")
{
fuente.IrSiguiente();
}
if(texto=="Ultimo")
{
fuente.IrUltimo();
}
System.out.println("Buenas Tardes (Click)");
if(evento.getActionCommand().equals("Deshabilitado"))
{
}
if(evento.getActionCommand().equals("Habilitado"))
{
}
}
}
class EventosVentana extends WindowAdapter
{
private Ventana fuente;
public EventosVentana(Ventana pWnd)
{
this.fuente=pWnd;
}
public void windowClosed(WindowEvent evento)
{
System.out.println("Ventana cerrada");
System.exit(0);
}
public void windowIconified(WindowEvent evento)
{
System.out.println("Ventana Minimizada");
}
public void windowDeiconified(WindowEvent evento)
{
System.out.println("Ventana No Minimizada");
}
public void windowClosing(WindowEvent evento)
{
fuente.dispose();
}
}
public class BaseDatos2
{
public static void main(String arg[])
{
Ventana miVentana = new Ventana("TextBox");
miVentana.pack(); miVentana.setVisible(true);
}
}

