package org.chimerax.hades.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 17-May-20
 * Time: 12:14 AM
 */

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String hello() {
        return "hello";
    }
}
