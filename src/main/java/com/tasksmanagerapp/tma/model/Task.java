package com.tasksmanagerapp.tma.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.sql.Date;

@Entity
@Table(schema= "public", name="tasks")
public class Task {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Boolean done;
    @Column (name = "datecreated")
    private Timestamp dateCreated;
    @Column
    private Date datecompleted;

    public Task() {
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.done = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateCompleted() {
        return datecompleted;
    }

    public void setDateCompleted(Date datecompleted) {
        this.datecompleted = datecompleted;
    }
}
