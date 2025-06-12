package com.sd.lab8sd.ui;

import com.sd.lab8sd.dao.DepartamentoDAO;
import com.sd.lab8sd.model.Departamento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DepartamentoPanel extends Panel {

    private final DepartamentoDAO dDao = new DepartamentoDAO();

    public DepartamentoPanel() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre", "Teléfono", "Fax"}, 0);
        JTable table = new JTable(model);
        refreshDepartamentos(model);

        JPanel form = new JPanel(new GridLayout(0, 2, 5, 5));
        JTextField id = new JTextField(), nombre = new JTextField(), tel = new JTextField(), fax = new JTextField();
        form.add(new JLabel("ID (para actualizar/eliminar):"));
        form.add(id);
        form.add(new JLabel("Nombre:"));
        form.add(nombre);
        form.add(new JLabel("Teléfono:"));
        form.add(tel);
        form.add(new JLabel("Fax:"));
        form.add(fax);

        JPanel btns = new JPanel();
        JButton insertar = new JButton("Insertar"), actualizar = new JButton("Actualizar"), eliminar = new JButton("Eliminar"), refrescar = new JButton("Refrescar");
        btns.add(insertar);
        btns.add(actualizar);
        btns.add(eliminar);
        btns.add(refrescar);

        insertar.addActionListener(e -> {
            try {
                Departamento d = new Departamento(0, nombre.getText(), tel.getText(), fax.getText());
                if (dDao.insertar(d)) {
                    JOptionPane.showMessageDialog(this, "Insertado con ID " + d.getId());
                    refreshDepartamentos(model);
                }

                limpiarInputs();

            } catch (Exception ex) {
                showError(ex);
            }
        });
        actualizar.addActionListener(e -> {
            try {
                Departamento d = new Departamento(Integer.parseInt(id.getText()), nombre.getText(), tel.getText(), fax.getText());
                if (dDao.actualizar(d)) {
                    JOptionPane.showMessageDialog(this, "Actualizado correctamente");
                    refreshDepartamentos(model);
                }

                limpiarInputs();

            } catch (Exception ex) {
                showError(ex);
            }
        });
        eliminar.addActionListener(e -> {
            try {
                if (DepartamentoDAO.eliminar(Integer.parseInt(id.getText()))) {
                    JOptionPane.showMessageDialog(this, "Eliminado correctamente");
                    refreshDepartamentos(model);
                }

                limpiarInputs();

            } catch (Exception ex) {
                showError(ex);
            }
        });
        refrescar.addActionListener(e -> refreshDepartamentos(model));

        this.add(new JScrollPane(table), BorderLayout.CENTER);
        this.add(form, BorderLayout.NORTH);
        this.add(btns, BorderLayout.SOUTH);
    }

    private void refreshDepartamentos(DefaultTableModel model) {
        try {
            model.setRowCount(0);
            for (Departamento d : dDao.listar()) {
                model.addRow(new Object[]{d.getId(), d.getNombre(), d.getTelefono(), d.getFax()});
            }

            limpiarInputs();

        } catch (Exception ex) {
            showError(ex);
        }
    }
}
