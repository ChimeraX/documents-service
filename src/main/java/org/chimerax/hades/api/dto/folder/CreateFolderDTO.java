package org.chimerax.hades.api.dto.folder;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 24-May-20
 * Time: 8:08 PM
 */

@Data
@Accessors(chain = true)
public class CreateFolderDTO {

    private String name;

    @Column(name = "parent_id")
    private Long parentId;
}
