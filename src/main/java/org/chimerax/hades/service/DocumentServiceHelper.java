package org.chimerax.hades.service;

import lombok.AllArgsConstructor;
import lombok.val;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.chimerax.hades.entity.Document;
import org.chimerax.hades.entity.Folder;
import org.chimerax.hades.repository.DocumentRepository;
import org.chimerax.hades.repository.FolderRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 04-Jun-20
 * Time: 4:34 PM
 */

@Aspect
@Service
@AllArgsConstructor
public class DocumentServiceHelper {

    private FolderRepository folderRepository;
    private DocumentRepository documentRepository;

    @Async
    @AfterReturning(value = "execution(* org.chimerax.hades.service.DocumentService.save(..))", returning = "documentId")
    public void afterSuccessfulSave(final long documentId) {
        final Optional<Document> optionalDocument = documentRepository.findById(documentId);
        optionalDocument.ifPresent(document -> computeSizeOfFolder(document.getFolderId()));
    }

    private void computeSizeOfFolder(final long folderId) {
        val optionalFolder = folderRepository.findById(folderId);
        if (optionalFolder.isPresent()) {
            final Folder folder = optionalFolder.get();

            final long size = folder.getDocuments().stream()
                    .map(Document::getSize)
                    .reduce(0L, Long::sum);

            final long totalFiles = folder.getSubFolders().stream()
                    .map(Folder::getTotalFiles)
                    .reduce(0L, Long::sum);

            folder.setSize(size);
            folder.setTotalFiles(totalFiles);

            if (!folder.isRoot()) {
                computeSizeOfFolder(folder.getParentId());
            }
        }
    }
}
