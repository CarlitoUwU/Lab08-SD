package com.sd.lab8sd.ui;

import com.sd.lab8sd.dao.IngenieroDAO;
import com.sd.lab8sd.model.Ingeniero;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class IngenierosPorProyectoPanel extends Panel {

    private final IngenieroDAO iDao = new IngenieroDAO();

    public IngenierosPorProyectoPanel() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "ID Dpto", "Nombre", "Apellido", "Especialidad", "Cargo"}, 0);
        JTable table = new JTable(model);

        JPanel form = new JPanel();
        JTextField idProy = new JTextField(5);
        JButton buscar = new JButton("Buscar");
        form.add(new JLabel("ID Proyecto:"));
        form.add(idProy);
        form.add(buscar);

        buscar.addActionListener(e -> {
            try {
                model.setRowCount(0);
                int id = Integer.parseInt(idProy.getText());
                List<Ingeniero> lista = iDao.obtenerIngenierosPorProyecto(id);
                if (lista.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No hay ingenieros asignados a este proyecto.");
                    return;
                }
                for (Ingeniero i : lista) {
                    model.addRow(new Object[]{
                            i.getIdIng(),
                            i.getIdDpto(),
                            i.getNombre(),
                            i.getApellido(),
                            i.getEspecialidad(),
                            i.getCargo()
                    });
                }
            } catch (Exception ex) {
                showError(ex);
            }
        });

        this.add(form, BorderLayout.NORTH);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }

}
