package com.sd.lab8sd.ui;

import com.sd.lab8sd.dao.AsignacionDAO;
import com.sd.lab8sd.model.Asignacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AsignacionPanel extends Panel{

    private final AsignacionDAO aDao = new AsignacionDAO();

    public AsignacionPanel() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "ID Ingeniero", "ID Proyecto", "Rol"}, 0);
        JTable table = new JTable(model);
        refreshAsignaciones(model);

        JPanel form = new JPanel(new GridLayout(0, 2, 5, 5));
        JTextField id = new JTextField(), idIng = new JTextField(), idProy = new JTextField(), rol = new JTextField();
        form.add(new JLabel("ID (para actualizar/eliminar):"));
        form.add(id);
        form.add(new JLabel("ID Ingeniero:"));
        form.add(idIng);
        form.add(new JLabel("ID Proyecto:"));
        form.add(idProy);
        form.add(new JLabel("Rol en el Proyecto:"));
        form.add(rol);

        JPanel btns = new JPanel();
        JButton insertar = new JButton("Insertar"), actualizar = new JButton("Actualizar"), eliminar = new JButton("Eliminar"), refrescar = new JButton("Refrescar");
        btns.add(insertar);
        btns.add(actualizar);
        btns.add(eliminar);
        btns.add(refrescar);

        insertar.addActionListener(e -> {
            try {
                Asignacion a = new Asignacion(0, Integer.parseInt(idIng.getText()), Integer.parseInt(idProy.getText()), rol.getText());
                if (aDao.insertar(a)) {
                    JOptionPane.showMessageDialog(this, "Asignación insertada con ID " + a.getIdAsignacion());
                    refreshAsignaciones(model);
                }
            } catch (Exception ex) {
                showError(ex);
            }
        });
        actualizar.addActionListener(e -> {
            try {
                Asignacion a = new Asignacion(Integer.parseInt(id.getText()), Integer.parseInt(idIng.getText()), Integer.parseInt(idProy.getText()), rol.getText());
                if (aDao.actualizar(a)) {
                    JOptionPane.showMessageDialog(this, "Asignación actualizada correctamente");
                    refreshAsignaciones(model);
                }
            } catch (Exception ex) {
                showError(ex);
            }
        });
        eliminar.addActionListener(e -> {
            try {
                if (aDao.eliminar(Integer.parseInt(id.getText()))) {
                    JOptionPane.showMessageDialog(this, "Asignación eliminada correctamente");
                    refreshAsignaciones(model);
                }
            } catch (Exception ex) {
                showError(ex);
            }
        });
        refrescar.addActionListener(e -> refreshAsignaciones(model));

        this.add(new JScrollPane(table), BorderLayout.CENTER);
        this.add(form, BorderLayout.NORTH);
        this.add(btns, BorderLayout.SOUTH);
    }

    private void refreshAsignaciones(DefaultTableModel model) {
        try {
            model.setRowCount(0);
            for (Asignacion a : aDao.listar()) {
                model.addRow(new Object[]{a.getIdAsignacion(), a.getIdIng(), a.getIdProy(), a.getRolProyecto()});
            }
        } catch (Exception ex) {
            showError(ex);
        }
    }

}
