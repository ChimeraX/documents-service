package org.chimerax.hades.repository;

import org.chimerax.hades.entity.Folder;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 24-May-20
 * Time: 8:27 PM
 */

public interface FolderRepository extends JpaRepositoryImplementation<Folder, Long> {

    Optional<Folder> findByRootAndOwner(final boolean root, final String owner);

    default Optional<Folder> findRootForOwner(final String owner) {
        return findByRootAndOwner(true, owner);
    }

    default Optional<String> findOwnerById(final long id) {
        return findById(id).map(Folder::getOwner);
    }

    CompletableFuture<Long> countAllByParentId(final long parentId);

}
