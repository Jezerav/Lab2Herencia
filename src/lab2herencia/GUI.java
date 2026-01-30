package lab2herencia;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.Date;

public class GUI {

    public static void main(String[] args) {
        configurarAparienciaSistema();

        JFrame frame = new JFrame("Portal Administrativo - Gestion de Talento Humano");
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

        JTextField txtCod = crearCampoElegante();
        JTextField txtNom = crearCampoElegante();
        JTextField txtSal = crearCampoElegante();
        JTextField txtHrs = crearCampoElegante();
        JTextField txtExt = crearCampoElegante();
        
        JComboBox<String> cbTipo = new JComboBox<>(new String[]{"Estandar", "Temporal", "Ventas"});
        cbTipo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JSpinner spIngreso = crearSpinnerFecha();
        JSpinner spSalida = crearSpinnerFecha();

        panelCampos.add(new JLabel("Codigo Identificador:")); panelCampos.add(txtCod);
        panelCampos.add(new JLabel("Nombre del Empleado:")); panelCampos.add(txtNom);
        panelCampos.add(new JLabel("Categoria Laboral:")); panelCampos.add(cbTipo);
        panelCampos.add(new JLabel("Remuneracion Base:")); panelCampos.add(txtSal);
        panelCampos.add(new JLabel("Fecha de Ingreso:")); panelCampos.add(spIngreso);
        panelCampos.add(new JLabel("Vencimiento Contrato:")); panelCampos.add(spSalida);
        panelCampos.add(new JLabel("Horas Reportadas:")); panelCampos.add(txtHrs);
        panelCampos.add(new JLabel("Comision / Ventas:")); panelCampos.add(txtExt);

        JTextArea consola = new JTextArea();
        consola.setFont(new Font("Monospaced", Font.PLAIN, 13));
        /* Cambio a fondo claro para el log */
        consola.setBackground(new Color(250, 250, 250));
        consola.setForeground(new Color(50, 50, 50));
        consola.setCaretColor(Color.BLACK);
        consola.setMargin(new Insets(15, 15, 15, 15));
        
        JScrollPane scroll = new JScrollPane(consola);
        scroll.setBorder(new TitledBorder(new LineBorder(azulProfundo), "Log de Transacciones", TitledBorder.LEADING, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 12), azulProfundo));

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

        /* ESPACIO PARA AGREGAR LOGICA DE BOTONES */

        btnAdd.addActionListener(e -> {
            /* INSERTE AQUI EL CODIGO PARA REGISTRAR EMPLEADO */
            consola.append("> Iniciando registro de empleado...\n");
        });

        btnSearch.addActionListener(e -> {
            /* INSERTE AQUI EL CODIGO PARA BUSCAR EMPLEADO POR CODIGO */
            consola.append("> Buscando empleado en la base de datos...\n");
        });

        btnPay.addActionListener(e -> {
            /* INSERTE AQUI EL CODIGO PARA CALCULAR EL SALARIO NETO */
            consola.append("> Calculando nomina del periodo actual...\n");
        });

        btnInfo.addActionListener(e -> {
            /* INSERTE AQUI EL CODIGO PARA GENERAR EL REPORTE GENERAL */
            consola.append("> Generando reporte detallado de empleados...\n");
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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