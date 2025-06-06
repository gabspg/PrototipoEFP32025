/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.bancos;
import Controlador.seguridad.UsuarioConectado;  // Para obtener usuario actual
import Modelo.seguridad.UsuarioDAO;               // Para manejar la lógica de usuario (ajusta el paquete si es otro)
import Controlador.seguridad.permisos;          // La clase que representa los permisos del usuario (ajusta el paquete)

import Modelo.bancos.tipo_monedaDAO;
import Controlador.bancos.tipo_moneda;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Controlador.seguridad.Bitacora;
import Controlador.seguridad.UsuarioConectado;
import Modelo.Conexion;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

// MANTENIMIENTO CREADO POR MISHEL LOEIZA 9959-23-3457 

/**
 *
 * @author visitante
 */
public class MantenimientoTipo_moneda extends javax.swing.JInternalFrame {
    int APLICACION = 105;
        private Connection connectio;
    // 🔒 Variables para permisos
    private int idUsuarioSesion;
    private UsuarioDAO usuarioDAO;
    private permisos permisos;
private permisos permisosUsuarioActual; 

    public void llenadoDeCombos() {
        tipo_monedaDAO tipo_monedaDAO = new tipo_monedaDAO();
        List<tipo_moneda> monedas = tipo_monedaDAO.select();
       
        for (int i = 0; i < monedas.size(); i++) {
            
        }
    }

    public void llenadoDeTablas() {
       DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("id_tipo_moneda");
    modelo.addColumn("tipo_moneda");
    modelo.addColumn("ID Tasa");
    modelo.addColumn("Valor Tasa");  // Nueva columna

    tipo_monedaDAO tipo_monedaDAO = new tipo_monedaDAO();
    List<tipo_moneda> tipo_monedas = tipo_monedaDAO.select();
    tablaTipo_moneda.setModel(modelo);

    String[] dato = new String[4];  // Ahora son 4 columnas
    for (int i = 0; i < tipo_monedas.size(); i++) {
        tipo_moneda moneda = tipo_monedas.get(i);
        dato[0] = Integer.toString(moneda.getId_tipo_moneda());
        dato[1] = moneda.getTipo_moneda();
        dato[2] = Integer.toString(moneda.getId_tasa_cambio_diario());
        // Obtener el valor de la tasa
        dato[3] = String.valueOf(tipo_monedaDAO.obtenerValorTasaCambio(moneda.getId_tasa_cambio_diario()));
        modelo.addRow(dato);
    }
    }

