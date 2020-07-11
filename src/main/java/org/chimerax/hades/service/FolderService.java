package org.chimerax.hades.service;

import lombok.AllArgsConstructor;
import org.chimerax.hades.api.dto.folder.*;
import org.chimerax.hades.entity.Folder;
import org.chimerax.hades.logging.Logged;
import org.chimerax.hades.repository.DocumentRepository;
import org.chimerax.hades.repository.FolderRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 24-May-20
 * Time: 8:29 PM
 */

@Service
@AllArgsConstructor
public class FolderService {

    private FolderRepository folderRepository;

    private FolderConverter folderConverter;

    @Logged
    public Optional<FolderDTO> findById(long id) {
        return folderRepository.findById(id)
                .map(folderConverter::toFolderDTO);
    }

    @Logged
    public Optional<FolderDTO> findRootForOwner() {
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return folderRepository.findRootForOwner(username).map(folderConverter::toFolderDTO);
    }

    @Logged
    public void save(final CreateFolderDTO folderDTO) {
        final Folder folder = folderConverter.toFolder(folderDTO);
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Folder> optionalFolder = folderRepository.findById(folderDTO.getParentId());
        if (optionalFolder.isPresent()) {
            final Folder parent = optionalFolder.get();
            if (parent.getOwner().equals(username)) {
                folder.setOwner(username);
                folderRepository.save(folder);
            }
        }
    }

    public long saveRootForOwner(RootFolderDTO rootFolderDTO) {
        Folder folder = new Folder()
                .setName("root")
                .setRoot(true)
                .setOwner(rootFolderDTO.getUsername());
        folder = folderRepository.save(folder);
        return folder.getId();
    }

}
