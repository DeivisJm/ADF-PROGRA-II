package GUI;

import MODEL.Producto;

public class ProductoPanel extends javax.swing.JPanel {

    private Producto producto;
    private int cantidad;
    private double precioTotal;
    private Usuario parent;
    
    /*Here we are set the text in the lbls of products and getting the name and more valvues */
    public ProductoPanel(Producto producto, Usuario parent) {
        try {
            initComponents();
            this.producto = producto;
            lblNombre.setText(producto.getNombre());
            lblPeso.setText(producto.getPeso());
            lblPrecio.setText("₡ " + producto.getPrecio());
            lblImgen.setIcon(new javax.swing.ImageIcon(getClass().getResource(producto.getImagen())));

        } catch (Exception e) {
            System.out.println(producto.getImagen());
        }
        this.parent = parent;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        lblImgen = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblPeso = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        btnComprar = new javax.swing.JButton();
        spnCantidad = new javax.swing.JSpinner();

        setBackground(new java.awt.Color(255, 255, 255));

        lblImgen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Bebidas/7up 3000ml.jpg"))); // NOI18N

        lblNombre.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblNombre.setText("Nombre");

        lblPeso.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblPeso.setText("Peso");

        lblPrecio.setBackground(new java.awt.Color(0, 0, 0));
        lblPrecio.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblPrecio.setText("Precio");

        btnComprar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/Carrito.png"))); // NOI18N
        btnComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarActionPerformed(evt);
            }
        });

        spnCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spnCantidad.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        spnCantidad.setMaximumSize(new java.awt.Dimension(40, 35));
        spnCantidad.setMinimumSize(new java.awt.Dimension(40, 45));
        spnCantidad.setPreferredSize(new java.awt.Dimension(40, 35));
        spnCantidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnCantidadStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre)
                            .addComponent(lblImgen)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImgen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarActionPerformed
        if (parent != null) {
            cantidad = (int) spnCantidad.getValue();
            precioTotal = producto.getPrecio() * cantidad;
            parent.agregarProducto(producto, cantidad, precioTotal);
        }
            parent.actualizarSubtotal();
    }//GEN-LAST:event_btnComprarActionPerformed

    private void spnCantidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnCantidadStateChanged
        cantidad = (int) spnCantidad.getValue();
        precioTotal = producto.getPrecio() * cantidad;
        parent.actualizarSubtotal();
    }//GEN-LAST:event_spnCantidadStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComprar;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JLabel lblImgen;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPeso;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JSpinner spnCantidad;
    // End of variables declaration//GEN-END:variables
}
