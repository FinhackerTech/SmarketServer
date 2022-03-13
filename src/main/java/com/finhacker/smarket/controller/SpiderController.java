package com.finhacker.smarket.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for spider.
 */
@RestController
@RequestMapping(value = "/local/spider")
public class SpiderController {

    /**
     * Spider call the server it has finished.
     * Content type: json
     * @param enterpriseList The list of ids of enterprises which has been added into the data source.
     */
    @PostMapping("/api/finish")
    @ResponseBody
    public void finish(@RequestBody List<Integer> enterpriseList) {
        //TODO
    }

}
