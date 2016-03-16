package com.projects.savethemeeting.objectmodel;

/**
 * Created by Michaela on 24.02.2016.
 */

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="record",schema = "public")
public class Record implements Serializable{

    private static final long serialVersionUID = -948760539653304945L;

    public Record(String path) {
        this.path = path;
    }

    public Record() {
    }

    @Id
    @Column(name="id_record")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String path;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
