package lab2herencia;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class GUI {

    private static Empresa empresa = new Empresa();
    private static JTextField txtCod, txtNom, txtSal, txtHrs, txtExt;
    private static JComboBox<String> cbTipo;
    private static JSpinner spIngreso, spSalida;

    public static void main(String[] args) {
        configurarAparienciaSistema();

        JFrame frame = new JFrame("LAB#2 - Herencia - Grupo 5");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 800);
        frame.setLayout(new BorderLayout(20, 20));

        Color azulProfundo = new Color(41, 128, 185);
        Color fondoGris = new Color(242, 244, 247);
        Color blancoPuro = Color.WHITE;

        frame.getContentPane().setBackground(fondoGris);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(azulProfundo);
        panelSuperior.setPreferredSize(new Dimension(0, 80));
        JLabel lblTitulo = new JLabel(" SISTEMA DE GESTION EMPRESARIAL", JLabel.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(blancoPuro);
        panelSuperior.add(lblTitulo, BorderLayout.CENTER);

        JPanel panelCentral = new JPanel(new BorderLayout(20, 20));
        panelCentral.setOpaque(false);
        panelCentral.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel panelCampos = new JPanel(new GridLayout(4, 4, 20, 20));
        panelCampos.setBackground(blancoPuro);
        panelCampos.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(210, 215, 220), 1, true),
                new EmptyBorder(30, 30, 30, 30)
        ));

        txtCod = crearCampoElegante();
        txtNom = crearCampoElegante();
        txtSal = crearCampoElegante();
        txtHrs = crearCampoElegante();
        txtExt = crearCampoElegante();

        cbTipo = new JComboBox<>(new String[]{"Estandar", "Temporal", "Venta"});
        cbTipo.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        spIngreso = crearSpinnerFecha();
        spSalida = crearSpinnerFecha();

        panelCampos.add(new JLabel("Codigo identificador:")); panelCampos.add(txtCod);
        panelCampos.add(new JLabel("Nombre del empleado:")); panelCampos.add(txtNom);
        panelCampos.add(new JLabel("Categoria laboral:")); panelCampos.add(cbTipo);
        panelCampos.add(new JLabel("Salario base:")); panelCampos.add(txtSal);
        panelCampos.add(new JLabel("Fecha de ingreso:")); panelCampos.add(spIngreso);
        panelCampos.add(new JLabel("Vencimiento contrato:")); panelCampos.add(spSalida);
        panelCampos.add(new JLabel("Horas reportadas:")); panelCampos.add(txtHrs);
        panelCampos.add(new JLabel("Comision / Ventas:")); panelCampos.add(txtExt);

        JTextArea consola = new JTextArea();
        consola.setFont(new Font("Monospaced", Font.PLAIN, 13));
        consola.setBackground(new Color(250, 250, 250));
        consola.setForeground(new Color(50, 50, 50));
        consola.setMargin(new Insets(15, 15, 15, 15));

        JScrollPane scroll = new JScrollPane(consola);
        scroll.setBorder(new TitledBorder(new LineBorder(azulProfundo), "Log de transacciones",
                TitledBorder.LEADING, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 12), azulProfundo));
        
        panelCentral.add(panelCampos, BorderLayout.NORTH);
        panelCentral.add(scroll, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new GridLayout(1, 7, 10, 0)); // 1 fila, 7 columnas, 10px entre botones
        panelInferior.setOpaque(false);


        JButton btnAdd = crearBotonEstilizado("REGISTRAR", new Color(46, 204, 113));
        JButton btnSearch = crearBotonEstilizado("BUSCAR", new Color(52, 152, 219));
        JButton btnPay = crearBotonEstilizado("CALCULAR PAGO", new Color(230, 126, 34));
        JButton btnInfo = crearBotonEstilizado("REPORTE", new Color(155, 89, 182));

        JButton btnHoras = crearBotonEstilizado("AGREGAR HORAS", new Color(52, 73, 94));
        JButton btnVentas = crearBotonEstilizado("REGISTRAR VENTAS", new Color(39, 174, 96));
        JButton btnActualizarContrato = crearBotonEstilizado("ACTUALIZAR CONTRATO", new Color(192, 57, 43));

        panelInferior.add(btnAdd);
        panelInferior.add(btnSearch);
        panelInferior.add(btnPay);
        panelInferior.add(btnInfo);
        panelInferior.add(btnHoras);
        panelInferior.add(btnVentas);
        panelInferior.add(btnActualizarContrato);


        frame.add(panelSuperior, BorderLayout.NORTH);
        frame.add(panelCentral, BorderLayout.CENTER);
        frame.add(panelInferior, BorderLayout.SOUTH);
        
        cbTipo.addActionListener(e -> {
    String tipo = (String) cbTipo.getSelectedItem();

    switch (tipo) {
        case "Estandar":
            spSalida.setVisible(false);
            txtExt.setVisible(false);  
            break;
        case "Temporal":
            spSalida.setVisible(true); 
            txtExt.setVisible(false); 
            break;
        case "Venta":
            spSalida.setVisible(false);
            txtExt.setVisible(true);   
            break;
    }

    spSalida.getParent().revalidate();
    spSalida.getParent().repaint();
    txtExt.getParent().revalidate();
    txtExt.getParent().repaint();
});

        
        

        btnAdd.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(frame, "Desea registrar al empleado con los datos ingresados?", "Confirmar Registro", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                try {
                    String codigo = txtCod.getText().trim();
                    String nombre = txtNom.getText().trim();
                    double salario = Double.parseDouble(txtSal.getText().trim());
                    String tipo = (String) cbTipo.getSelectedItem();
                    double horas = txtHrs.getText().isEmpty() ? 0 : Double.parseDouble(txtHrs.getText().trim());
                    double comision = txtExt.getText().isEmpty() ? 0 : Double.parseDouble(txtExt.getText().trim());

                    Calendar ingreso = Calendar.getInstance();
                    ingreso.setTime((Date) spIngreso.getValue());
                    Calendar salida = Calendar.getInstance();
                    salida.setTime((Date) spSalida.getValue());

                    Empleado emp = null;
                    if (tipo.equals("Estandar")) emp = new Empleado(codigo, nombre, salario);
                    else if (tipo.equals("Temporal")) emp = new EmpleadoTemporal(codigo, nombre, salario, salida);
                    else emp = new EmpleadoVentas(codigo, nombre, salario, comision);

                    if (emp != null && empresa.registrarEmpleado(emp)) {
                        if (horas > 0) emp.registrarHoras(horas);
                        consola.append("> Registro exitoso: " + codigo + "\n");
                        limpiarCampos();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error: El codigo ya existe.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error en formato de datos.");
                }
            }
        });

        btnSearch.addActionListener(e -> {
            String codBusqueda = JOptionPane.showInputDialog(frame, "Ingrese el codigo del empleado a buscar:");
            if (codBusqueda != null && !codBusqueda.isEmpty()) {
                Empleado emp = empresa.buscarEmpleado(codBusqueda);
                if (emp != null) {
                    consola.append("> Resultado de busqueda:\n" + emp.mostrarInfo() + "\n");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(frame, "Empleado no encontrado.");
                }
            }
        });

        btnPay.addActionListener(e -> {
            String codPago = JOptionPane.showInputDialog(frame, "Ingrese el codigo para calcular el pago:");
            if (codPago != null && !codPago.isEmpty()) {
                double pago = empresa.calcularPagoMensual(codPago);
                if (pago > 0) {
                    consola.append("> Pago calculado para " + codPago + ": $" + pago + "\n");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(frame, "No se pudo calcular el pago. Verifique el codigo.");
                }
            }
        });

        btnInfo.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Generando reportes en consola del sistema...");
            empresa.generarReportes(consola);
            consola.append("> Reporte generado exitosamente.\n");
            
        });
        
        btnHoras.addActionListener(e -> {
            String cod = JOptionPane.showInputDialog(frame, "Ingrese el codigo del empleado:");
            if (cod != null && !cod.isEmpty()) {
                Empleado emp = empresa.buscarEmpleado(cod);
                if (emp != null) {
                    try {
                        double horas = Double.parseDouble(JOptionPane.showInputDialog(frame, "Ingrese las horas trabajadas:"));
                        emp.registrarHoras(horas);
                        consola.append("> Se agregaron " + horas + " horas a " + cod + "\n");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Formato de horas incorrecto.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Empleado no encontrado.");
                }
            }
        });


        btnVentas.addActionListener(e -> {
            String cod = JOptionPane.showInputDialog(frame, "Ingrese el codigo del empleado de ventas:");
            if (cod != null && !cod.isEmpty()) {
                Empleado emp = empresa.buscarEmpleado(cod);
                if (emp != null && emp.getClass().getSimpleName().equals("EmpleadoVentas")) {
                    try {
                        double monto = Double.parseDouble(JOptionPane.showInputDialog(frame, "Ingrese el monto de ventas:"));
                        ((EmpleadoVentas) emp).registrarVentas(monto); // cast explícito
                        consola.append("> Se registraron $" + monto + " de ventas a " + cod + "\n");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Formato de monto incorrecto.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "El empleado no es de tipo Ventas.");
                }
            }
        });


        btnActualizarContrato.addActionListener(e -> {
            String cod = JOptionPane.showInputDialog(frame, "Ingrese el codigo del empleado temporal:");
            if (cod != null && !cod.isEmpty()) {
                Empleado emp = empresa.buscarEmpleado(cod);
                if (emp != null && emp.getClass().getSimpleName().equals("EmpleadoTemporal")) {
                    try {
                        JSpinner spNueva = crearSpinnerFecha();
                        int res = JOptionPane.showConfirmDialog(frame, spNueva, 
                                "Seleccione nueva fecha de fin de contrato", JOptionPane.OK_CANCEL_OPTION);
                        if (res == JOptionPane.OK_OPTION) {
                            Calendar nuevaFecha = Calendar.getInstance();
                            nuevaFecha.setTime((Date) spNueva.getValue());
                            ((EmpleadoTemporal) emp).actualizarFecha(nuevaFecha); // cast explícito
                            consola.append("> Fecha de fin de contrato actualizada para " + cod + "\n");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Error al actualizar fecha.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "El empleado no es temporal.");
                }
            }
        });



        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void limpiarCampos() {
        txtCod.setText("");
        txtNom.setText("");
        txtSal.setText("");
        txtHrs.setText("");
        txtExt.setText("");
        cbTipo.setSelectedIndex(0);
    }

    private static void configurarAparienciaSistema() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}
    }

    private static JTextField crearCampoElegante() {
        JTextField campo = new JTextField();
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1),
                new EmptyBorder(5, 10, 5, 10)
        ));
        return campo;
    }

    private static JSpinner crearSpinnerFecha() {
        JSpinner s = new JSpinner(new SpinnerDateModel());
        s.setEditor(new JSpinner.DateEditor(s, "dd/MM/yyyy"));
        s.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return s;
    }

    private static JButton crearBotonEstilizado(String texto, Color fondo) {
        JButton b = new JButton(texto);
        b.setPreferredSize(new Dimension(160, 45));
        b.setBackground(fondo);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setFocusPainted(false);
        b.setBorder(new LineBorder(fondo.darker(), 1));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return b;
    }
}