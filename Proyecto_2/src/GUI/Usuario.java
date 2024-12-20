package GUI;

import ENTITY.Producto;
import ENTITY.Facturacion;
import ENTITY.Compra;
import ENTITY.Datos;
import ENTITY.DetalleCompra;
import BO.*;
import DAO.DB;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.json.simple.*;
import org.json.simple.parser.*;

/**
 *
 * @author deivi
 */
public class Usuario extends javax.swing.JFrame {

    private FacturacionBO facturacionBO;
    private ProductoBO productoBO;
    private DetalleCompra detallecompra = new DetalleCompra(this);
    private Compra compra = new Compra(this);
    private Datos cliente = new Datos(this);
    private Login login;
    private double subtotal = 0.0;

    public Usuario(Login login) {
        initComponents();
        this.login = login;
        comboCategorias.addItem("Elegir...");
        comboCategorias.addItem("Bebidas");
        comboCategorias.addItem("Abarrotes");
        comboCategorias.addItem("Limpieza");
        comboCategorias.addItem("Cuidado Personal");
        comboCategorias.addItem("Mascotas");
        comboCategorias.addItem("Frescos");
        comboSubcategorias.addItem("Elegir...");
        Facturacion facturacion = new Facturacion(); // Facturacion tenga un constructor sin argumentos 
        Producto producto = new Producto();
        this.facturacionBO = new FacturacionBO(facturacion);
        this.productoBO = new ProductoBO();

    }


    /*Método para actualizar el subtotal txt para mostrar la suma total en el txt.*/
    public void actualizarSubtotal() {
        double subtotal = 0.0;

        for (int row = 0; row < tblCarrito.getRowCount(); row++) {
            double precio = (double) tblCarrito.getValueAt(row, tblCarrito.getColumnModel().getColumnIndex("Precio"));
            subtotal += precio;
        }

        txtSubtotal.setText(String.valueOf(subtotal));
    }

    /*Método para actualizar el subtotal txt para mostrar la resta total en el txt.*/
    private void actualizarSubtotalResta() {
        double nuevoSubtotal = 0.0;

        for (int row = 0; row < tblCarrito.getRowCount(); row++) {
            double precio = (double) tblCarrito.getValueAt(row, tblCarrito.getColumnModel().getColumnIndex("Precio"));
            nuevoSubtotal += precio;
        }

        subtotal = nuevoSubtotal;
        txtSubtotal.setText(String.valueOf(subtotal));
    }

    //Eliminar producto del carrito
    public void eliminarProducto(int fila) {
        double precio = (double) tblCarrito.getValueAt(fila, tblCarrito.getColumnModel().getColumnIndex("Precio"));
        subtotal -= precio;
        actualizarSubtotal();
    }

