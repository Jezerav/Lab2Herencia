/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2herencia;

import java.util.Calendar;

/**
 *
 * @author jerem
 */
public class EmpleadoVentas extends Empleado{
    private double ventasMensuales[];
    private double tasaComision;
    
    public EmpleadoVentas(String codigo, String nombre, double salarioBase, double tasaComision){
        super(codigo, nombre, salarioBase);
        this.tasaComision = tasaComision;
        this.ventasMensuales = new double[12];
    }
    
    public void registrarVentas(double monto){
        int mesActual = Calendar.getInstance().get(Calendar.MONTH);
        ventasMensuales[mesActual] += monto;
    }
    
    public double calcularComision(){
        int mesActual = Calendar.getInstance().get(Calendar.MONTH);
        
        return ventasMensuales[mesActual] * tasaComision;
    }
    
    public double calcularPago(){
        return super.calcularPago() + calcularComision();
    }
    
    public double ventasAnuales(){
        double total = 0.00;
        
        for(double venta: ventasMensuales){
            total += venta;
        }
        return total;
    }
    
    public String mostrarInfo(){
        return super.mostrarInfo() +
                "\nVentas Anuales: " +  ventasAnuales();
    }
}
