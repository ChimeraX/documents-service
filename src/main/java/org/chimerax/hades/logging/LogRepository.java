package org.chimerax.hades.logging;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 04-Jun-20
 * Time: 6:32 PM
 */
public interface LogRepository extends JpaRepository<Log, Long> {
}
