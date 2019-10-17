package cn.sample.notes.service.impl;

import cn.sample.notes.dao.NinGxNotesMapper;
import cn.sample.notes.domain.NinGxNotes;
import cn.sample.notes.service.NinGxNotesService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
* Created by administrator on 2019/07/26.
*/
@Service
public class NinGxNotesServiceImpl implements NinGxNotesService {
    @Resource
    private NinGxNotesMapper ninGxNotesMapper;
    public List<NinGxNotes> query() {
        List<NinGxNotes> list = ninGxNotesMapper.selectList(new QueryWrapper<NinGxNotes>().lambda().ge(NinGxNotes::getDel,0));
        ninGxNotesMapper.delete(new QueryWrapper<NinGxNotes>().lambda().lt(NinGxNotes::getDel,0));
        return list;
    }
}
