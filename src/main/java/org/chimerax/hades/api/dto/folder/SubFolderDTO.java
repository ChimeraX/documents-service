package org.chimerax.hades.api.dto.folder;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 24-May-20
 * Time: 8:08 PM
 */

@Data
@Accessors(chain = true)
public class SubFolderDTO {

    private long id;

    private String name;

    private long createdAt;

    private long documents;

    private long subFolders;

}
