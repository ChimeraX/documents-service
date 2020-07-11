package org.chimerax.hades.api.dto.folder;

import lombok.Data;
import lombok.experimental.Accessors;
import org.chimerax.hades.api.dto.document.NoDataDocumentDTO;

import java.util.List;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 24-May-20
 * Time: 8:08 PM
 */

@Data
@Accessors(chain = true)
public class RootFolderDTO {

    String username;
}
