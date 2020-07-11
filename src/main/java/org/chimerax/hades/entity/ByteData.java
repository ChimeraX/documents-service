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
@Table(name = "data", schema = "documents")
@Accessors(chain = true)
public class ByteData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    private byte[] data;
}
