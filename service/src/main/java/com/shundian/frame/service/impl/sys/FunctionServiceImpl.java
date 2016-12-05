package com.shundian.frame.service.impl.sys;


import com.shundian.frame.api.sys.FunctionService;
import com.shundian.frame.envm.ProjectTypeEnum;
import com.shundian.frame.mapper.sys.FunctionMapper;
import com.shundian.frame.mapper.sys.FunctionModuleMapper;
import com.shundian.frame.model.sys.Function;
import com.shundian.frame.model.sys.FunctionModule;
import com.shundian.lib.function.FunctionType;
import com.shundian.lib.function.ModuleType;
import com.shundian.lib.util.EnumUtil;
import com.shundian.lib.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
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
            Function function = new Function();
            function.setUid(functionType.getId());
            List<Function> funcs = mapper.select(function);
            if (funcs == null || funcs.isEmpty() || funcs.size() == 0) {
                function.setClazz(functionClass.getName());
                function.setName(functionType.getName());
                function.setUiSref(functionType.getUiSref());
                function.setProject(EnumUtil.valueOf(ProjectTypeEnum.class, String.valueOf(functionType.getProject())));
                function.setIsShow(true);
                function.setOrder(new Random().nextInt(20));
                function.setId(UuidUtil.getUUID());
                mapper.insert(function);
            } else {
                Function dbFunction = null;
                if (funcs.size() > 1) {
                    throw new Exception("功能 " + functionType.getName() + " uid 重复！");
                }
                for (Function func : funcs) {
                    if (func.getClazz().equals(functionClass.getName())) {
                        dbFunction = func;
                    }
                }
                if (dbFunction != null) {
                    function.setId(dbFunction.getId());
                    if ((!functionType.getName().equals(dbFunction.getName())
                            || !functionType.getUiSref().equals(dbFunction.getUiSref()))) {
                        function.setClazz(functionClass.getName());
                        function.setName(functionType.getName());
                        function.setUiSref(functionType.getUiSref());
                        mapper.updateFunction(function);
                    }
                } else {
                    throw new Exception("功能 " + functionType.getName() + " uid 重复！");
                }
            }
            for (Class<? extends ModuleType> moduleClass : moduleClasses) {
                ModuleType module = moduleClass.newInstance();
                FunctionModule functionModule = new FunctionModule();
                functionModule.setFunctionId(function.getId());
                functionModule.setName(module.getName());
                functionModule.setKey(module.getKey());
                functionModule.setClazz(moduleClass.getName());
                functionModule.setUid(module.getId());
                if (functionModuleMapper.selectDuplicate(functionModule) > 0) {
                    throw new Exception("模块 " + functionModule.getName() + " uid 重复！");
                }
                FunctionModule select = new FunctionModule();
                select.setFunctionId(functionModule.getFunctionId());
                select.setUid(functionModule.getUid());
                List<FunctionModule> select1 = functionModuleMapper.select(select);
                if (select1 == null || select1.isEmpty() || select1.size() == 0) {
                    functionModuleMapper.insert(functionModule);
                } else if (select1.size() == 1) {
                    FunctionModule selectModule = select1.get(0);
                    if (!functionModule.getKey().equals(selectModule.getKey()) ||
                            !functionModule.getName().equals(selectModule.getName())) {
                        functionModuleMapper.updateModule(functionModule);
                    }
                } else {
                    throw new Exception("模块 " + functionModule.getName() + " 错误！");
                }
            }

        }
    }

}
