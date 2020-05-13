package org.chimerax.hades.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

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

    @Lob
    private byte[] data;

    private long size;

    private String owner;
}
