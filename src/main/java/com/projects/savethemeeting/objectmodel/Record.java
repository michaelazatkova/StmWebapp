package com.projects.savethemeeting.objectmodel;

/**
 * Created by Michaela on 24.02.2016.
 */

import javax.persistence.*;

@Entity
@Table(name="record",schema = "public")
public class Record {

    @Id
    @Column(name="id_record")
    @GeneratedValue
    private long id;

    @Column
    private String path;
}
