package org.chimerax.hades.api.dto.document;

import org.chimerax.hades.entity.ByteData;
import org.chimerax.hades.entity.Document;
import org.springframework.stereotype.Component;

import java.util.Base64;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 13-May-20
 * Time: 5:09 PM
 */

@Component
public class DocumentConverter {

    public NoDataDocumentDTO convertToNoDataDocumentDTO(final Document document) {
        return new NoDataDocumentDTO()
                .setId(document.getId())
                .setCreatedAt(document.getCreatedAt())
                .setName(document.getName())
                .setSize(document.getSize())
                .setType(document.getType());
    }

    public DataDocumentDTO convertToDataDocumentDTO(final Document document, final byte[] data) {
        return new DataDocumentDTO()
                .setData(data)
                .setName(document.getName())
                .setSize(document.getSize())
                .setType(document.getType());
    }

    private byte[] decode(final byte[] data) {
        final String dataURL = new String(data);
        final int dataStartIndex = dataURL.indexOf(",") + 1;
        return Base64.getDecoder().decode(dataURL.substring(dataStartIndex));
    }

    public Document convertToDocument(final CreateDocumentDTO document, final long data, final String owner) {
        return new Document()
                .setData(data)
                .setOwner(owner)
                .setFolderId(document.getFolderId())
                .setName(document.getName())
                .setSize(document.getSize())
                .setType(document.getType());
    }

    public ByteData convertToData(final CreateDocumentDTO document) {
        return new ByteData().setData(decode(document.getData().getBytes()));
    }
}
