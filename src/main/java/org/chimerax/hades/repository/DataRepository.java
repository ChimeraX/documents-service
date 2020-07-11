package org.chimerax.hades.repository;

import org.chimerax.hades.entity.ByteData;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;


/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 24-May-20
 * Time: 8:27 PM
 */

public interface DataRepository extends JpaRepositoryImplementation<ByteData, Long> {
}

