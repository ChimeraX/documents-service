package org.chimerax.hades.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 13-May-20
 * Time: 3:50 PM
 */

@Data
@Entity
@Table(name = "documents", schema = "documents")
@Accessors(chain = true)
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String type;

    @Column(name = "data_id")
    private long data;

    private long size;

    @Column(name = "folder_id")
    private long folderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id", updatable = false, insertable = false)
    private Folder folder;

    private String owner;

    @Column(name = "public")
    private Boolean isPublic;

    private long createdAt;

    @PrePersist
    void prePersist() {
        createdAt = new Date().getTime();
    }
}
