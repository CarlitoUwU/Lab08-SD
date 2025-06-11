package com.sd.lab8sd.ui;


import com.sd.lab8sd.dao.IngenieroDAO;
import com.sd.lab8sd.model.Ingeniero;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class IngenieroPanel extends Panel{
    private final IngenieroDAO iDao = new IngenieroDAO();

    public IngenieroPanel(){
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "ID Dpto", "Nombre", "Apellido", "Especialidad", "Cargo"}, 0);
        JTable table = new JTable(model);
        refreshIngenieros(model);

        JPanel form = new JPanel(new GridLayout(0, 2, 5, 5));
        JTextField id = new JTextField(), idDpto = new JTextField(), nombre = new JTextField(), apellido = new JTextField(), especialidad = new JTextField(), cargo = new JTextField();
        form.add(new JLabel("ID (para actualizar/eliminar):"));
        form.add(id);
        form.add(new JLabel("ID Departamento:"));
        form.add(idDpto);
        form.add(new JLabel("Nombre:"));
        form.add(nombre);
        form.add(new JLabel("Apellido:"));
        form.add(apellido);
        form.add(new JLabel("Especialidad:"));
        form.add(especialidad);
        form.add(new JLabel("Cargo:"));
        form.add(cargo);

        JPanel btns = new JPanel();
        JButton insertar = new JButton("Insertar"), actualizar = new JButton("Actualizar"), eliminar = new JButton("Eliminar"), refrescar = new JButton("Refrescar");
        btns.add(insertar);
        btns.add(actualizar);
        btns.add(eliminar);
        btns.add(refrescar);

        insertar.addActionListener(e -> {
            try {
                Ingeniero i = new Ingeniero(0, Integer.parseInt(idDpto.getText()), nombre.getText(), apellido.getText(), especialidad.getText(), cargo.getText());
                if (iDao.insertar(i)) {
                    JOptionPane.showMessageDialog(this, "Insertado con ID " + i.getIdIng());
                    refreshIngenieros(model);
                }
            } catch (Exception ex) {
                showError(ex);
            }
        });
        actualizar.addActionListener(e -> {
            try {
                Ingeniero i = new Ingeniero(Integer.parseInt(id.getText()), Integer.parseInt(idDpto.getText()), nombre.getText(), apellido.getText(), especialidad.getText(), cargo.getText());
                if (iDao.actualizar(i)) {
                    JOptionPane.showMessageDialog(this, "Actualizado correctamente");
                    refreshIngenieros(model);
                }
            } catch (Exception ex) {
                showError(ex);
            }
        });
        eliminar.addActionListener(e -> {
            try {
                if (iDao.eliminar(Integer.parseInt(id.getText()))) {
                    JOptionPane.showMessageDialog(this, "Eliminado correctamente");
                    refreshIngenieros(model);
                }
            } catch (Exception ex) {
                showError(ex);
            }
        });
        refrescar.addActionListener(e -> refreshIngenieros(model));

        this.add(new JScrollPane(table), BorderLayout.CENTER);
        this.add(form, BorderLayout.NORTH);
        this.add(btns, BorderLayout.SOUTH);
    }

    private void refreshIngenieros(DefaultTableModel model) {
        try {
            model.setRowCount(0);
            for (Ingeniero i : iDao.listar()) {
                model.addRow(new Object[]{i.getIdIng(), i.getIdDpto(), i.getNombre(), i.getApellido(), i.getEspecialidad(), i.getCargo()});
            }
        } catch (Exception ex) {
            showError(ex);
        }
    }
}
