package com.sd.lab8sd.ui;

import com.sd.lab8sd.dao.ProyectoDAO;
import com.sd.lab8sd.model.Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

public class ProyectoPanel extends Panel {

    private final ProyectoDAO pDao = new ProyectoDAO();

    public ProyectoPanel() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre", "Fecha Inicio", "Fecha Fin"}, 0);
        JTable table = new JTable(model);
        refreshProyectos(model);

        JPanel form = new JPanel(new GridLayout(0, 2, 5, 5));
        JTextField id = new JTextField(), nombre = new JTextField(), inicio = new JTextField(), fin = new JTextField();
        form.add(new JLabel("ID (para actualizar/eliminar):"));
        form.add(id);
        form.add(new JLabel("Nombre:"));
        form.add(nombre);
        form.add(new JLabel("Fecha inicio (YYYY-MM-DD):"));
        form.add(inicio);
        form.add(new JLabel("Fecha fin (YYYY-MM-DD, opcional):"));
        form.add(fin);

        JPanel btns = new JPanel();
        JButton insertar = new JButton("Insertar"), actualizar = new JButton("Actualizar"), eliminar = new JButton("Eliminar"), refrescar = new JButton("Refrescar");
        btns.add(insertar);
        btns.add(actualizar);
        btns.add(eliminar);
        btns.add(refrescar);

        insertar.addActionListener(e -> {
            try {
                LocalDate ini = LocalDate.parse(inicio.getText());
                LocalDate f = fin.getText().isEmpty() ? null : LocalDate.parse(fin.getText());
                Proyecto p = new Proyecto(0, nombre.getText(), ini, f);
                if (pDao.insertar(p)) {
                    JOptionPane.showMessageDialog(this, "Insertado con ID " + p.getIdProy());
                    refreshProyectos(model);
                }

                limpiarInputs();

            } catch (Exception ex) {
                showError(ex);
            }
        });
        actualizar.addActionListener(e -> {
            try {
                LocalDate ini = LocalDate.parse(inicio.getText());
                LocalDate f = fin.getText().isEmpty() ? null : LocalDate.parse(fin.getText());
                Proyecto p = new Proyecto(Integer.parseInt(id.getText()), nombre.getText(), ini, f);
                if (pDao.actualizar(p)) {
                    JOptionPane.showMessageDialog(this, "Actualizado correctamente");
                    refreshProyectos(model);
                }

                limpiarInputs();

            } catch (Exception ex) {
                showError(ex);
            }
        });
        eliminar.addActionListener(e -> {
            try {
                if (pDao.eliminar(Integer.parseInt(id.getText()))) {
                    JOptionPane.showMessageDialog(this, "Eliminado correctamente");
                    refreshProyectos(model);
                }

                limpiarInputs();

            } catch (Exception ex) {
                showError(ex);
            }
        });
        refrescar.addActionListener(e -> refreshProyectos(model));

        this.add(new JScrollPane(table), BorderLayout.CENTER);
        this.add(form, BorderLayout.NORTH);
        this.add(btns, BorderLayout.SOUTH);
    }

    private void refreshProyectos(DefaultTableModel model) {
        try {
            model.setRowCount(0);
            for (Proyecto p : pDao.listar()) {
                model.addRow(new Object[]{p.getIdProy(), p.getNombre(), p.getFechaInicio(), p.getFechaFin()});
            }
            limpiarInputs();
        } catch (Exception ex) {
            showError(ex);
        }
    }

}