    /*Aquí comprobamos si el producto existe y si existe mediante un if el método obtiene el valor de la fila y la columna y luego inserta el nuevo valor. */
    public void agregarProductoAlCarrito(Producto producto, int cantidad, double precioTotal) {
        DefaultTableModel model = (DefaultTableModel) tblCarrito.getModel();

        // Llamar al método en ProductoBO que maneja la lógica de agregar al carrito
        try {
            int row = productoBO.verificarProductoExistente(model, producto.getNombre());
            if (row != -1) {
                // Actualizar cantidad y precio total si el producto ya existe en el carrito
                int cantidadExistente = (int) model.getValueAt(row, 2);
                double precioTotalExistente = (double) model.getValueAt(row, 3);
                model.setValueAt(cantidadExistente + cantidad, row, 2);
                model.setValueAt(precioTotalExistente + precioTotal, row, 3);
            } else {
                // Agregar nuevo producto al carrito
                model.addRow(new Object[]{producto.getNombre(), producto.getPeso(), cantidad, precioTotal});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lo sentimos, hay un error al agregar el producto al carrito", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /*Llamamos a facturacionBO para ejecutralo*/
    public void facturarCarrito() {
        try {
            facturacionBO.Facturar(tblCarrito, txtFactura);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al facturar.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    //Se obtiene la fecha de hoy en dia con el date
    private String obtenerFechaActual() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    //eliminamos todas las subcategorías, luego cargamos todas las categorías dentro del switch y dentro de las categorías, están las subcategorías que tiene cada uno
    private void actualizarComboSubcategorias(String categoria) {
        comboSubcategorias.removeAllItems();
        comboSubcategorias.addItem("Elegir...");
        switch (categoria) {
            case "Bebidas":
                agregarSubcategoriaItems("Cervezas", "Licores", "Vinos", "Jugos", "Gaseosas", "Energizantes");
                break;
            case "Abarrotes":
                agregarSubcategoriaItems("Granos y Pastas", "Aceites_Grasas", "Botanas", "Enlatados");
                break;
            case "Limpieza":
                agregarSubcategoriaItems("Detergentes", "Lava platos", "Desifectante y limpiadores", "Accesorios");
                break;
            case "Mascotas":
                agregarSubcategoriaItems("Alimentos_Mascotas", "Accesorios_Mascotas", "Limpieza_Mascotas");
                break;
            case "Cuidado Personal":
                agregarSubcategoriaItems("Cuidado Bucal", "Cuidado capilar", "Cuidado de piel", "Cuidado femenino", "Desodorantes");
                break;
            case "Frescos":
                agregarSubcategoriaItems("Pollo", "Cerdo", "Res", "Mariscos", "Frutas", "Verduras", "Embutidos");
                break;
            default:
                break;
        }
    }

    //Agrega subcategorias
    private void agregarSubcategoriaItems(String... items) {
        for (String item : items) {
            comboSubcategorias.addItem(item);
        }
    }

    //Aquí cargamos los productos
    private void cargarProductos(int idSubcategoria) {
        Datos datos = new Datos();
        List<Producto> productos = datos.obtenerProductosPorSubcategoria(idSubcategoria);

        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos para la subcategoría seleccionada.");
        } else {
            // Crear el panel de productos y mostrarlo en el JScrollPane
            ListaProductos listaProductos = new ListaProductos(productos, this);
            scrollProductos.setViewportView(listaProductos);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JTabMain = new javax.swing.JTabbedPane();
        JpInicio = new javax.swing.JPanel();
        JPLinea = new javax.swing.JPanel();
        lblNombreEmpresa = new javax.swing.JLabel();
        lblImejenInicio = new javax.swing.JLabel();
        JpCategorias = new javax.swing.JPanel();
        JpCategorias1 = new javax.swing.JPanel();
        JpCarrito1 = new javax.swing.JPanel();
        btnEliminarCarrito1 = new javax.swing.JButton();
        scrollCarrito = new javax.swing.JScrollPane();
        tblCarrito = new javax.swing.JTable();
        btnFinalizar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnGuardarCompra = new javax.swing.JButton();
        comboCategorias = new javax.swing.JComboBox<>();
        scrollProductos = new javax.swing.JScrollPane();
        jPProductos = new javax.swing.JPanel();
        comboSubcategorias = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtFactura = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JTabMain.setBackground(new java.awt.Color(153, 204, 255));
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

        JpInicio.add(JPLinea, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 300, 730, 10));

        lblNombreEmpresa.setBackground(new java.awt.Color(0, 0, 0));
        lblNombreEmpresa.setFont(new java.awt.Font("Segoe UI Emoji", 0, 48)); // NOI18N
        lblNombreEmpresa.setText("Bienvenidos a ADF VIRTUAL STORE");
        lblNombreEmpresa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreEmpresaMouseClicked(evt);
            }
        });
        JpInicio.add(lblNombreEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, 750, -1));

        lblImejenInicio.setForeground(new java.awt.Color(0, 0, 153));
        lblImejenInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/Retail-de-supermercado.png"))); // NOI18N
        JpInicio.add(lblImejenInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(-140, -50, 1800, 810));

        JTabMain.addTab("Inicio", JpInicio);

        JpCategorias.setBackground(new java.awt.Color(0, 0, 0));

        JpCategorias1.setBackground(new java.awt.Color(255, 255, 255));
        JpCategorias1.setPreferredSize(new java.awt.Dimension(1630, 810));

        JpCarrito1.setBackground(new java.awt.Color(255, 255, 255));
        JpCarrito1.setBorder(javax.swing.BorderFactory.createTitledBorder("CARRITO"));
        JpCarrito1.setPreferredSize(new java.awt.Dimension(500, 804));

        btnEliminarCarrito1.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarCarrito1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/basurero.jpg"))); // NOI18N
        btnEliminarCarrito1.setText("Eliminar");
        btnEliminarCarrito1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEliminarCarrito1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCarrito1ActionPerformed(evt);
            }
        });

        tblCarrito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Peso", "Cantidad", "Precio"
            }
        ));
        scrollCarrito.setViewportView(tblCarrito);

        btnFinalizar.setForeground(new java.awt.Color(255, 255, 255));
        btnFinalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/finalizar.png"))); // NOI18N
        btnFinalizar.setText("Finalizar Compra");
        btnFinalizar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel3.setText("Subtotal: ");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtSubtotal.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Eliminar");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Guardar");

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Finalizar");

        jLabel7.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel7.setText("Subtotal:");

        btnGuardarCompra.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/guardar.jpg"))); // NOI18N
        btnGuardarCompra.setText("Guardar Compra");
        btnGuardarCompra.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGuardarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCompraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JpCarrito1Layout = new javax.swing.GroupLayout(JpCarrito1);
        JpCarrito1.setLayout(JpCarrito1Layout);
        JpCarrito1Layout.setHorizontalGroup(
            JpCarrito1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpCarrito1Layout.createSequentialGroup()
                .addGroup(JpCarrito1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpCarrito1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(JpCarrito1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                            .addComponent(btnEliminarCarrito1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addGap(35, 35, 35)
                        .addGroup(JpCarrito1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(JpCarrito1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                            .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addGroup(JpCarrito1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JpCarrito1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addGap(222, 222, 222)
                                .addComponent(jLabel3))
                            .addGroup(JpCarrito1Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(JpCarrito1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollCarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JpCarrito1Layout.setVerticalGroup(
            JpCarrito1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpCarrito1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollCarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(JpCarrito1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpCarrito1Layout.createSequentialGroup()
                        .addGroup(JpCarrito1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpCarrito1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(JpCarrito1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminarCarrito1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JpCarrito1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(17, 17, 17))))
        );

        comboCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCategoriasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPProductosLayout = new javax.swing.GroupLayout(jPProductos);
        jPProductos.setLayout(jPProductosLayout);
        jPProductosLayout.setHorizontalGroup(
            jPProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1536, Short.MAX_VALUE)
        );
        jPProductosLayout.setVerticalGroup(
            jPProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 795, Short.MAX_VALUE)
        );

        scrollProductos.setViewportView(jPProductos);

        comboSubcategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSubcategoriasActionPerformed(evt);
            }
        });

        txtFactura.setColumns(20);
        txtFactura.setRows(5);
        jScrollPane1.setViewportView(txtFactura);

        jLabel1.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel1.setText("Sub Categorias");

        jLabel2.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel2.setText("Categorias");

        btnCerrarSesion.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/Logout_37127.png"))); // NOI18N
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel8.setText("Cerrar Sesion");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/logoInicio.jpg"))); // NOI18N

        javax.swing.GroupLayout JpCategorias1Layout = new javax.swing.GroupLayout(JpCategorias1);
        JpCategorias1.setLayout(JpCategorias1Layout);
        JpCategorias1Layout.setHorizontalGroup(
            JpCategorias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpCategorias1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(JpCategorias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JpCategorias1Layout.createSequentialGroup()
                        .addComponent(scrollProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 1047, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JpCategorias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JpCarrito1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1))
                        .addGap(0, 126, Short.MAX_VALUE))
                    .addGroup(JpCategorias1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboSubcategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addGap(526, 526, 526)
                        .addComponent(jLabel9)
                        .addGap(344, 344, 344))))
        );
        JpCategorias1Layout.setVerticalGroup(
            JpCategorias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpCategorias1Layout.createSequentialGroup()
                .addGroup(JpCategorias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpCategorias1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(JpCategorias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboSubcategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(btnCerrarSesion)))
                    .addGroup(JpCategorias1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8))
                    .addGroup(JpCategorias1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpCategorias1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpCategorias1Layout.createSequentialGroup()
                        .addComponent(JpCarrito1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(scrollProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout JpCategoriasLayout = new javax.swing.GroupLayout(JpCategorias);
        JpCategorias.setLayout(JpCategoriasLayout);
        JpCategoriasLayout.setHorizontalGroup(
            JpCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpCategoriasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JpCategorias1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        JpCategoriasLayout.setVerticalGroup(
            JpCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpCategoriasLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(JpCategorias1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        JTabMain.addTab("Categorias", JpCategorias);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JTabMain, javax.swing.GroupLayout.PREFERRED_SIZE, 1552, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JTabMain, javax.swing.GroupLayout.PREFERRED_SIZE, 841, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//Podemos elegir las Categorías con getSelectedItem
    private void comboCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCategoriasActionPerformed
        String categoriaSeleccionada = (String) comboCategorias.getSelectedItem();
        Datos datos = new Datos();
        int idCategoriaSeleccionada = datos.obtenerIdCategoria(categoriaSeleccionada);

        if (idCategoriaSeleccionada != -1) {
            // Cargar subcategorías en el comboSubcategorias basadas en la categoría seleccionada
            List<String> subcategorias = datos.obtenerSubcategoriasPorCategoria(categoriaSeleccionada);
            comboSubcategorias.removeAllItems();
            for (String subcategoria : subcategorias) {
                comboSubcategorias.addItem(subcategoria);
            }
        } else {
            System.out.println("Categoría no encontrada.");
        }
    }//GEN-LAST:event_comboCategoriasActionPerformed
    ////Podemos elegir las subcategorías con getSelectedItem
    private void comboSubcategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSubcategoriasActionPerformed
        String subcategoriaSeleccionada = (String) comboSubcategorias.getSelectedItem();
        Datos datos = new Datos();
        int idSubcategoriaSeleccionada = datos.obtenerIdSubcategoria(subcategoriaSeleccionada);

        if (idSubcategoriaSeleccionada != -1) {
            cargarProductos(idSubcategoriaSeleccionada); // Llama al método para cargar los productos de la subcategoría seleccionada
        } else {
            System.out.println("Subcategoría no encontrada.");
        }
    }//GEN-LAST:event_comboSubcategoriasActionPerformed

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        this.facturarCarrito();
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void btnEliminarCarrito1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCarrito1ActionPerformed
        int filaSeleccionada = tblCarrito.getSelectedRow();
        if (filaSeleccionada >= 0) {
            // Llamar al metodo eliminarFila desde FacturacionBO
            facturacionBO.eliminarFila(tblCarrito);

            // Actualizar la lógica interna y el subtotal después de eliminar la fila
            eliminarProducto(filaSeleccionada);
            actualizarSubtotalResta();

            JOptionPane.showMessageDialog(this, "Producto eliminado del carrito");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un producto en el carrito para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarCarrito1ActionPerformed

    private void btnGuardarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCompraActionPerformed
        //Obtiene categorias
        String subCategoria = (String) comboSubcategorias.getSelectedItem();
        DefaultTableModel modeloTablaCarrito = (DefaultTableModel) tblCarrito.getModel();
        int rowCount = modeloTablaCarrito.getRowCount(); //Se cuenta las filas para los productos anadidos

        //Instancias de los BO
        IDManagerBO idManagerBO = new IDManagerBO();
        CompraBO compraBO = new CompraBO();
        DetalleCompraBO detalleCompraBO = new DetalleCompraBO();
        ClienteBO clienteBO = new ClienteBO();

        int nuevoIdCompras = idManagerBO.generarID("compra.json") + 1;
        double nuevoMontoTotal = 0.0;
        int idCliente = -1;

        String nombreCliente = login.getNombreUsuario();

        try {
            idCliente = clienteBO.obtenerIdClientePorNombre(nombreCliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Verificar si se obtuvo el ID del cliente
        if (idCliente != -1) {
            for (int i = 0; i < rowCount; i++) {
                int nuevoIdDetalleCompra = idManagerBO.generarID("DetalleCompra.json") + 1;
                int nuevaCantidad = (int) modeloTablaCarrito.getValueAt(i, 2);
                double nuevoMonto = (double) modeloTablaCarrito.getValueAt(i, 3);
                String nombreProducto = (String) modeloTablaCarrito.getValueAt(i, 0);

                String nuevoIdProducto = null;
                try {
                    nuevoIdProducto = compraBO.obtenerIdProductoPorNombreYSubcategoria(nombreProducto, subCategoria);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Si se obtuvo el ID del producto, crear y guardar el detalle de compra
                if (nuevoIdProducto != null) {
                    DetalleCompra nuevoDetalle = new DetalleCompra(nuevoIdDetalleCompra, nuevaCantidad, nuevoMonto, nuevoIdProducto, nuevoIdCompras);
                    try {
                        detalleCompraBO.guardarDetalleCompra(nuevoDetalle);
                        nuevoMontoTotal += nuevoMonto;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            String fechaActual = obtenerFechaActual();
            Compra nuevaCompra = new Compra(nuevoIdCompras, fechaActual, nuevoMontoTotal, idCliente, nuevoIdCompras);

            try {
                //Guardar, actulixar 
                compraBO.guardarCompra(nuevaCompra);
                compraBO.actualizarTabla((DefaultTableModel) tblCarrito.getModel());
                //limpiar campos
                modeloTablaCarrito.getDataVector().removeAllElements();
                modeloTablaCarrito.fireTableDataChanged();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al guardar la compra: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }

        } else {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado");
        }

    }//GEN-LAST:event_btnGuardarCompraActionPerformed

    private void lblNombreEmpresaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreEmpresaMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_lblNombreEmpresaMouseClicked

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        Login log = new Login();
        log.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPLinea;
    private javax.swing.JTabbedPane JTabMain;
    private javax.swing.JPanel JpCarrito1;
    private javax.swing.JPanel JpCategorias;
    private javax.swing.JPanel JpCategorias1;
    private javax.swing.JPanel JpInicio;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnEliminarCarrito1;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnGuardarCompra;
    private javax.swing.JComboBox<String> comboCategorias;
    private javax.swing.JComboBox<String> comboSubcategorias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPProductos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImejenInicio;
    private javax.swing.JLabel lblNombreEmpresa;
    private javax.swing.JScrollPane scrollCarrito;
    private javax.swing.JScrollPane scrollProductos;
    private javax.swing.JTable tblCarrito;
    private javax.swing.JTextArea txtFactura;
    private javax.swing.JTextField txtSubtotal;
    // End of variables declaration//GEN-END:variables
}
