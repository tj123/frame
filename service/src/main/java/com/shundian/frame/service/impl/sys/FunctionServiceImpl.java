package com.shundian.frame.service.impl.sys;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.shundian.frame.api.entity.sys.Function;
import com.shundian.frame.api.entity.sys.FunctionModule;
import com.shundian.frame.api.po.sys.FunctionPo;
import com.shundian.frame.api.service.sys.FunctionService;
import com.shundian.frame.common.PageUtil;
import com.shundian.frame.api.envm.ProjectTypeEnum;
import com.shundian.frame.mapper.sys.FunctionMapper;
import com.shundian.frame.mapper.sys.FunctionModuleMapper;
import com.shundian.frame.api.po.sys.FunctionModulePo;
import com.shundian.lib.Page;
import com.shundian.lib.PageResult;
import com.shundian.lib.exception.CannotConvertException;
import com.shundian.lib.function.FunctionType;
import com.shundian.lib.function.ModuleType;
import com.shundian.lib.util.EnumUtil;
import com.shundian.lib.util.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Slf4j
public class FunctionServiceImpl implements FunctionService {

    @Autowired
    private FunctionMapper mapper;

    @Autowired
    private FunctionModuleMapper functionModuleMapper;

    /**
     * 插入数据并判断是否存在是否重复
     */
    public void insert(Map<Class<? extends FunctionType<?>>, List<Class<? extends ModuleType>>> functions) throws Exception {
        for (Map.Entry<Class<? extends FunctionType<?>>, List<Class<? extends ModuleType>>> entry : functions.entrySet()) {
            Class<? extends FunctionType> functionClass = entry.getKey();
            List<Class<? extends ModuleType>> moduleClasses = entry.getValue();
            FunctionType<?> functionType = functionClass.newInstance();
            FunctionPo functionPo = new FunctionPo();
            functionPo.setUid(functionType.getId());
            List<FunctionPo> funcs = mapper.select(functionPo);
            if (funcs == null || funcs.isEmpty() || funcs.size() == 0) {
                functionPo.setClazz(functionClass.getName());
                functionPo.setName(functionType.getName());
                functionPo.setUiSref(functionType.getUiSref());
                functionPo.setProject(EnumUtil.valueOf(ProjectTypeEnum.class, String.valueOf(functionType.getProject())));
                functionPo.setIsShow(true);
                functionPo.setOrder(new Random().nextInt(20));
                functionPo.setId(UuidUtil.getUUID());
                mapper.insert(functionPo);
            } else {
                FunctionPo dbFunctionPo = null;
                if (funcs.size() > 1) {
                    throw new Exception("功能 " + functionType.getName() + " uid 重复！");
                }
                for (FunctionPo func : funcs) {
                    if (func.getClazz().equals(functionClass.getName())) {
                        dbFunctionPo = func;
                    }
                }
                if (dbFunctionPo != null) {
                    functionPo.setId(dbFunctionPo.getId());
                    if ((!functionType.getName().equals(dbFunctionPo.getName())
                            || !functionType.getUiSref().equals(dbFunctionPo.getUiSref()))) {
                        functionPo.setClazz(functionClass.getName());
                        functionPo.setName(functionType.getName());
                        functionPo.setUiSref(functionType.getUiSref());
                        mapper.updateFunction(functionPo);
                    }
                } else {
                    throw new Exception("功能 " + functionType.getName() + " uid 重复！");
                }
            }
            for (Class<? extends ModuleType> moduleClass : moduleClasses) {
                ModuleType module = moduleClass.newInstance();
                if (module.getId() == 0) {
                    throw new Exception("模块 id 0 为保留 id！");
                }
                FunctionModulePo functionModulePo = new FunctionModulePo();
                functionModulePo.setFunctionId(functionPo.getId());
                functionModulePo.setName(module.getName());
                functionModulePo.setKey(module.getKey());
                functionModulePo.setClazz(moduleClass.getName());
                functionModulePo.setUid(module.getId());
                if (functionModuleMapper.selectDuplicate(functionModulePo) > 0) {
                    throw new Exception("模块 " + functionModulePo.getName() + " uid 重复！");
                }
                FunctionModulePo select = new FunctionModulePo();
                select.setFunctionId(functionModulePo.getFunctionId());
                select.setUid(functionModulePo.getUid());
                List<FunctionModulePo> select1 = functionModuleMapper.select(select);
                if (select1 == null || select1.isEmpty() || select1.size() == 0) {
                    functionModuleMapper.insert(functionModulePo);
                } else if (select1.size() == 1) {
                    FunctionModulePo selectModule = select1.get(0);
                    if (!functionModulePo.getKey().equals(selectModule.getKey()) ||
                            !functionModulePo.getName().equals(selectModule.getName())) {
                        functionModuleMapper.updateModule(functionModulePo);
                    }
                } else {
                    throw new Exception("模块 " + functionModulePo.getName() + " 错误！");
                }
            }

        }
    }

    @Override
    public List<Map<String,Object>> list(Map<String,String> conditions) throws Exception {
        List<Map<String, Object>> list = mapper.list(conditions);
        list.forEach(map -> {
            try {
                map.put("project", EnumUtil.valueOf(ProjectTypeEnum.class, (String) map.get("project")).getName());
            } catch (CannotConvertException e) {
                log.error("枚举错误", e);
            }
            String module  = (String)map.get("module");
            try {
                List<Object> mds = new ObjectMapper().readValue(module,List.class);
                for (Object md : mds) {
                    Map<String, Object> objectMap = (Map<String, Object>) md;
                    objectMap.put("name",objectMap.get("n"));
                    objectMap.remove("n");
                    objectMap.put("id",Short.valueOf((String) objectMap.get("i")));
                    objectMap.remove("i");
                    objectMap.put("key", objectMap.get("k"));
                    objectMap.remove("k");
                }
                map.put("module",mds);
            } catch (IOException e) {
                log.error("解析模块错误",e);
                map.put("module","[]");
            }
        });
        return list;
    }


    @Override
    public PageResult<Map<String, Object>> list(Page page) throws Exception {
        PageUtil<Map<String, Object>> util = new PageUtil<>();
        util.startPage(page);
        return util.assembleResult(list(page.getKeywords()));
    }

    @Override
    public Map<String, Object> list(String id) throws Exception {
        if (id == null || id.trim().equals("")) {
            return new HashMap<String, Object>();
        }
        Map<String,String> mp = new HashMap<String,String>();
        mp.put("id",id);
        List<Map<String, Object>> list = list(mp);
        if (list != null && !list.isEmpty() && list.size() > 0) {
            return list.get(0);
        }
        return new HashMap<String, Object>();
    }

    @Override
    public List<FunctionModule> findModules() throws Exception {
        return functionModuleMapper.selectFunctionModule(null);
    }

    @Override
    public List<Function> findFunctions() throws Exception {
        return mapper.selectFunction(null);
    }

}
