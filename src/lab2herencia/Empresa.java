
package lab2herencia;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JTextArea;

public class Empresa {

    private List<Empleado> empleados;

    public Empresa() {
        empleados = new ArrayList<>();
    }

    public Empleado buscarEmpleado(String codigo) {
        for (Empleado e : empleados) {
            if (e.getCodigo().equals(codigo)) {
                return e;
            }
        }
        return null;
    }

    public boolean registrarEmpleado(Empleado emp) {
        if (buscarEmpleado(emp.getCodigo()) != null) {
            return false;
        }
        empleados.add(emp);
        return true;
    }

    public boolean registrarHorasTrabajadas(String codigo, double horas) {
        Empleado emp = buscarEmpleado(codigo);
        if (emp == null) {
            return false;
        }
        emp.registrarHoras(horas);
        return true;
    }

    public boolean registrarVentas(String codigo, double monto) {
        Empleado emp = buscarEmpleado(codigo);

        if (emp instanceof EmpleadoVentas) {
            EmpleadoVentas ventas = (EmpleadoVentas) emp;
            ventas.registrarVentas(monto);
            return true;
        }
        return false;
    }

    public boolean actualizarFechaFinContrato(String codigo, Calendar nuevaFecha) {
        Empleado emp = buscarEmpleado(codigo);

        if (emp instanceof EmpleadoTemporal) {
            EmpleadoTemporal temp = (EmpleadoTemporal) emp;
            temp.actualizarFecha(nuevaFecha);
            return true;
        }
        return false;
    }

    public double calcularPagoMensual(String codigo) {
        Empleado emp = buscarEmpleado(codigo);
        if (emp == null) {
            return 0;
        }

        if (!(emp instanceof EmpleadoTemporal) && !(emp instanceof EmpleadoVentas)) {
            return emp.calcularPagoConDeduccion();
        }

        return emp.calcularPago();
    }

    public void generarReportes(JTextArea consola) {
    int estandar = 0, temporales = 0, ventas = 0;

    for (Empleado e : empleados) {

        if (e instanceof EmpleadoTemporal) {
            temporales++;
        } else if (e instanceof EmpleadoVentas) {
            ventas++;
        } else {
            estandar++;
        }

        consola.append("<----------------------------->\n");
        consola.append(e.mostrarInfo() + "\n");
        consola.append("Horas trabajadas: " + e.getHorasTotal() + "\n");
        consola.append("Pago mensual: " + calcularPagoMensual(e.getCodigo()) + "\n");
    }

    consola.append("\n<---- RESUMEN DE EMPLEADOS ---->\n");
    consola.append("Empleados BASE: " + estandar + "\n");
    consola.append("Empleados TEMPORALES: " + temporales + "\n");
    consola.append("Empleados de VENTAS: " + ventas + "\n");
    }

}