    public void buscarMoneda() {
    tipo_moneda tipoMonedaConsultar = new tipo_moneda();
    tipo_monedaDAO tipo_monedaDAO = new tipo_monedaDAO();
    tipoMonedaConsultar.setId_tipo_moneda(Integer.parseInt(txtbuscado.getText()));
    tipoMonedaConsultar = tipo_monedaDAO.query(tipoMonedaConsultar);

    if (tipoMonedaConsultar != null) {
        txtTipo_moneda.setText(tipoMonedaConsultar.getTipo_moneda());
        txtTasa_cambio_usd.setText(Integer.toString(tipoMonedaConsultar.getId_tasa_cambio_diario()));
        
        // Obtener y mostrar el valor de la tasa de cambio
        double valorTasa = tipo_monedaDAO.obtenerValorTasaCambio(tipoMonedaConsultar.getId_tasa_cambio_diario());
        txtValorEnNumero.setText(String.valueOf(valorTasa));

        int resultadoBitacora = 0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(
            UsuarioConectado.getIdUsuario(), APLICACION, "Buscar Datos tipo_moneda"
        );
    } else {
        JOptionPane.showMessageDialog(this, "No se encontró el tipo de moneda con el ID especificado", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    public MantenimientoTipo_moneda() {
        initComponents();
        llenadoDeTablas();
        llenadoDeCombos();
    // 🔐 Validación de permisos
       idUsuarioSesion = UsuarioConectado.getIdUsuario();

        usuarioDAO = new UsuarioDAO();
        permisos = usuarioDAO.obtenerPermisosPorUsuario(idUsuarioSesion);

        
        btnEliminar.setEnabled(permisos.isPuedeEliminar());
        btnRegistrar.setEnabled(permisos.isPuedeRegistrar());
        btnModificar.setEnabled(permisos.isPuedeModificar());

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb2 = new javax.swing.JLabel();
        lbusu = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        label1 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        label3 = new javax.swing.JLabel();
        txtbuscado = new javax.swing.JTextField();
        txtTipo_moneda = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTipo_moneda = new javax.swing.JTable();
        txtTasa_cambio_usd = new javax.swing.JTextField();
        label5 = new javax.swing.JLabel();
        lb = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        btnReporte = new javax.swing.JButton();
        txtValorEnNumero = new javax.swing.JTextField();

        lb2.setForeground(new java.awt.Color(204, 204, 204));
        lb2.setText(".");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("MantenimientoTipo_Moneda");
        setVisible(true);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        label1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label1.setText("Tipo de Moneda");
        label1.setToolTipText("");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        label3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label3.setText("Tipo de Moneda");

        txtTipo_moneda.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtTipo_moneda.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        tablaTipo_moneda.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tablaTipo_moneda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id_tipo_moneda", "tipo_moneda", "Tasa de cambio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaTipo_moneda);
        if (tablaTipo_moneda.getColumnModel().getColumnCount() > 0) {
            tablaTipo_moneda.getColumnModel().getColumn(0).setResizable(false);
        }

        txtTasa_cambio_usd.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtTasa_cambio_usd.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        label5.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label5.setText("Tasa de Cambio");

        lb.setForeground(new java.awt.Color(204, 204, 204));
        lb.setText(".");

        jButton2.setText("Ayuda");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnReporte.setText("Reporte");
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });

        txtValorEnNumero.setEnabled(false);
        txtValorEnNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorEnNumeroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(358, 358, 358)
                                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label3)
                                    .addComponent(label5))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTasa_cambio_usd)
                                    .addComponent(txtTipo_moneda, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                            .addComponent(btnReporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtbuscado, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtValorEnNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 223, Short.MAX_VALUE)
                        .addComponent(label1)
                        .addGap(253, 253, 253))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label1)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lb)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTipo_moneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTasa_cambio_usd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label5))
                                .addGap(32, 32, 32)
                                .addComponent(txtValorEnNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(100, 100, 100)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnRegistrar)
                                    .addComponent(btnEliminar)
                                    .addComponent(btnModificar))
                                .addGap(18, 18, 18)
                                .addComponent(txtbuscado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnLimpiar)
                                    .addComponent(btnBuscar))
                                .addGap(10, 10, 10)))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(btnReporte)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
      // TODO add your handling code here:
tipo_monedaDAO tipo_monedaDAO = new tipo_monedaDAO();
tipo_moneda tipoMonedaAEliminar = new tipo_moneda();
tipoMonedaAEliminar.setId_tipo_moneda(Integer.parseInt(txtbuscado.getText()));
tipo_monedaDAO.delete(tipoMonedaAEliminar);
llenadoDeTablas();

UsuarioConectado usuarioEnSesion = new UsuarioConectado();
int resultadoBitacora = 0;
Bitacora bitacoraRegistro = new Bitacora();
resultadoBitacora = bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION, "Eliminar Datos tipo_moneda");

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
 // Instancia de tipo_monedaDAO
 
 tipo_monedaDAO tipo_monedaDAO = new tipo_monedaDAO();

