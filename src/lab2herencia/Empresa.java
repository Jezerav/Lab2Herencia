
package lab2herencia;

import java.util.*;
import java.time.LocalDate;

public class Empresa {
    
    public Empresa() {
        empleados = new ArrayList<>();
    }
    
    private List<Empleado> empleados;
    
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
    
    public boolean registrarHorasTrabajadas(){
        Empleado emp = buscarEmpleado(codigo);
        if (emp == null) return false;
        
        emp.registrarHoras(horas);
        return true;

    }
    
    public boolean registrarVentas(){
        Empleado emp = buscarEmpleado(codigo);
        
        if (emp instanceof EmpleadoVentas ventas) {
            ventas.registrarVenta(monto);
            return true;
        }
        return false;
        
    }
    
    public boolean actualizarFechaFinContrato(){
        Empleado emp = buscarEmpleado(codigo);
        
        if (emp instanceof EmpleadoTemporal temp) {
            temp.actualizarFechaFin(nuevaFecha);
            return true;
        }
        return false;
    }
    
    public double calcularPagoMensual(){
        Empleado emp = buscarEmpleado(codigo);
        if (emp == null) return null;

        return emp.calcularPagoMensual();
    }
    
    public void generarReportes(){
        
    }
    
    public void buscarEmpleados(){
        
    }
}
