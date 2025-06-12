package com.sd.lab8sd.ui;

import com.sd.lab8sd.dao.DepartamentoDAO;
import com.sd.lab8sd.model.Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProyectosPorDptoPanel extends Panel{

    private final DepartamentoDAO dDao = new DepartamentoDAO();

    public ProyectosPorDptoPanel() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre", "Fecha Inicio", "Fecha Fin", "Created At", "Updated At"}, 0);
        JTable table = new JTable(model);

        JPanel form = new JPanel();
        JTextField idDpto = new JTextField(5);
        JButton buscar = new JButton("Buscar");
        form.add(new JLabel("ID Departamento:"));
        form.add(idDpto);
        form.add(buscar);

        buscar.addActionListener(e -> {
            try {
                model.setRowCount(0);
                int id = Integer.parseInt(idDpto.getText());
                List<Proyecto> lista = dDao.obtenerProyectosPorDepartamento(id);
                if (lista.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No hay proyectos para este departamento.");
                    return;
                }
                for (Proyecto p : lista) {
                    model.addRow(new Object[]{
                            p.getIdProy(),
                            p.getNombre(),
                            p.getFechaInicio(),
                            (p.getFechaFin() != null ? p.getFechaFin() : "-"),
                            (p.getCreatedAt() != null ? p.getCreatedAt() : "-"),
                            (p.getUpdatedAt() != null ? p.getUpdatedAt() : "-")
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
