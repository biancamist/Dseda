package Vista;

import java.awt.Image;
import java.awt.Toolkit;

public class ventanaPrincipal extends javax.swing.JFrame 
{
    public ventanaPrincipal() 
    {
        initComponents();
        
        this.setLocationRelativeTo(ventanaPrincipal.this);
        this.setResizable(false);
        
        Image ico = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Recursos/z.jpg"));
        this.setIconImage(ico);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        barraMenuPpal = new javax.swing.JMenuBar();
        menuProducto = new javax.swing.JMenu();
        itemMenuAdminProd = new javax.swing.JMenuItem();
        menuProveedor = new javax.swing.JMenu();
        itemMenuCargarProv = new javax.swing.JMenuItem();
        menuCliente = new javax.swing.JMenu();
        menuHistorial = new javax.swing.JMenu();
        menuAyuda = new javax.swing.JMenu();

        jMenu4.setText("File");
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Edit");
        jMenuBar1.add(jMenu5);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dseda");

        menuProducto.setText("Producto");

        itemMenuAdminProd.setText("Administrar");
        itemMenuAdminProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuAdminProdActionPerformed(evt);
            }
        });
        menuProducto.add(itemMenuAdminProd);

        barraMenuPpal.add(menuProducto);

        menuProveedor.setText("Proveedor");

        itemMenuCargarProv.setText("Cargar proveedor");
        menuProveedor.add(itemMenuCargarProv);

        barraMenuPpal.add(menuProveedor);

        menuCliente.setText("Cliente");
        barraMenuPpal.add(menuCliente);

        menuHistorial.setText("Historial");
        barraMenuPpal.add(menuHistorial);

        menuAyuda.setText("Ayuda");
        barraMenuPpal.add(menuAyuda);

        setJMenuBar(barraMenuPpal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 599, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemMenuAdminProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuAdminProdActionPerformed
        ventanaProducto vProd = new ventanaProducto();
        vProd.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_itemMenuAdminProdActionPerformed

    public static void main(String args[]) 
    {        
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                //new ventanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenuPpal;
    private javax.swing.JMenuItem itemMenuAdminProd;
    private javax.swing.JMenuItem itemMenuCargarProv;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menuAyuda;
    private javax.swing.JMenu menuCliente;
    private javax.swing.JMenu menuHistorial;
    private javax.swing.JMenu menuProducto;
    private javax.swing.JMenu menuProveedor;
    // End of variables declaration//GEN-END:variables
}
