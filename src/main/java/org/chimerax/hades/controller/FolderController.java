package org.chimerax.hades.controller;

import lombok.AllArgsConstructor;
import org.chimerax.hades.api.dto.folder.CreateFolderDTO;
import org.chimerax.hades.api.dto.folder.FolderDTO;
import org.chimerax.hades.api.dto.folder.RootFolderDTO;
import org.chimerax.hades.service.FolderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 24-May-20
 * Time: 8:25 PM
 */

@RestController
@RequestMapping("/folders")
@AllArgsConstructor
public class FolderController {

    private FolderService folderService;

    @GetMapping("/{id}")
    public ResponseEntity<FolderDTO> findById(@PathVariable final long id) {
        return ResponseEntity.of(folderService.findById(id));
    }

    @GetMapping("/root")
    public ResponseEntity<FolderDTO> findRootForOwner() {
        return ResponseEntity.of(folderService.findRootForOwner());
    }

    @PostMapping("/root")
    public ResponseEntity<FolderDTO> saveRootForOwner(@RequestBody RootFolderDTO rootFolderDTO) {
        final long id = folderService.saveRootForOwner(rootFolderDTO);
        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }


    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CreateFolderDTO folderDTO) {
        folderService.save(folderDTO);
        return ResponseEntity.ok().build();
    }
}
