
package lab2herencia;

import java.util.*;
import java.time.LocalDate;

public class Empresa {
    
    protected double horas;
    protected String codigo;
    protected double monto;
    
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
    
    public boolean actualizarFecha(){
        Empleado emp = buscarEmpleado(codigo);
        
        if (emp instanceof EmpleadoTemporal temp) {
            temp.actualizarFecha(fechaNueva);
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
        int est = 0, temp = 0, vent = 0;

        for (Empleado e : empleados) {
            System.out.println("Código: " + e.codigo + " | Nombre: " + e.nombre);
            System.out.println("Horas trabajadas: " + e.horasTrabajadas);
            System.out.println("Pago mensual: " + e.calcularPagoMensual());
            
            if (e instanceof Empleado) est++;
            else if (e instanceof EmpleadoTemporal) temp++;
            else if (e instanceof EmpleadoVentas) vent++;
            
            System.out.println("----------------------------------");
        }

    }
    
}
