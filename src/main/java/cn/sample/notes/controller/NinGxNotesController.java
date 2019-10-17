package cn.sample.notes.controller;

import cn.sample.notes.service.NinGxNotesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by chenjh on 2019/7/26.
 */
@RestController
@RequestMapping("/ninGxNotes")
public class NinGxNotesController {
    @Resource
    private NinGxNotesService ninGxNotesService;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Object list(){
        return ninGxNotesService.query();
    }
}
