package GUI;

import BO.*;
import MODEL.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.json.simple.parser.ParseException;

/**
 *
 * @author deivi
 */
public final class Administrador extends javax.swing.JFrame {

    private final ClienteBO clienteBO = new ClienteBO();
    private final ProveedorBO proveedorBO = new ProveedorBO();
    private final ProductoBO productoBO = new ProductoBO();
    private final MarcaBO marcaBO = new MarcaBO();
    private final CompraBO compraBO = new CompraBO();
    private final DetalleCompraBO detalleCompraBO = new DetalleCompraBO();
    private IDManagerBO idmanager = new IDManagerBO();
    private Object Validar;
    private Object nuevoProvvedor;

    public Administrador() {
        initComponents();
        actualizarTablaClientes();
        comboCategorias.addItem("Bebidas");
        comboCategorias.addItem("Abarrotes");
        comboCategorias.addItem("Limpieza");
        comboCategorias.addItem("Mascotas");
        comboCategorias.addItem("Frescos");
    }

    // Method to check if the fields are empty
    private boolean camposVacios(Object... campos) {
        for (Object campo : campos) {
            if (campo == null || (campo instanceof String && ((String) campo).isEmpty())) {
                return true;
            }
        }
        return false;
    }

    private void limpiarCampos() {
        this.txtCedulaCliente.setText("");
        this.txtNombreCliente.setText("");
        this.txt1ApellidoCliente.setText("");
        this.txt2ApellidoCliente.setText("");
        this.txtTelefonoCliente.setText("");
        this.txtCorreoCliente.setText("");
    
    }

    public void actualizarTablaClientes() {
        clienteBO.actualizarTabla((DefaultTableModel) tblClientes.getModel());
    }


    private void actualizarTablaProductos(String subcategoria) {
        String subcategoriaSeleccionada = (String) comboSubcategorias.getSelectedItem();
        if (subcategoriaSeleccionada != null) {
            productoBO.actualizarTabla((DefaultTableModel) tblProducto.getModel(), subcategoriaSeleccionada);
        }
    }

    //here we remove the items and replace with the new items asignaded
    private void actualizarComboSubcategorias(String categoria) {
        comboSubcategorias.removeAllItems();
        switch (categoria) {
            case "Bebidas":
                agregarSubcategoriaItems("Cervezas", "Licores", "Vinos", "Jugos", "Gaseosas", "Energizantes");
                break;
            case "Abarrotes":
                agregarSubcategoriaItems("Granos y Pastas", "Aceites_Grasas", "Botanas", "Enlatados");
                break;
            case "Limpieza":
                agregarSubcategoriaItems("Detergentes", "Lava platos", "Desinfectante y limpiadores", "Accesorios");
                break;
            case "Mascotas":
                agregarSubcategoriaItems("Alimentos_Mascotas", "Accesorios_Mascotas", "Limpieza_Mascotas");
                break;
            case "Frescos":
                agregarSubcategoriaItems("Pollo", "Cerdo", "Res", "Mariscos", "Frutas", "Verduras", "Embutidos");
                break;
            default:
                break;
        }
    }

    private void agregarSubcategoriaItems(String... items) {
        for (String item : items) {
            comboSubcategorias.addItem(item);
        }
    }

