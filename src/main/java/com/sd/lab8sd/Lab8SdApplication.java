package com.sd.lab8sd;

import com.sd.lab8sd.ui.*;

import javax.swing.*;

public class Lab8SdApplication extends JFrame {

    public Lab8SdApplication() {
        setTitle("Gestión de Empresa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        DepartamentoPanel dPanel = new DepartamentoPanel();
        IngenieroPanel iPanel = new IngenieroPanel();
        ProyectoPanel pPanel = new ProyectoPanel();
        AsignacionPanel aPanel = new AsignacionPanel();
        ProyectosPorDptoPanel pdPanel = new ProyectosPorDptoPanel();
        IngenierosPorProyectoPanel ipPanel = new IngenierosPorProyectoPanel();

        tabs.addTab("Departamentos", dPanel);
        tabs.addTab("Ingenieros", iPanel);
        tabs.addTab("Proyectos", pPanel);
        tabs.addTab("Asignaciones", aPanel);
        tabs.addTab("Proyectos por Dpto", pdPanel);
        tabs.addTab("Ingenieros por Proyecto", ipPanel);

        add(tabs);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Lab8SdApplication().setVisible(true));
    }
}