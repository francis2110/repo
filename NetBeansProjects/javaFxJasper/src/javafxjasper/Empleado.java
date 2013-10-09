/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxjasper;

/**
 *
 * @author fran
 */
public class Empleado {
    private String name,cedula;
    private Integer salary;

    public Empleado(String name, String cedula, Integer salary) {
        this.name = name;
        this.cedula = cedula;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
    
    
}
