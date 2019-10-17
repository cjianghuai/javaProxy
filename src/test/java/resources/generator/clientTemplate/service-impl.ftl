package ${basePackage}.service.impl;

import cn.netinnet.common.dao.AbstractService;
import cn.netinnet.common.util.SnowFlake;
import ${basePackage}.dao.${modelNameUpperCamel}Mapper;
import ${basePackage}.domain.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
* Created by ${author} on ${date}.
*/
@Service
public class ${modelNameUpperCamel}ServiceImpl extends AbstractService<${modelNameUpperCamel}> implements ${modelNameUpperCamel}Service {
    @Resource
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

    @Override
    public int delLogical(Long id, Long userId) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = new ${modelNameUpperCamel}();
        ${modelNameLowerCamel}.set${keyName}(id);
        //设置逻辑删除
        ${modelNameLowerCamel}.setDel(1);
        //设置修改者id
        ${modelNameLowerCamel}.setModifyUserId(userId);
        return ${modelNameLowerCamel}Mapper.updateByPrimaryKeySelective(${modelNameLowerCamel});
    }

    @Override
    public List<${modelNameUpperCamel}> getLogicalList(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        if(${modelNameLowerCamel} == null) {
            ${modelNameLowerCamel} = new ${modelNameUpperCamel}();
        }
        ${modelNameLowerCamel}.setDel(0);
        return ${modelNameLowerCamel}Mapper.getList(${modelNameLowerCamel});
    }

    @Override
    public int insertSelective(${modelNameUpperCamel} ${modelNameLowerCamel}, Long userId) {
        ${modelNameLowerCamel}.setCreateUserId(userId);
        ${modelNameLowerCamel}.setModifyUserId(userId);
        if(${modelNameLowerCamel}.get${keyName}() == null){
            ${modelNameLowerCamel}.set${keyName}(SnowFlake.getPrimaryKey());
        }
        return ${modelNameLowerCamel}Mapper.insertSelective(${modelNameLowerCamel});
    }

    public int updateByPrimaryKeySelective(${modelNameUpperCamel} ${modelNameLowerCamel}, Long userId){
        ${modelNameLowerCamel}.setModifyUserId(userId);
        return ${modelNameLowerCamel}Mapper.updateByPrimaryKeySelective(${modelNameLowerCamel});
    }

    public int insertBatch(List<${modelNameUpperCamel}> list){
        for(${modelNameUpperCamel} ${modelNameLowerCamel} : list){
            if(${modelNameLowerCamel}.get${keyName}() == null){
                ${modelNameLowerCamel}.set${keyName}(SnowFlake.getPrimaryKey());
            }
        }
        return ${modelNameLowerCamel}Mapper.insertBatch(list);
    }

    @Override
    public Class getClazz(){
        return ${modelNameUpperCamel}.class;
    }
}