package org.chimerax.hades.api.dto.folder;

import lombok.AllArgsConstructor;
import org.chimerax.hades.api.dto.document.DocumentConverter;
import org.chimerax.hades.api.dto.document.NoDataDocumentDTO;
import org.chimerax.hades.entity.Document;
import org.chimerax.hades.entity.Folder;
import org.chimerax.hades.repository.DocumentRepository;
import org.chimerax.hades.repository.FolderRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 13-May-20
 * Time: 5:09 PM
 */

@Component
@AllArgsConstructor
public class FolderConverter {

    private DocumentConverter documentConverter;
    private FolderRepository folderRepository;
    private DocumentRepository documentRepository;

    public FolderDTO toFolderDTO(final Folder folder){
        final List<SubFolderDTO> children = folder.getSubFolders().stream()
                .map(this::toSimpleFolderDTO)
                .collect(Collectors.toList());

        final List<NoDataDocumentDTO> documents =  folder.getDocuments()
                .stream()
                .map(documentConverter::convertToNoDataDocumentDTO)
                .collect(Collectors.toList());

        return new FolderDTO()
                .setId(folder.getId())
                .setName(folder.getName())
                .setCreatedAt(folder.getCreatedAt())
                .setSubFolders(children)
                .setDocuments(documents);
    }

    public SubFolderDTO toSimpleFolderDTO(final Folder folder){
        final SubFolderDTO subFolderDTO = new SubFolderDTO()
                .setId(folder.getId())
                .setName(folder.getName())
                .setCreatedAt(folder.getCreatedAt());

        folderRepository.countAllByParentId(folder.getId()).thenAccept(subFolderDTO::setSubFolders);
        documentRepository.countAllByFolderId(folder.getId()).thenAccept(subFolderDTO::setDocuments);
        return subFolderDTO;
    }

    public Folder toFolder(final CreateFolderDTO folder){
        return new Folder()
                .setName(folder.getName())
                .setParentId(folder.getParentId());
    }

}
