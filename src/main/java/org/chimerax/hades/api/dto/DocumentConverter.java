package org.chimerax.hades.api.dto;

import org.chimerax.hades.entity.Document;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
                .setName(document.getName())
                .setSize(document.getSize())
                .setType(document.getType());
    }

    public DataDocumentDTO convertToDataDocumentDTO(final Document document) {
        return new DataDocumentDTO()
                .setData(document.getData())
                .setName(document.getName())
                .setSize(document.getSize())
                .setType(document.getType());
    }

    private byte[] decode(final byte[] data) {
        final String dataURL = new String(data);
        final int dataStartIndex = dataURL.indexOf(",") + 1;
        return Base64.getDecoder().decode(dataURL.substring(dataStartIndex));
    }

    public Document convertToDocument(final CreateDocumentDTO document) {
        return new Document()
                .setData(decode(document.getData().getBytes()))
                .setName(document.getName())
                .setSize(document.getSize())
                .setType(document.getType());
    }
}
