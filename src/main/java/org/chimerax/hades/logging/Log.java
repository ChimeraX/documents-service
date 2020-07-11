package org.chimerax.hades.logging;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 04-Jun-20
 * Time: 6:24 PM
 */


@Data
@Entity
@Table(name = "logs", schema = "logs")
@Accessors(chain = true)
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;

    private String type;

    private String path;

    private long createdAt;

    @PrePersist
    void prePersist() {
        createdAt = new Date().getTime();
    }
}
