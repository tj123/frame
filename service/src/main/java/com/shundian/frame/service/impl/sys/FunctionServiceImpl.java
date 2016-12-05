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

	public <F extends FunctionType<?>, M extends ModuleType> void insertUpdateModule(Class<F> functionClass, Class<M> moduleClass) throws Exception {
		Function function = new Function();
		F functionType = functionClass.newInstance();
		function.setClazz(functionClass.getName());
		function.setName(functionType.getName());
		function.setUiSref(functionType.getUiSref());
		function.setProject(EnumUtil.valueOf(ProjectTypeEnum.class,String.valueOf(functionType.getProject())));
		function.setShow(true);
		function.setOrder(new Random().nextInt(20));
		function.setUid(functionType.getId());
		String functionId = UuidUtil.getUUID();
		function.setId(functionId);
		if(mapper.selectDuplicate(function) > 0){
			throw new Exception(function.getName() + " 功能 uid 重复！");
		}
		mapper.insertUpdate(function);
		functionId = function.getId();
		M module = moduleClass.newInstance();
		FunctionModule functionModule = new FunctionModule();
		functionModule.setFunctionId(functionId);
		functionModule.setName(module.getName());
		functionModule.setKey(module.getKey());
		functionModule.setUid(module.getId());
		functionModule.setId(UuidUtil.getUUID());
		functionModuleMapper.insertUpdate(functionModule);
	}

	public <F extends FunctionType<?>, M extends ModuleType> void insert(Map<Class<F>, List<Class<M>>> functions) throws Exception {
		for (Map.Entry<Class<F>, List<Class<M>>> entry : functions.entrySet()) {
			Class<F> functionClass = entry.getKey();
			List<Class<M>> moduleClasses = entry.getValue();
			F functionType = functionClass.newInstance();
			Function function = new Function();
			function.setClazz(functionClass.getName());
			function.setUid(functionType.getId());



			function.setName(functionType.getName());
			function.setUiSref(functionType.getUiSref());
			function.setProject(EnumUtil.valueOf(ProjectTypeEnum.class,String.valueOf(functionType.getProject())));
			function.setShow(true);
			function.setOrder(new Random().nextInt(20));

			String functionId = UuidUtil.getUUID();
			function.setId(functionId);

		}
	}

}
