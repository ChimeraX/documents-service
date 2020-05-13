package org.chimerax.hades.repository;

import org.chimerax.hades.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 13-May-20
 * Time: 4:04 PM
 */
public interface DocumentRepository extends JpaRepository<Document, Long> {

}
