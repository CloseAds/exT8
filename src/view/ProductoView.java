package view;

import model.entities.Producto;
import model.services.ProductoService;
import model.services.ProductoServiceImpl;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;



public class ProductoView extends JPanel{

    ProductoService service;
    JTable listadoTable;
    private JPanel formPanel;
    JTextField _codigo_producto;
    JTextField _descripcion;
    JTextField _precio;
    JTextField _stock;
    JButton addButton;
    JButton updateButton;
    JButton deleteButton;

    public ProductoView() {

        service = new ProductoServiceImpl();

        this.setLayout(new GridLayout(2,1));

        listadoTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(listadoTable);
        this.add(scrollPane, BorderLayout.CENTER);

        crearFormulario();

        this.add(formPanel, BorderLayout.SOUTH);
        formPanel.setVisible(true);

        listadoTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = listadoTable.getSelectedRow();
                if (selectedRow >= 0) {
                    showProductoForm(selectedRow);
                }
            }
        });

        showProducto();
        this.setVisible(true);
    }

    private void crearFormulario() {
        // Crear formulario
        formPanel = new JPanel(new GridLayout(5, 2));

        formPanel.setBorder(BorderFactory.createTitledBorder("Detalles del Producto"));
        formPanel.add(new JLabel("Código del producto:"));
        _codigo_producto = new JTextField();
        formPanel.add(_codigo_producto);

        formPanel.add(new JLabel("Descripcion:"));
        _descripcion = new JTextField();
        formPanel.add(_descripcion);

        formPanel.add(new JLabel("Precio:"));
        _precio = new JTextField();
        formPanel.add(_precio);

        formPanel.add(new JLabel("Stock disponible:"));
        _stock = new JTextField();
        formPanel.add(_stock);

        addButton = new JButton("Nuevo");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProducto();
            }
        });
        formPanel.add(addButton);

        updateButton = new JButton("Actualizar");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProducto();
            }
        });
        formPanel.add(updateButton);

        deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCliente();
            }
        });
        formPanel.add(deleteButton);

    }

    private void showProducto() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Código del producto");
        model.addColumn("Descripción");
        model.addColumn("Precio");
        model.addColumn("Stock");

        List<Producto> productos = service.getList();

        for (Producto producto : productos) {
            model.addRow(new Object[]{
                    producto.getCodigo_producto(),
                    producto.getDescripcion(),
                    producto.getPrecio(),
                    producto.getStock()
            });
        }

        listadoTable.setModel(model);
        formPanel.setVisible(true);
    }

    private void showProductoForm(int rowIndex) {
        DefaultTableModel model = (DefaultTableModel) listadoTable.getModel();
        String codigo_producto = (String) model.getValueAt(rowIndex, 0);

        Producto producto = service.getById(codigo_producto);

        _codigo_producto.setText(producto.getCodigo_producto());
        _descripcion.setText(producto.getDescripcion());
        _precio.setText(producto.getPrecio());
        _stock.setText(producto.getStock());
    }

    private void addProducto() {
        String codigo_producto = _codigo_producto.getText();
        String descripcion = _descripcion.getText();
        String precio = _precio.getText();
        String stock = _stock.getText();

        Producto producto = new Producto();

        producto.setCodigo_producto(Integer.parseInt(codigo_producto));
        producto.setDescripcion(descripcion);
        producto.setPrecio(Double.parseDouble(precio));
        producto.setStock(Integer.parseInt(stock));

        DefaultTableModel model = (DefaultTableModel) listadoTable.getModel();

        service.save(producto);

        model.addRow(new Object[]{
                producto.getCodigo_producto(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock()
        });

        clearForm();
    }

    private void updateProducto() {

        String codigo_producto = _codigo_producto.getText();
        String descripcion = _descripcion.getText();
        String precio = _precio.getText();
        String stock = _stock.getText();

        Producto producto = new Producto();

        producto.setCodigo_producto(Integer.parseInt(codigo_producto));
        producto.setDescripcion(descripcion);
        producto.setPrecio(Double.parseDouble(precio));
        producto.setStock(Integer.parseInt(stock));

        DefaultTableModel model = (DefaultTableModel) listadoTable.getModel();

        service.update(producto);
        showProducto();

    }

    private void deleteCliente() {
        String codigo_producto = _codigo_producto.getText();
        Producto productos = new Producto();
        productos.setCodigo_producto(Integer.parseInt(codigo_producto));
        service.delete(productos);
        showProducto();
    }

    private void clearForm() {
        _codigo_producto.setText("");
        _descripcion.setText("");
        _precio.setText("");
        _stock.setText("");
    }
}