// Verifica si el tipo de moneda ya existe
if (tipo_monedaDAO.existeTipoMoneda(txtTipo_moneda.getText())) {
    // Si el tipo de moneda ya existe, muestra un mensaje de error
    JOptionPane.showMessageDialog(null, "¡El tipo de moneda ya existe en la base de datos!", "Error", JOptionPane.ERROR_MESSAGE);
} else {
    // Si no existe, proceder con la inserción
    tipo_moneda tipoMonedaAInsertar = new tipo_moneda();
    tipoMonedaAInsertar.setTipo_moneda(txtTipo_moneda.getText());
    tipoMonedaAInsertar.setId_tasa_cambio_diario(Integer.parseInt(txtTasa_cambio_usd.getText()));

    // Inserta el nuevo tipo de moneda
    int resultado = tipo_monedaDAO.insert(tipoMonedaAInsertar);

    // Verifica si la inserción fue exitosa
    if (resultado > 0) {
        // Si la inserción fue exitosa, muestra un mensaje de éxito
        JOptionPane.showMessageDialog(null, "Tipo de moneda insertado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        
        // Limpiar los campos de texto después de la inserción
        txtTipo_moneda.setText("");
        txtTasa_cambio_usd.setText("");

        // Actualiza la tabla de datos (si es necesario)
        llenadoDeTablas();

        // Registrar el evento en la bitácora
        UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        Bitacora bitacoraRegistro = new Bitacora();
        bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION, "Insertar Datos tipo_moneda");
    } else {
        // Si la inserción falló, muestra un mensaje de error
        JOptionPane.showMessageDialog(null, "Error al insertar el tipo de moneda.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
 
 
 
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        buscarMoneda();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
//    // TODO add your handling code here:

tipo_monedaDAO tipo_monedaDAO = new tipo_monedaDAO();
tipo_moneda tipoMonedaAActualizar = new tipo_moneda();
tipoMonedaAActualizar.setId_tipo_moneda(Integer.parseInt(txtbuscado.getText()));
tipoMonedaAActualizar.setTipo_moneda(txtTipo_moneda.getText());
tipoMonedaAActualizar.setId_tasa_cambio_diario(Integer.parseInt(txtTasa_cambio_usd.getText()));
tipo_monedaDAO.update(tipoMonedaAActualizar);
llenadoDeTablas();

int resultadoBitacora = 0;
Bitacora bitacoraRegistro = new Bitacora();
resultadoBitacora = bitacoraRegistro.setIngresarBitacora(UsuarioConectado.getIdUsuario(), APLICACION, "Modificar Datos tipo_moneda");

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
 // Recorre todos los componentes dentro del panel principal//NUEVO METODO FUNCIONAL
    for (java.awt.Component comp : this.getContentPane().getComponents()) {
        if (comp instanceof javax.swing.JTextField) {
            ((javax.swing.JTextField) comp).setText("");
        } else if (comp instanceof javax.swing.JComboBox) {
            ((javax.swing.JComboBox<?>) comp).setSelectedIndex(0);
        }
    }
    // Aquí se habilitan los botones según los permisos actuales, no todos en true
    aplicarPermisos(permisosUsuarioActual);


    // botones estén habilitados
    btnRegistrar.setEnabled(true);
    btnModificar.setEnabled(true);
    btnEliminar.setEnabled(true);

    System.out.println("Todos los campos han sido limpiados automáticamente.");
      UsuarioConectado usuarioEnSesion = new UsuarioConectado();
        int resultadoBitacora=0;
        Bitacora bitacoraRegistro = new Bitacora();
        resultadoBitacora = bitacoraRegistro.setIngresarBitacora(usuarioEnSesion.getIdUsuario(), APLICACION,  "Limpiar TIPO MONEDA");
    }//GEN-LAST:event_btnLimpiarActionPerformed
/*
     // TODO add your handling code here:
        MantenimientoAula ventana = new MantenimientoAula();
        jDesktopPane1.add(ventana);
        Dimension desktopSize = jDesktopPane1.getSize();
        Dimension FrameSize = ventana.getSize();
        ventana.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
    */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            if ((new File("src\\main\\java\\ayudas\\banco\\AyudaBanco.chm")).exists()) {
                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler src\\main\\java\\ayudas\\banco\\AyudaBanco.chm");
                p.waitFor();
            } else {
                System.out.println("La ayuda no Fue encontrada");
            }
            System.out.println("Correcto");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        // TODO add your handling code here:
                       Map p = new HashMap();
        JasperReport report;
        JasperPrint print;

        try {
                           Connection connectio = Conexion.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/main/java/reporte/banco/reporteTipoMoneda.jrxml");
//
            print = JasperFillManager.fillReport(report, p, connectio);

            JasperViewer view = new JasperViewer(print, false);

            view.setTitle("Prueba reporte");
            view.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al generar el reporte: " + e.getMessage());
        }
        
                                              
    }//GEN-LAST:event_btnReporteActionPerformed

    private void txtValorEnNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorEnNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorEnNumeroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnReporte;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lbusu;
    private javax.swing.JTable tablaTipo_moneda;
    private javax.swing.JTextField txtTasa_cambio_usd;
    private javax.swing.JTextField txtTipo_moneda;
    private javax.swing.JTextField txtValorEnNumero;
    private javax.swing.JTextField txtbuscado;
    // End of variables declaration//GEN-END:variables

    private void aplicarPermisos(permisos permisosUsuarioActual) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
