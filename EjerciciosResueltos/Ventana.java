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
JButton BtnUltimo;
JButton BtnInsertar;
JButton BtnModificar;
JButton BtnEliminar;
JButton BtnActualizar;
ResultSet resultado; Statement
sentencia;
public Ventana(String titulo)
{
super(titulo);
//setSize(150,150);
LblId = new JLabel("Id Categoria");
LblNombre = new JLabel("Nombre");
LblDescripcion = new JLabel("Descripcion");
TxtId = new JTextField();
TxtNombre = new JTextField();
TxtDescripcion = new JTextField();
//
BtnPrimero =new JButton("Primero");
BtnPrimero.addActionListener(new EventoBoton(this));
BtnAnterior =new JButton("Anterior");
BtnAnterior.addActionListener(new EventoBoton(this));
BtnSiguiente =new JButton("Siguiente");
BtnSiguiente.addActionListener(new EventoBoton(this));
BtnUltimo =new JButton("Ultimo");
BtnUltimo.addActionListener(new EventoBoton(this));
//
//
BtnInsertar =new JButton("Insertar");
BtnInsertar.addActionListener(new EventoBoton(this));
BtnModificar =new JButton("Modificar");
BtnModificar.addActionListener(new EventoBoton(this));
BtnEliminar =new JButton("Eliminar");
BtnEliminar.addActionListener(new EventoBoton(this));
BtnActualizar =new JButton("Actualizar");
BtnActualizar.addActionListener(new EventoBoton(this));
//
JPanel Panel1 = new JPanel();
JPanel Panel11 = new JPanel();
Panel11.setLayout(new BoxLayout(Panel11,BoxLayout.Y_AXIS));
Panel11.setAlignmentY(BOTTOM_ALIGNMENT);
Panel11.add(Box.createRigidArea(new Dimension(10,10)));
Panel11.add(LblId);
Panel11.add(Box.createRigidArea(new Dimension(10,10)));
Panel11.add(LblNombre);
Panel11.add(Box.createRigidArea(new Dimension(10,10)));
Panel11.add(LblDescripcion);
Panel11.add(Box.createRigidArea(new Dimension(10,20)));
JPanel Panel12 = new JPanel();
Panel12.setLayout(new BoxLayout(Panel12,BoxLayout.Y_AXIS));
//Panel12.setAlignmentY(Component.TOP_ALIGNMENT);
Panel12.setAlignmentY(BOTTOM_ALIGNMENT);
Panel12.add(Box.createRigidArea(new Dimension(10,10)));
Panel12.add(TxtId);
Panel12.add(Box.createRigidArea(new Dimension(10,10)));
Panel12.add(TxtNombre);
Panel12.add(Box.createRigidArea(new Dimension(10,10)));
Panel12.add(TxtDescripcion);
Panel12.add(Box.createRigidArea(new Dimension(10,10)));
Panel12.add(Box.createVerticalGlue());
Panel1.setLayout(new BoxLayout(Panel1,BoxLayout.X_AXIS));
Panel1.setAlignmentY(TOP_ALIGNMENT);
Panel1.add(Box.createRigidArea(new Dimension(5,10)));
Panel1.add(Panel11);
Panel1.add(Box.createRigidArea(new Dimension(5,10)));
Panel1.add(Panel12);
//Panel1.add(Box.createRigidArea(new Dimension(5,10)));
//Panel1.add(Panel13);
JPanel Panel2 = new JPanel();
Panel2.setLayout(new BoxLayout(Panel2,BoxLayout.X_AXIS));
Panel2.add(Box.createRigidArea(new Dimension(5,10)));
Panel2.add(BtnInsertar);
Panel2.add(Box.createRigidArea(new Dimension(5,10)));
Panel2.add(BtnModificar);
Panel2.add(Box.createRigidArea(new Dimension(5,10)));
Panel2.add(BtnActualizar);
Panel2.add(Box.createRigidArea(new Dimension(5,10)));
Panel2.add(BtnEliminar);
Panel2.add(Box.createRigidArea(new Dimension(5,10)));
Panel2.setBackground( new Color(0,0,255) );
//Panel13.add(new Box.Filler(new Dimension(10,20),new Dimension(10,20),new Dimension(10,20)));
JPanel Panel3 = new JPanel();
//SubPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//SubPanel.setLayout(new BorderLayout());
Panel3.setLayout(new BoxLayout(Panel3,BoxLayout.X_AXIS));
//SubPanel.add(PanelDatos);
Panel3.add(Box.createRigidArea(new Dimension(10,10)));
Panel3.add(BtnPrimero);
//SubPanel.add(scrollPanel,"South");
Panel3.add(Box.createRigidArea(new Dimension(10,10)));
Panel3.add(BtnAnterior);
Panel3.add(Box.createRigidArea(new Dimension(10,10)));
Panel3.add(BtnSiguiente);
Panel3.add(Box.createRigidArea(new Dimension(10,10)));
Panel3.add(BtnUltimo);
Panel3.add(Box.createRigidArea(new Dimension(10,10)));
Panel3.setBackground( Color.orange );
JPanel Panel = new JPanel();
//SubPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//SubPanel.setLayout(new BorderLayout());
Panel.setLayout(new BoxLayout(Panel,BoxLayout.Y_AXIS));
Panel.setAlignmentY(TOP_ALIGNMENT);
//SubPanel.add(PanelDatos);
Panel.add(Panel1);
Panel.add(Box.createRigidArea(new Dimension(0,20)));
Panel.add(Panel2);
Panel.add(Box.createRigidArea(new Dimension(0,20)));
Panel.add(Panel3);
//Panel.setBackground( Color.orange );
Panel.setBackground( new Color(255,0,0) );
getContentPane().setLayout(new
BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
getContentPane().add(Panel); addWindowListener(new
EventosVentana(this)); Conexion();
}
private void Conexion()
{ try {
//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Class.forName("com.mysql.jdbc.Driver").newInstance();
Connection conexion =
DriverManager.getConnection("jdbc:mysql://localhost:3306/EmpresaMSQL?useUnicode=true&characterEncoding=UTF-8",
    "n4nd0",
    "chacha");
//Connection conexion =
//DriverManager.getConnection("jdbc:odbc:Empresa"); 
sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); boolean tieneResultados=sentencia.execute("Select * from Categorias");
if(tieneResultados)
{
resultado = sentencia.getResultSet();
if (resultado != null)
{
mostrarResultados(resultado);
} else
{
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
String var; if(!resultado.previous()) resultado.first();
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
public void IrInsertar()
{
System.out.println("IIII");
try {
String var1,var2,var3;
resultado.first();
var1=TxtId.getText();
var2=TxtNombre.getText();
var3=TxtDescripcion.getText();
int Cantidad=sentencia.executeUpdate("INSERT INTO Categorias(Nombre,Descripcion)"+ "VALUES('"+var2+"','"+var3+"')");
}
catch(Exception e)
{
System.out.println("Error de Conexión "+e);
}
}
public void IrEliminar()
{
System.out.println("EEEE"); try {
String var1,var2,var3; resultado.first();
var1=TxtId.getText(); var2=TxtNombre.getText();
var3=TxtDescripcion.getText();
int Cantidad=sentencia.executeUpdate("DELETE FROM Categorias"+" WHERE IDCategoria="+var1);
}
catch(Exception e)
{
System.out.println("Error de Conexión "+e);
}
}
public void IrModificar()
{
System.out.println("MMM"); try {
    String var1,var2,var3; resultado.first();
var1=TxtId.getText(); var2=TxtNombre.getText();
var3=TxtDescripcion.getText();
int Cantidad=sentencia.executeUpdate("UPDATE Categorias SET Nombre="+"'"+var2+"',Descripcion="+"'"+var3+"'"+" WHERE IDCategoria="+var1);
}
catch(Exception e)
{
System.out.println("Error de Conexión "+e);
}
}
public void IrActualizar()
{
System.out.println("AA");
try { sentencia.execute("Select * from Categorias"); resultado =
sentencia.getResultSet();
}
catch(Exception e)
{
System.out.println("Error de Conexión "+e);
}
}
}

