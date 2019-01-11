package com.tasksmanagerapp.tma.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

@Entity
@Table(schema= "public", name="image", uniqueConstraints = {@UniqueConstraint(columnNames={"date", "name"})})
public class Image {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column (name = "date")
    private Date date;

    private Image() {
    }

    public Image(String name) {
        Calendar calendar = Calendar.getInstance();
        java.sql.Date dateNow = new java.sql.Date(calendar.getTime().getTime());

        this.name = name;
        this.date = dateNow;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
