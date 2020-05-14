package org.chimerax.hades.service;

import lombok.AllArgsConstructor;
import lombok.val;
import org.chimerax.hades.api.dto.CreateDocumentDTO;
import org.chimerax.hades.api.dto.DataDocumentDTO;
import org.chimerax.hades.api.dto.DocumentConverter;
import org.chimerax.hades.api.dto.NoDataDocumentDTO;
import org.chimerax.hades.repository.DocumentRepository;
import org.springframework.stereotype.Service;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 13-May-20
 * Time: 4:47 PM
 */

@Service
@AllArgsConstructor
public class DocumentService {

    private DocumentRepository documentRepository;
    private DocumentConverter documentConverter;

    public NoDataDocumentDTO findDocumentById(final long id) {
        val document = documentRepository.findById(id);
        return document
                .map(documentConverter::convertToNoDataDocumentDTO)
                .orElseThrow(RuntimeException::new);
    }

    public DataDocumentDTO findDocumentWithDataById(final long id) {
        val document = documentRepository.findById(id);
        return document
                .map(documentConverter::convertToDataDocumentDTO)
                .orElseThrow(RuntimeException::new);
    }

    public long save(final CreateDocumentDTO document) {
        return documentRepository.save(documentConverter.convertToDocument(document)).getId();
    }
}
