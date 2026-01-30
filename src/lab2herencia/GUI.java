package lab2herencia;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class GUI {

    private static Empresa empresa = new Empresa();

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

        // PANEL SUPERIOR
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(azulProfundo);
        panelSuperior.setPreferredSize(new Dimension(0, 80));
        JLabel lblTitulo = new JLabel(" SISTEMA DE GESTION EMPRESARIAL", JLabel.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(blancoPuro);
        panelSuperior.add(lblTitulo, BorderLayout.CENTER);

        // PANEL CENTRAL
        JPanel panelCentral = new JPanel(new BorderLayout(20, 20));
        panelCentral.setOpaque(false);
        panelCentral.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel panelCampos = new JPanel(new GridLayout(4, 4, 20, 20));
        panelCampos.setBackground(blancoPuro);
        panelCampos.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(210, 215, 220), 1, true),
                new EmptyBorder(30, 30, 30, 30)
        ));

        // Campos de entrada
        JTextField txtCod = crearCampoElegante();
        JTextField txtNom = crearCampoElegante();
        JTextField txtSal = crearCampoElegante();
        JTextField txtHrs = crearCampoElegante();
        JTextField txtExt = crearCampoElegante();

        JComboBox<String> cbTipo = new JComboBox<>(new String[]{"Estandar", "Temporal", "Venta"});
        cbTipo.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JSpinner spIngreso = crearSpinnerFecha();
        JSpinner spSalida = crearSpinnerFecha();

        // Agregar campos al panel
        panelCampos.add(new JLabel("Codigo identificador:")); panelCampos.add(txtCod);
        panelCampos.add(new JLabel("Nombre del empleado:")); panelCampos.add(txtNom);
        panelCampos.add(new JLabel("Categoria laboral:")); panelCampos.add(cbTipo);
        panelCampos.add(new JLabel("Remuneracion base:")); panelCampos.add(txtSal);
        panelCampos.add(new JLabel("Fecha de ingreso:")); panelCampos.add(spIngreso);
        panelCampos.add(new JLabel("Vencimiento contrato:")); panelCampos.add(spSalida);
        panelCampos.add(new JLabel("Horas reportadas:")); panelCampos.add(txtHrs);
        panelCampos.add(new JLabel("Comision / Ventas:")); panelCampos.add(txtExt);

        // Consola
        JTextArea consola = new JTextArea();
        consola.setFont(new Font("Monospaced", Font.PLAIN, 13));
        consola.setBackground(new Color(250, 250, 250));
        consola.setForeground(new Color(50, 50, 50));
        consola.setCaretColor(Color.BLACK);
        consola.setMargin(new Insets(15, 15, 15, 15));

        JScrollPane scroll = new JScrollPane(consola);
        scroll.setBorder(new TitledBorder(new LineBorder(azulProfundo), "Log de transacciones",
                TitledBorder.LEADING, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 12), azulProfundo));

        // Panel inferior - botones
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        panelInferior.setOpaque(false);

        JButton btnAdd = crearBotonEstilizado("REGISTRAR", new Color(46, 204, 113));
        JButton btnSearch = crearBotonEstilizado("BUSCAR", new Color(52, 152, 219));
        JButton btnPay = crearBotonEstilizado("CALCULAR PAGO", new Color(230, 126, 34));
        JButton btnInfo = crearBotonEstilizado("REPORTE", new Color(155, 89, 182));

        panelInferior.add(btnAdd);
        panelInferior.add(btnSearch);
        panelInferior.add(btnPay);
        panelInferior.add(btnInfo);

        panelCentral.add(panelCampos, BorderLayout.NORTH);
        panelCentral.add(scroll, BorderLayout.CENTER);

        frame.add(panelSuperior, BorderLayout.NORTH);
        frame.add(panelCentral, BorderLayout.CENTER);
        frame.add(panelInferior, BorderLayout.SOUTH);

        // ---------------------------
        // EVENTOS DE LOS BOTONES
        // ---------------------------

        // Registrar empleado
        btnAdd.addActionListener(e -> {
            String codigo = txtCod.getText().trim();
            String nombre = txtNom.getText().trim();
            double salario = Double.parseDouble(txtSal.getText().trim());
            String tipo = (String) cbTipo.getSelectedItem();
            double horas = txtHrs.getText().isEmpty() ? 0 : Double.parseDouble(txtHrs.getText().trim());
            double comision = txtExt.getText().isEmpty() ? 0 : Double.parseDouble(txtExt.getText().trim());

            // Convertir fechas a Calendar
            Calendar ingreso = Calendar.getInstance();
            ingreso.setTime((Date) spIngreso.getValue());
            Calendar salida = Calendar.getInstance();
            salida.setTime((Date) spSalida.getValue());

            Empleado emp = null;

            switch (tipo) {
                case "Estandar":
                    emp = new Empleado(codigo, nombre, salario);
                    break;
                case "Temporal":
                    emp = new EmpleadoTemporal(codigo, nombre, salario, salida);
                    break;
                case "Venta":
                    emp = new EmpleadoVentas(codigo, nombre, salario, comision);
                    break;
            }

            if (emp != null && empresa.registrarEmpleado(emp)) {
                // Registrar horas iniciales
                if (horas > 0) emp.registrarHoras(horas);
                consola.append("> Empleado registrado correctamente: " + codigo + "\n");
            } else {
                consola.append("> Error: el código ya existe o datos inválidos\n");
            }
        });

        // Buscar empleado
        btnSearch.addActionListener(e -> {
            String codigo = txtCod.getText().trim();
            Empleado emp = empresa.buscarEmpleado(codigo);
            if (emp != null) {
                consola.append("> Empleado encontrado: \n" + emp.mostrarInfo() + "\n");
            } else {
                consola.append("> Empleado no encontrado\n");
            }
        });

        // Calcular pago mensual
        btnPay.addActionListener(e -> {
            String codigo = txtCod.getText().trim();
            double pago = empresa.calcularPagoMensual(codigo);
            consola.append("> Pago mensual de " + codigo + ": " + pago + "\n");
        });

        // Generar reporte
        btnInfo.addActionListener(e -> {
            consola.append("> Generando reporte completo...\n");
            empresa.generarReportes();
        });

        // Mostrar ventana
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // ---------------------------
    // Métodos auxiliares
    // ---------------------------
    private static void configurarAparienciaSistema() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {
        }
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