    private void cargarProductos(String subcategoria) {
        DefaultTableModel modeloTabla = (DefaultTableModel) tblProducto.getModel();
        productoBO.actualizarTabla(modeloTabla, subcategoria);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JTabMain = new javax.swing.JTabbedPane();
        JpInicio = new javax.swing.JPanel();
        JPLinea = new javax.swing.JPanel();
        lblNombreEmpresa = new javax.swing.JLabel();
        lblImejenInicio = new javax.swing.JLabel();
        JpClientes = new javax.swing.JPanel();
        scrollClientes = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCedulaCliente = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        txt1ApellidoCliente = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt2ApellidoCliente = new javax.swing.JTextField();
        txtTelefonoCliente = new javax.swing.JTextField();
        txtCorreoCliente = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnEditarCliente = new javax.swing.JButton();
        btnEliminarCliente = new javax.swing.JButton();
        btnGuardarCliente = new javax.swing.JButton();
        lblImajen1 = new javax.swing.JLabel();
        lblImajen2 = new javax.swing.JLabel();
        lblImajen3 = new javax.swing.JLabel();
        lblImajen4 = new javax.swing.JLabel();
        lblImajen5 = new javax.swing.JLabel();
        lblImajen6 = new javax.swing.JLabel();
        JpProductos = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblProducto = new javax.swing.JTable();
        comboSubcategorias = new javax.swing.JComboBox<>();
        comboCategorias = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        txtPrecioProducto = new javax.swing.JTextField();
        txtPesoProducto = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtIDProveedorProducto = new javax.swing.JTextField();
        txtIDCategoriaProducto = new javax.swing.JTextField();
        txtIDMarcaProducto = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        btnEditarProducto = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        btnGuardarProducto = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        JTabMain.setBackground(new java.awt.Color(102, 255, 102));
        JTabMain.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JTabMain.setOpaque(true);

        JpInicio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JPLinea.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout JPLineaLayout = new javax.swing.GroupLayout(JPLinea);
        JPLinea.setLayout(JPLineaLayout);
        JPLineaLayout.setHorizontalGroup(
            JPLineaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        JPLineaLayout.setVerticalGroup(
            JPLineaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        JpInicio.add(JPLinea, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 300, 730, 10));

        lblNombreEmpresa.setBackground(new java.awt.Color(0, 0, 0));
        lblNombreEmpresa.setFont(new java.awt.Font("Segoe UI Emoji", 0, 48)); // NOI18N
        lblNombreEmpresa.setText("Bienvenidos a ADF VIRTUAL STORE");
        JpInicio.add(lblNombreEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, 810, -1));

        lblImejenInicio.setForeground(new java.awt.Color(0, 0, 153));
        lblImejenInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/Retail-de-supermercado.png"))); // NOI18N
        JpInicio.add(lblImejenInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(-160, 0, 1800, 810));

        JTabMain.addTab("Inicio", JpInicio);

        JpClientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Cedula", "Nombre", "Primer Apellido", "Segundo Apellido", "Teléfono", "Correo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollClientes.setViewportView(tblClientes);

        JpClientes.add(scrollClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 1040, 230));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        jLabel10.setText("Cedula:");

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        jLabel11.setText("Nombre:");

        jLabel12.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        jLabel12.setText("Primer Apellido:");

        txtCedulaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaClienteActionPerformed(evt);
            }
        });
        txtCedulaCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaClienteKeyTyped(evt);
            }
        });

        txtNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreClienteKeyTyped(evt);
            }
        });

        txt1ApellidoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt1ApellidoClienteKeyTyped(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        jLabel13.setText("Segundo Apellido:");

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        jLabel14.setText("Telefono:");

        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        jLabel15.setText("Correo:");

        txt2ApellidoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt2ApellidoClienteKeyTyped(evt);
            }
        });

        txtTelefonoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoClienteKeyTyped(evt);
            }
        });

        txtCorreoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoClienteKeyTyped(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnEditarCliente.setBackground(new java.awt.Color(0, 102, 102));
        btnEditarCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarCliente.setText("Editar");
        btnEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarClienteActionPerformed(evt);
            }
        });

        btnEliminarCliente.setBackground(new java.awt.Color(153, 0, 0));
        btnEliminarCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarCliente.setText("Eliminar");
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });

        btnGuardarCliente.setBackground(new java.awt.Color(0, 153, 51));
        btnGuardarCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarCliente.setText("Guardar");
        btnGuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                    .addComponent(btnEditarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEditarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnGuardarCliente)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt1ApellidoCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCedulaCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(309, 309, 309)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCorreoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(txt2ApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txt2ApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtCedulaCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(2, 2, 2))
                            .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(txt1ApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCorreoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel15))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        JpClientes.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 1040, 190));

        lblImajen1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/9ecda51d8c2bad23237e6e63159df01b.jpg"))); // NOI18N
        JpClientes.add(lblImajen1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 740, 420));

        lblImajen2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/9ecda51d8c2bad23237e6e63159df01b.jpg"))); // NOI18N
        JpClientes.add(lblImajen2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -10, 730, 420));

        lblImajen3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/9ecda51d8c2bad23237e6e63159df01b.jpg"))); // NOI18N
        JpClientes.add(lblImajen3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, -10, 460, 430));

        lblImajen4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/9ecda51d8c2bad23237e6e63159df01b.jpg"))); // NOI18N
        JpClientes.add(lblImajen4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, -1, 410));

        lblImajen5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/9ecda51d8c2bad23237e6e63159df01b.jpg"))); // NOI18N
        JpClientes.add(lblImajen5, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 410, 450, 410));

        lblImajen6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/9ecda51d8c2bad23237e6e63159df01b.jpg"))); // NOI18N
        JpClientes.add(lblImajen6, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 410, 550, 400));

        JTabMain.addTab("Gestion de Clientes", JpClientes);

        JpProductos.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        tblProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NOMBRE", "PRECIO", "PESO", "ID PROVEEDOR", "ID CATEGORIA", "ID MARCA"
            }
        ));
        jScrollPane4.setViewportView(tblProducto);

        comboSubcategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSubcategoriasActionPerformed(evt);
            }
        });

        comboCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCategoriasActionPerformed(evt);
            }
        });

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Prodcutos"));

        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        jLabel16.setText("Nombre");

        jLabel17.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        jLabel17.setText("Precio");

        jLabel18.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        jLabel18.setText("Peso");

        txtNombreProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreProductoActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        jLabel19.setText("ID Proveedor");

        jLabel20.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        jLabel20.setText("ID categoria");

        jLabel21.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        jLabel21.setText("ID marca");

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnEditarProducto.setBackground(new java.awt.Color(0, 102, 102));
        btnEditarProducto.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarProducto.setText("Editar");
        btnEditarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProductoActionPerformed(evt);
            }
        });

        btnEliminarProducto.setBackground(new java.awt.Color(153, 0, 0));
        btnEliminarProducto.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarProducto.setText("Eliminar");
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });

        btnGuardarProducto.setBackground(new java.awt.Color(0, 153, 51));
        btnGuardarProducto.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarProducto.setText("Guardar");
        btnGuardarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEditarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardarProducto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarProducto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEditarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnGuardarProducto)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPesoProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecioProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(309, 309, 309)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtIDMarcaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtIDCategoriaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(txtIDProveedorProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(txtIDProveedorProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtNombreProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(2, 2, 2))
                            .addComponent(txtPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtIDCategoriaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel20)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel18)
                                .addComponent(txtPesoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtIDMarcaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel21))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        btnCerrarSesion.setText("CERRAR SESION");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 951, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(comboCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(comboSubcategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCerrarSesion))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(141, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboSubcategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerrarSesion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout JpProductosLayout = new javax.swing.GroupLayout(JpProductos);
        JpProductos.setLayout(JpProductosLayout);
        JpProductosLayout.setHorizontalGroup(
            JpProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpProductosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        JpProductosLayout.setVerticalGroup(
            JpProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JTabMain.addTab("Gestion de Productos", JpProductos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JTabMain, javax.swing.GroupLayout.PREFERRED_SIZE, 1181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JTabMain, javax.swing.GroupLayout.PREFERRED_SIZE, 847, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        Login login = new Login();
        login.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnGuardarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProductoActionPerformed
        String subcategoria = (String) comboSubcategorias.getSelectedItem();

        // Obtener los datos ingresados en los campos de texto
        String nombre = txtNombreProducto.getText();
        double precio = Double.parseDouble(txtPrecioProducto.getText());
        String peso = txtPesoProducto.getText();
        String idProveedor = txtIDProveedorProducto.getText();
        String idCategoria = txtIDCategoriaProducto.getText();
        String idMarca = txtIDMarcaProducto.getText();

        // Crear una instancia de Producto con los datos ingresados
        Producto nuevoProducto = new Producto(nombre, precio, peso, idProveedor, idCategoria, idMarca);

        // Crear instancia de ProductoBO
        ProductoBO productoBO = new ProductoBO();

        // Llamar al método guardarProducto en ProductoBO
        productoBO.guardarProducto(subcategoria, nuevoProducto);
        // Actualizar la tabla y limpiar los campos
        actualizarTablaProductos(subcategoria);
        limpiarCampos();
    }//GEN-LAST:event_btnGuardarProductoActionPerformed

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        int selectedRow = tblProducto.getSelectedRow();
        if (selectedRow != -1) {
            String idProductoAEliminar = tblProducto.getValueAt(selectedRow, 0).toString();
            String subcategoria = (String) comboSubcategorias.getSelectedItem();

            // Crear una instancia de ProductoBO
            ProductoBO productoBO = new ProductoBO();

            // Llamar al método eliminarProducto en ProductoBO
            productoBO.eliminarProducto(subcategoria, idProductoAEliminar);
            // Actualizar la tabla de productos
            actualizarTablaProductos(subcategoria);
            limpiarCampos();
        }
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnEditarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProductoActionPerformed
        int selectedRow = tblProducto.getSelectedRow();
        if (selectedRow >= 0) {
            String idProducto = tblProducto.getValueAt(selectedRow, 0).toString();
            String subcategoria = (String) comboSubcategorias.getSelectedItem();
            String nombre = txtNombreProducto.getText();
            double precio = 0.0;
            String precioTexto = txtPrecioProducto.getText();
            if (!precioTexto.isEmpty()) {
                precio = Double.parseDouble(precioTexto);
            }
            String peso = txtPesoProducto.getText();
            String idProveedor = txtIDProveedorProducto.getText();
            String idCategoria = txtIDCategoriaProducto.getText();
            String idMarca = txtIDMarcaProducto.getText();

            // Crear una instancia de Producto con los datos ingresados
            Producto productoActualizado = new Producto(idProducto, nombre, precio, peso, idProveedor, idCategoria, idMarca);

            // Crear instancia de ProductoBO
            ProductoBO productoBO = new ProductoBO();

            // Llamar al método editarProducto en ProductoBO pasando la subcategoria y el objeto producto
            productoBO.editarProducto(subcategoria, productoActualizado);
            // Actualizar la tabla de productos
            actualizarTablaProductos(subcategoria);
            // Limpiar los campos de entrada
            limpiarCampos();
            JOptionPane.showMessageDialog(null, "Se actualizó el producto");
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un producto en la tabla");
        }
    }//GEN-LAST:event_btnEditarProductoActionPerformed

    private void txtNombreProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreProductoActionPerformed

    private void comboCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCategoriasActionPerformed
        String categoriaSeleccionada = (String) comboCategorias.getSelectedItem();
        actualizarComboSubcategorias(categoriaSeleccionada);
        String subcategoriaSeleccionada = (String) comboSubcategorias.getSelectedItem();
        cargarProductos(subcategoriaSeleccionada);
    }//GEN-LAST:event_comboCategoriasActionPerformed

    private void comboSubcategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSubcategoriasActionPerformed
        String subcategoriaSeleccionada = (String) comboSubcategorias.getSelectedItem();
        cargarProductos(subcategoriaSeleccionada);
    }//GEN-LAST:event_comboSubcategoriasActionPerformed

    private void btnGuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarClienteActionPerformed
        // Obtener los datos ingresados en los campos de texto
        String cedula = txtCedulaCliente.getText();
        String nombre = txtNombreCliente.getText();
        String primerApellido = txt1ApellidoCliente.getText();
        String segundoApellido = txt2ApellidoCliente.getText();
        String telefono = txtTelefonoCliente.getText();
        String correo = txtCorreoCliente.getText();

        // Verificar que los campos no estén vacíos
        if (camposVacios(cedula, nombre, primerApellido, segundoApellido, telefono, correo)) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear una instancia de Cliente con los datos ingresados
        Cliente nuevoCliente = new Cliente(
            idmanager.generarID("cliente.json") + 1, // Genera el ID para el cliente
            cedula,
            nombre,
            primerApellido,
            segundoApellido,
            telefono,
            correo
        );

        // Crear instancia de ClienteBO
        ClienteBO clienteBO = new ClienteBO();

        // Guardar el nuevo cliente a través de ClienteBO
        clienteBO.guardarCliente(nuevoCliente);

        // Actualizar la tabla de clientes
        actualizarTablaClientes();

        // Limpiar los campos de entrada
        limpiarCampos();
    }//GEN-LAST:event_btnGuardarClienteActionPerformed

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed
        int selectedRow = tblClientes.getSelectedRow();
        if (selectedRow != -1) {
            int idClienteAEliminar = Integer.parseInt(tblClientes.getValueAt(selectedRow, 0).toString());

            // Crear instancia de ClienteBO
            ClienteBO clienteBO = new ClienteBO();

            // Llamar al método eliminarCliente en ClienteBO
            clienteBO.eliminarCliente(idClienteAEliminar);

            // Actualizar la tabla de clientes y limpiar campos
            actualizarTablaClientes();
            limpiarCampos();
        }
    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    private void btnEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClienteActionPerformed
        int selectedRow = tblClientes.getSelectedRow();
        if (selectedRow != -1) {
            String cedula = txtCedulaCliente.getText();
            String nombre = txtNombreCliente.getText();
            String primerA = txt1ApellidoCliente.getText();
            String segundoA = txt2ApellidoCliente.getText();
            String telefono = txtTelefonoCliente.getText();
            String correo = txtCorreoCliente.getText();

            // Obtener el ID del cliente a partir de la tabla
            int idCliente = Integer.parseInt(tblClientes.getValueAt(selectedRow, 0).toString());

            // Crear instancia de ClienteBO
            ClienteBO clienteBO = new ClienteBO();

            // Llamar al método editarCliente en ClienteBO pasando los parámetros individuales
            clienteBO.editarCliente(idCliente, cedula, nombre, primerA, segundoA, telefono, correo);
            // Actualizar la tabla de clientes y limpiar campos
            actualizarTablaClientes();
            limpiarCampos();
            JOptionPane.showMessageDialog(null, "Se actualizó el cliente");
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un Cliente en la tabla");
        }
    }//GEN-LAST:event_btnEditarClienteActionPerformed

    private void txtCorreoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoClienteKeyTyped
        char caracter = evt.getKeyChar();

        if (!Character.isLetterOrDigit(caracter) && caracter != '@' && caracter != '.' && caracter != '_') {

            javax.swing.JOptionPane.showMessageDialog(this, "Caracter no válido ingresado", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

            evt.consume();
        }
    }//GEN-LAST:event_txtCorreoClienteKeyTyped

    private void txtTelefonoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoClienteKeyTyped
        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Solo se permiten números.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtTelefonoClienteKeyTyped

    private void txt2ApellidoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt2ApellidoClienteKeyTyped
        char c = evt.getKeyChar();

        if (!Character.isLetter(c) && c != 'á' && c != 'é' && c != 'í' && c != 'ó' && c != 'ú'
            && !Character.isUpperCase(c) && !Character.isLowerCase(c)) {
            evt.consume();

            JOptionPane.showMessageDialog(this, "Solo se permiten letras, tildes, mayúsculas y minúsculas.");
        }
    }//GEN-LAST:event_txt2ApellidoClienteKeyTyped

    private void txt1ApellidoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1ApellidoClienteKeyTyped
        char c = evt.getKeyChar();

        if (!Character.isLetter(c) && c != 'á' && c != 'é' && c != 'í' && c != 'ó' && c != 'ú'
            && !Character.isUpperCase(c) && !Character.isLowerCase(c)) {
            evt.consume();

            JOptionPane.showMessageDialog(this, "Solo se permiten letras, tildes, mayúsculas y minúsculas.");
        }
    }//GEN-LAST:event_txt1ApellidoClienteKeyTyped

    private void txtNombreClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyTyped
        String textoActual = txtNombreCliente.getText();

        char caracterIngresado = evt.getKeyChar();

        if (!Character.isLetter(caracterIngresado) && caracterIngresado != KeyEvent.VK_BACK_SPACE && caracterIngresado != KeyEvent.VK_DELETE) {

            JOptionPane.showMessageDialog(this, "Solo se permiten letras en este campo.", "Advertencia", JOptionPane.WARNING_MESSAGE);

            evt.consume();
        }
    }//GEN-LAST:event_txtNombreClienteKeyTyped

    private void txtCedulaClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaClienteKeyTyped
        char caracter = evt.getKeyChar();

        if (Character.isLetter(caracter)) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Error en Cédula: Ingrese solo números.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtCedulaClienteKeyTyped

    private void txtCedulaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaClienteActionPerformed
        String cedula = txtCedulaCliente.getText();
        boolean esValida = validarCedula(cedula);

        if (esValida) {
            System.out.println("Cédula válida");
        } else {
            System.out.println("Cédula no válida");
        }
    }//GEN-LAST:event_txtCedulaClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPLinea;
    private javax.swing.JTabbedPane JTabMain;
    private javax.swing.JPanel JpClientes;
    private javax.swing.JPanel JpInicio;
    private javax.swing.JPanel JpProductos;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnEditarCliente;
    private javax.swing.JButton btnEditarProducto;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnGuardarCliente;
    private javax.swing.JButton btnGuardarProducto;
    private javax.swing.JComboBox<String> comboCategorias;
    private javax.swing.JComboBox<String> comboSubcategorias;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblImajen1;
    private javax.swing.JLabel lblImajen2;
    private javax.swing.JLabel lblImajen3;
    private javax.swing.JLabel lblImajen4;
    private javax.swing.JLabel lblImajen5;
    private javax.swing.JLabel lblImajen6;
    private javax.swing.JLabel lblImejenInicio;
    private javax.swing.JLabel lblNombreEmpresa;
    private javax.swing.JScrollPane scrollClientes;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTable tblProducto;
    private javax.swing.JTextField txt1ApellidoCliente;
    private javax.swing.JTextField txt2ApellidoCliente;
    private javax.swing.JTextField txtCedulaCliente;
    private javax.swing.JTextField txtCorreoCliente;
    private javax.swing.JTextField txtIDCategoriaProducto;
    private javax.swing.JTextField txtIDMarcaProducto;
    private javax.swing.JTextField txtIDProveedorProducto;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtPesoProducto;
    private javax.swing.JTextField txtPrecioProducto;
    private javax.swing.JTextField txtTelefonoCliente;
    // End of variables declaration//GEN-END:variables

    private boolean validarCedula(String cedula) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
