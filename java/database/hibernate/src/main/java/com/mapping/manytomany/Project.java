package com.mapping.manytomany;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class Project {
    @Id
    private int pid;
    private String pname;
    @ManyToMany(mappedBy = "projectList")
    private List<Employee> employeeList;

    public Project(){

    }

    public Project (int pid, String pname, List<Employee> employeeList) {
        this.pid = pid;
        this.pname = pname;
        this.employeeList = employeeList;
    }

    public int getPid () {
        return pid;
    }

    public void setPid (int pid) {
        this.pid = pid;
    }

    public String getPname () {
        return pname;
    }

    public void setPname (String pname) {
        this.pname = pname;
    }

    public List<Employee> getEmployeeList () {
        return employeeList;
    }

    public void setEmployeeList (List<Employee> employeeList) {
        this.employeeList = employeeList;
    }


}
