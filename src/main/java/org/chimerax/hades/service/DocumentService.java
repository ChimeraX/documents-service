package org.chimerax.hades.service;

import lombok.AllArgsConstructor;
import org.chimerax.hades.api.dto.document.CreateDocumentDTO;
import org.chimerax.hades.api.dto.document.DataDocumentDTO;
import org.chimerax.hades.api.dto.document.DocumentConverter;
import org.chimerax.hades.api.dto.document.NoDataDocumentDTO;
import org.chimerax.hades.entity.ByteData;
import org.chimerax.hades.entity.Document;
import org.chimerax.hades.repository.DataRepository;
import org.chimerax.hades.repository.DocumentRepository;
import org.chimerax.hades.repository.FolderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 13-May-20
 * Time: 4:47 PM
 */

@Service
@AllArgsConstructor
public class DocumentService {

    private DataRepository dataRepository;
    private DocumentRepository documentRepository;
    private DocumentConverter documentConverter;

    private FolderRepository folderRepository;

    public Optional<NoDataDocumentDTO> findDocumentById(final long id) {
        return documentRepository.findById(id)
                .map(documentConverter::convertToNoDataDocumentDTO);
    }

    public DataDocumentDTO findDocumentWithDataById(final long id) {
        final Optional<Document> optional = documentRepository.findById(id);
        if (optional.isPresent()) {
            final Document document = optional.get();
            final ByteData data = dataRepository.findById(document.getData()).get();
            return documentConverter.convertToDataDocumentDTO(document, data.getData());
        }
        return null;
    }

    public long save(final CreateDocumentDTO documentDTO) {
        Optional<String> folder = folderRepository.findOwnerById(documentDTO.getFolderId());

        if (folder.isPresent()) {
            final String owner = folder.get();

            ByteData byteData = documentConverter.convertToData(documentDTO);

            assert (documentDTO.getSize() == byteData.getData().length);

            byteData = dataRepository.save(byteData);

            Document document = documentConverter.convertToDocument(documentDTO, byteData.getId(), owner);
            document = documentRepository.save(document);

            return document.getId();
        }
        throw new RuntimeException();
    }

    public void deleteById(Long aLong) {
        documentRepository.findById(aLong).ifPresent(document -> {
            dataRepository.deleteById(document.getData());
        });
        documentRepository.deleteById(aLong);
    }
}
