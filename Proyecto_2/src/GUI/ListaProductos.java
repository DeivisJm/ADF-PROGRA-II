
package GUI;

import MODEL.Producto;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JPanel;


public class ListaProductos extends javax.swing.JPanel {
    //Create variable type list of product
    private List<Producto> productos;
    private Usuario parent;
    
    public ListaProductos(List<Producto> productos, Usuario parent) {
        initComponents();
        this.productos = productos;
        this.parent = parent;
        this.imprimirProductos();
        
    }
    //Method for print the panels with a for to get a index and then we do a instance of ProductoPanel
    private void imprimirProductos(){
        JPanel panel;
        setLayout(new GridLayout(0, 3, 8, 8));
        
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            panel = new ProductoPanel(producto, parent);
            add(panel);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 258, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
