package com.mapping.manytomany;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class Employee {
    @Id
    private int eid;
    private String ename;
    @ManyToMany
    private List<Project> projectList;

    public Employee () {
    }

    public Employee (int eid, String ename, List<Project> projectList) {
        this.eid = eid;
        this.ename = ename;
        this.projectList = projectList;
    }

    public int getEid () {
        return eid;
    }

    public void setEid (int eid) {
        this.eid = eid;
    }

    public String getEname () {
        return ename;
    }

    public void setEname (String ename) {
        this.ename = ename;
    }

    public List<Project> getProjectList () {
        return projectList;
    }

    public void setProjectList (List<Project> projectList) {
        this.projectList = projectList;
    }
}
