package org.chimerax.hades.entity;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 24-May-20
 * Time: 7:47 PM
 */

@Data
@Entity
@Table(name = "folders", schema = "documents")
@Accessors(chain = true)
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id",
            insertable = false, updatable = false)
    @ToString.Exclude
    private Folder parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private List<Folder> subFolders = new ArrayList<>();

    @OneToMany(mappedBy = "folder", fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private List<Document> documents = new ArrayList<>();

    private String owner;

    private long createdAt;

    private boolean root;

    private long size;

    private long totalFiles;

    @PrePersist
    void prePersist() {
        createdAt = new Date().getTime();
    }
}
