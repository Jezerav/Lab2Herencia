/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2herencia;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author jerem
 */
public class Empleado {
    protected String codigo;
    protected String nombre;
    protected Calendar fechaCont;
    protected double salarioBase;
    protected double horasTotal;
    
    public Empleado(String codigo, String nombre,double salarioBase){
        this.codigo=codigo;
        this.nombre=nombre;
        this.fechaCont=Calendar.getInstance();
        this.salarioBase=salarioBase;
        this.horasTotal=0;
    }
    
    public String getCodigo() {
        return codigo;
    }
    public String getNombre() {
        return nombre;
    }

    public double getSalarioBase() {
        return salarioBase;
    }
    
    public void registrarHoras(double horas){
        this.horasTotal += horas;
    }
    
    public double calcularPago(){
        return salarioBase * (horasTotal/160.00);
    }
    
    public double calcularPagoConDeduccion(){ //Jeremy: lo separo para que no se mezcle con las deducciones a los Temporales y Ventas
        double pago = calcularPago();
        return pago - (pago * 0.035);
    }
    public double getHorasTotal() {
        return horasTotal;
    }
    
    public String mostrarInfo(){
        SimpleDateFormat fechaFormateada = new SimpleDateFormat("dd/MM/yyyy");
        
        return "Codigo: "+ codigo +
                "\nNombre: " + nombre +
                "\nFecha de contratacion: " +  fechaFormateada.format(fechaCont.getTime());
    }  
}
    
