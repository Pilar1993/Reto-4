package Vistas;

import Controlador.*;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class UserMenu extends javax.swing.JFrame{

    Conexion conexion = new Conexion();
    Connection connection;
    Statement st;
    ResultSet rs;
    DefaultTableModel contenidoTablaEmpleados, contenidoTablaDepartamentos;
    ComboBoxModel modelEnumDepartamentos, modelEnumZona, modelEnumTipoCalle;

    public UserMenu() {
	modelEnumDepartamentos = new DefaultComboBoxModel(EnumDepartamentos.values());
	modelEnumZona = new DefaultComboBoxModel(EnumZona.values());
	modelEnumTipoCalle = new DefaultComboBoxModel(EnumTipoCalle.values());
	initComponents();
	listarEmpleados();
	listarDepartamentos();
    }

    private void listarDepartamentos() {
	String nombreDepartamento = txtSearchDepartamento.getText();
	if (nombreDepartamento.isEmpty()) {
	    String query = "SELECT `nombreSucursal`, `nombreDepartamento`, CONCAT( 'Zona ',`zona`, '.', `tipoCalle`,' ', `numero1`, 'N°.', `numero2`, '-', `numero3`) AS direccion FROM direccion INNER JOIN sucursal WHERE idDireccion = FK_idDireccion AND nombreDepartamento LIKE '%%' ORDER BY nombreDepartamento;";
	    try {
		connection = conexion.getConnection();
		st = connection.createStatement();
		rs = st.executeQuery(query);
		Object[] direccion = new Object[3];
		contenidoTablaDepartamentos = (DefaultTableModel) tblDepartamentos.getModel();
		while (rs.next()) {
		    direccion[0] = rs.getString("nombreSucursal");
		    direccion[1] = rs.getString("nombreDepartamento");
		    direccion[2] = rs.getString("direccion");
		    System.out.println("sucursal: " + direccion[0] + ", departamento: " + direccion[1]);
		    contenidoTablaDepartamentos.addRow(direccion);
		    tblDepartamentos.setModel(contenidoTablaDepartamentos);
		}
	    } catch (SQLException e) {
		System.out.println(e);
	    }
	} else {
	    String query = "SELECT `nombreSucursal`, `nombreDepartamento`, CONCAT( 'Zona ',`zona`, '.', `tipoCalle`,' ', `numero1`, 'N°.', `numero2`, '-', `numero3`) AS direccion FROM direccion INNER JOIN sucursal WHERE idDireccion = FK_idDireccion AND nombreDepartamento LIKE '%%' ORDER BY nombreDepartamento;";
	    try {
		connection = conexion.getConnection();
		st = connection.createStatement();
		rs = st.executeQuery(query);
		Object[] direccion = new Object[3];
		contenidoTablaDepartamentos = (DefaultTableModel) tblDepartamentos.getModel();
		while (rs.next()) {
		     direccion[0] = rs.getString("nombreSucursal");
		    direccion[1] = rs.getString("nombreDepartamento");
		    direccion[2] = rs.getString("direccion");
		    System.out.println("sucursal: " + direccion[0] + ", departamento: " + direccion[1]);
		    contenidoTablaDepartamentos.addRow(direccion);
		    tblDepartamentos.setModel(contenidoTablaDepartamentos);
		}
	    } catch (SQLException e) {
		System.out.println(e);
	    }
	}
    }


    public void borrarDatosTablaDepartamentos() {
	for (int i = 0; i < tblDepartamentos.getRowCount(); i++) {
	    contenidoTablaDepartamentos.removeRow(i);
	    i -= 1;
	}
    }

    private void listarEmpleados() {
	String filtroBusqueda = txtSearchDepartamento.getText();
	System.out.println("Buscar los empleados con nombre o apellido: " + filtroBusqueda);
	if (filtroBusqueda.isEmpty()) {
	    String queryConsulta = "SELECT nombreEmp,apellidos,tipoDocumento,documento,correo, nombreSucursal FROM empleado INNER JOIN sucursal ON empleado.FK_idSucursal = sucursal.idSucursal";
	    try {
		connection = conexion.getConnection();
		st = connection.createStatement();
		rs = st.executeQuery(queryConsulta);
		Object[] empleados = new Object[6];
		contenidoTablaEmpleados = (DefaultTableModel) tblEmpleados.getModel();
		while (rs.next()) {
		    empleados[0] = rs.getString("nombreEmp");
		    empleados[1] = rs.getString("apellidos");
		    empleados[2] = rs.getString("tipoDocumento");
		    empleados[3] = rs.getString("documento");
		    empleados[4] = rs.getString("correo");
		    empleados[5] = rs.getString("nombreSucursal");		     
		    contenidoTablaEmpleados.addRow(empleados);
		    tblEmpleados.setModel(contenidoTablaEmpleados);
		}
	    } catch (SQLException e) {
		System.out.println("Error");
	    }
	} else {
	    String queryConsulta = "SELECT nombreEmp,apellidos,tipoDocumento,documento,correo, nombreSucursal FROM ´empleado´ WHERE nombreEmp LIKE '%" + filtroBusqueda + "%' OR apellidos LIKE '%" + filtroBusqueda + "%';";
            System.out.println(queryConsulta);
	    try {
		connection = conexion.getConnection();
		st = connection.createStatement();
		rs = st.executeQuery(queryConsulta);
		Object[] empleados = new Object[6];
		contenidoTablaEmpleados = (DefaultTableModel) tblEmpleados.getModel();
		while (rs.next()) {
		    empleados[0] = rs.getString("nombreEmp");
		    empleados[1] = rs.getString("apellidos");
		    empleados[2] = rs.getString("tipoDocumento");
		    empleados[3] = rs.getString("documento");
		    empleados[4] = rs.getString("correo");
		    empleados[5] = rs.getString("nombreSucursal");
		    contenidoTablaEmpleados.addRow(empleados);
		    tblEmpleados.setModel(contenidoTablaEmpleados);
		}
	    } catch (SQLException e) {
		System.out.println("Error");
	    }
	}
    }


    private void borrarDatosTabla() {
	for (int i = 0; i < tblEmpleados.getRowCount(); i++) {
	    contenidoTablaEmpleados.removeRow(i);
	    i -= 1;
	}
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        empleadosTab = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnAddUser = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblEmpleados = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        sucursalesTab = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbDepartamento = new javax.swing.JComboBox<>();
        cbZona = new javax.swing.JComboBox<>();
        cbCalle = new javax.swing.JComboBox<>();
        txtNumero2 = new javax.swing.JTextField();
        txtNumero1 = new javax.swing.JTextField();
        txtNumero3 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        txtSearchDepartamento = new javax.swing.JTextField();
        btnListarEmpleados = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDepartamentos = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        empleadosTab.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Informacion Empleados");

        btnAddUser.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAddUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Crear.png"))); // NOI18N
        btnAddUser.setText("Crear Empleado");
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Nombre:");

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        tblEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre(s)", "Apellido(s)", "Tipo Documento", "Documento", "Correo", "Sucursal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEmpleados.setEnabled(false);
        tblEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpleadosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblEmpleados);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Buscar.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout empleadosTabLayout = new javax.swing.GroupLayout(empleadosTab);
        empleadosTab.setLayout(empleadosTabLayout);
        empleadosTabLayout.setHorizontalGroup(
            empleadosTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empleadosTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(empleadosTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(empleadosTabLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6))
                    .addGroup(empleadosTabLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)))
                .addGap(284, 284, 284))
            .addGroup(empleadosTabLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(empleadosTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(empleadosTabLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAddUser))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 28, Short.MAX_VALUE))
        );
        empleadosTabLayout.setVerticalGroup(
            empleadosTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empleadosTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(empleadosTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addGap(36, 36, 36)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(empleadosTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(empleadosTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAddUser, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1))
                    .addGroup(empleadosTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Empleados", empleadosTab);

        sucursalesTab.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo.png"))); // NOI18N

        cbDepartamento.setModel(modelEnumDepartamentos);

        cbZona.setModel(modelEnumZona);

        cbCalle.setModel(modelEnumTipoCalle
        );
        cbCalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCalleActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Departamento:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Zona:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Tipo Calle:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("N°:");

        jLabel3.setText("-");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Direcciones");

        btnSearch.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Consulta.png"))); // NOI18N
        btnSearch.setText("Buscar");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        txtSearchDepartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchDepartamentoActionPerformed(evt);
            }
        });

        btnListarEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Avatar_1.png"))); // NOI18N
        btnListarEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarEmpleadosActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        tblDepartamentos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tblDepartamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sucursal", "Departamento", "Direccion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblDepartamentos);
        if (tblDepartamentos.getColumnModel().getColumnCount() > 0) {
            tblDepartamentos.getColumnModel().getColumn(2).setHeaderValue("Direccion");
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout sucursalesTabLayout = new javax.swing.GroupLayout(sucursalesTab);
        sucursalesTab.setLayout(sucursalesTabLayout);
        sucursalesTabLayout.setHorizontalGroup(
            sucursalesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sucursalesTabLayout.createSequentialGroup()
                .addGroup(sucursalesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sucursalesTabLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGroup(sucursalesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sucursalesTabLayout.createSequentialGroup()
                                .addGap(171, 171, 171)
                                .addComponent(jLabel4))
                            .addGroup(sucursalesTabLayout.createSequentialGroup()
                                .addGap(175, 175, 175)
                                .addComponent(jLabel12)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(sucursalesTabLayout.createSequentialGroup()
                        .addGroup(sucursalesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sucursalesTabLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(sucursalesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sucursalesTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel13)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(sucursalesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sucursalesTabLayout.createSequentialGroup()
                                .addComponent(txtSearchDepartamento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch))
                            .addGroup(sucursalesTabLayout.createSequentialGroup()
                                .addGroup(sucursalesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(sucursalesTabLayout.createSequentialGroup()
                                        .addComponent(cbCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNumero1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel11))
                                    .addGroup(sucursalesTabLayout.createSequentialGroup()
                                        .addComponent(cbDepartamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(sucursalesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(sucursalesTabLayout.createSequentialGroup()
                                        .addComponent(txtNumero2, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtNumero3, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                                    .addComponent(cbZona, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)))
                .addGroup(sucursalesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnListarEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32))
            .addGroup(sucursalesTabLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sucursalesTabLayout.setVerticalGroup(
            sucursalesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sucursalesTabLayout.createSequentialGroup()
                .addGroup(sucursalesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sucursalesTabLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sucursalesTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12)
                        .addGap(20, 20, 20)
                        .addComponent(btnGuardar)
                        .addGap(6, 6, 6)))
                .addGroup(sucursalesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sucursalesTabLayout.createSequentialGroup()
                        .addGroup(sucursalesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sucursalesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSearchDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13))
                            .addComponent(jLabel4))
                        .addGap(6, 6, 6)
                        .addGroup(sucursalesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(cbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(sucursalesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNumero2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumero3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel11)
                            .addComponent(cbCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumero1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addComponent(btnListarEmpleados))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sucursales", sucursalesTab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
	AddUserForm addUserF = new AddUserForm(this, true);
	addUserF.setVisible(true);
	borrarDatosTabla();
	listarEmpleados();
    }//GEN-LAST:event_btnAddUserActionPerformed

    private void cbCalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCalleActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_cbCalleActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
	borrarDatosTablaDepartamentos();
	listarDepartamentos();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchDepartamentoActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_txtSearchDepartamentoActionPerformed

    private void tblEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleadosMouseClicked
	int row = tblEmpleados.getSelectedRow();
	System.out.println("Fila seleccionada" + row);
	if (row<0) {
	    JOptionPane.showMessageDialog(this, "Selecciona un empleado.", "", JOptionPane.WARNING_MESSAGE);
	} else {
	    
	    String nombreEmp = (String) tblEmpleados.getValueAt(row,0);
	    String apellidos = (String) tblEmpleados.getValueAt(row,1);
	    String tipoDocumento = (String) tblEmpleados.getValueAt(row,2);
	    String documento = (String) tblEmpleados.getValueAt(row,3);
	    String correo = (String) tblEmpleados.getValueAt(row,4);
	    String sucursal = (String) tblEmpleados.getValueAt(row,5);
	    
	    
	    ShowUserForm showUserForm = new ShowUserForm(this, true);
	    showUserForm.recibeDatos(nombreEmp, apellidos, tipoDocumento, documento, correo,sucursal);
	    showUserForm.setVisible(true);
	    borrarDatosTabla();
	    listarEmpleados();
	}
    }//GEN-LAST:event_tblEmpleadosMouseClicked

    private void btnListarEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarEmpleadosActionPerformed
	int filaSeleccionada = tblDepartamentos.getSelectedRow();
        String sucursal = tblDepartamentos.getValueAt(filaSeleccionada,0).toString();
	String query = "SELECT idSucursal FROM sucursal WHERE nombreSucursal = '" + sucursal + "';";
	System.out.println(query);
	try {
	    connection = conexion.getConnection();
	    st = connection.createStatement();
	    rs = st.executeQuery(query);
	    while (rs.next()) {
		int idSucursal = rs.getInt("idSucursal");
		EmpleadoForm empleadoForm = new EmpleadoForm(this, true);
		empleadoForm.setVisible(true);
		empleadoForm.recibeIdSucursal(idSucursal);
	    }
	} catch (SQLException e) {
	    System.out.println(e);
	}
    }//GEN-LAST:event_btnListarEmpleadosActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
	String departamento = cbDepartamento.getSelectedItem().toString();
	String zona = cbZona.getSelectedItem().toString();
	String tipoCalle = cbCalle.getSelectedItem().toString();
	String numero1 = txtNumero1.getText();
	String numero2 = txtNumero2.getText();
	String numero3 = txtNumero3.getText();
	String query = "INSERT INTO `direccion`(`zona`, `Calle`, `numero1`, `numero2`, `numero3`, `nombreDepartamento`) VALUES ('" + zona + "','" + tipoCalle + "','" + numero1 + "','" + numero2 + "','" + numero3 + "','" + departamento + "');";
	System.out.println(query);
	try {
	    connection = conexion.getConnection();
	    st = connection.createStatement();
	    st.executeUpdate(query);
	    SucursalForm sucursalForm = new SucursalForm(this, true);
	    sucursalForm.setVisible(true);
	    String queryIdDireccion = "SELECT idDireccion FROM `direccion` WHERE nombreDepartamento = '" + departamento + "' AND zona = '" + zona + "' AND tipoCalle = '" + tipoCalle + "' AND numero1 = '" + numero1 + "'AND numero2 ='" + numero2 + "' AND numero3 = '" + numero3 + "';";
	    try {
		st = connection.createStatement();
		rs = st.executeQuery(queryIdDireccion);
		int idDireccion;
		while (rs.next()) {
		    idDireccion = rs.getInt("idDireccion");
		    sucursalForm.recibeIdDireccion(idDireccion);
		}
		JOptionPane.showMessageDialog(this, "La sucursal ha sido creada.");
		borrarDatosTabla();
		listarDepartamentos();
	    } catch (SQLException e) {
		System.out.println(e);
	    }

	} catch (SQLException e) {
	    System.out.println(e);
	    JOptionPane.showMessageDialog(this, "No fue posible crear la dirección", "Sucursales Misión TIC", JOptionPane.ERROR_MESSAGE);
	}
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
	borrarDatosTabla();
	listarEmpleados();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
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
	} catch (ClassNotFoundException e) {
	    java.util.logging.Logger.getLogger(UserMenu.class
		    .getName()).log(java.util.logging.Level.SEVERE, null, e);

	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(UserMenu.class
		    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(UserMenu.class
		    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(UserMenu.class
		    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(new Runnable()
	  {
	    public void run() {
		new UserMenu().setVisible(true);
	    }
	  });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnListarEmpleados;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbCalle;
    private javax.swing.JComboBox<String> cbDepartamento;
    private javax.swing.JComboBox<String> cbZona;
    private javax.swing.JPanel empleadosTab;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JPanel sucursalesTab;
    private javax.swing.JTable tblDepartamentos;
    private javax.swing.JTable tblEmpleados;
    private javax.swing.JTextField txtNumero1;
    private javax.swing.JTextField txtNumero2;
    private javax.swing.JTextField txtNumero3;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearchDepartamento;
    // End of variables declaration//GEN-END:variables
  }
