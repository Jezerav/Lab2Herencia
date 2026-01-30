
package lab2herencia;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    public void generarReportes() {
        int estandar = 0, temporales = 0, ventas = 0;

        for (Empleado e : empleados) {

            if (e instanceof EmpleadoTemporal) {
                temporales++;
            } else if (e instanceof EmpleadoVentas) {
                ventas++;
            } else {
                estandar++;
            }

            System.out.println("------------------------------------------");
            System.out.println(e.mostrarInfo());
            System.out.println("Horas trabajadas: " + e.getHorasTotal());
            System.out.println("Pago mensual: " + calcularPagoMensual(e.getCodigo()));
        }

        System.out.println("\n<---- RESUMEN DE EMPLEADOS ---->");
        System.out.println("Empleados base: " + estandar);
        System.out.println("Empleados temporales: " + temporales);
        System.out.println("Empleados de ventas: " + ventas);
    }
}
