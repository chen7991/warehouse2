package com.casic.warehouse2.controller;


import com.casic.warehouse2.bean.Customer;
import com.casic.warehouse2.bean.Provider;
import com.casic.warehouse2.common.ExportCustomerUtils;
import com.casic.warehouse2.common.ExportProviderUtils;
import com.casic.warehouse2.common.ProviderQuery;
import com.casic.warehouse2.common.Result;
import com.casic.warehouse2.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 *
 *  供应商前端控制器
 */
@Controller
@RequestMapping("/provider")
public class ProviderController {

	@Autowired
	private ProviderService providerService;
	/**
	 * 跳转到供应商管理
	 */
	@RequestMapping("toProviderManager")
	public String toProviderManager() {
		return "/views/provider/providerManager";
	}

	/**
	 * 加载供应商
	 * @param providerQuery
	 * @return
	 */
	@RequestMapping("loadAllProvider")
	@ResponseBody
	public Result loadAllProvider(@RequestBody ProviderQuery providerQuery){
		providerQuery.setPage(providerQuery.getPage()-1);
		List<Provider> customerList = providerService.getPageList(providerQuery);
		Long count = providerService.getCount(providerQuery);
		Result result = new Result();
		result.setCode(0);
		result.setCount(count);
		result.setData(customerList);
		return result;
	}
	/**
	 * 添加供应商addProvider
	 */
	@RequestMapping("addProvider")
	@ResponseBody
	public Result addProvider(Provider provider){
		Result result =new Result();
		try{
			providerService.insert(provider);
			result.setMsg("添加成功");
		}catch (Exception e){
			e.printStackTrace();
			result.setMsg(("添加失败"));
		}
		return result;
	}
	/**
	 * 修改供应商updateProvider
	 */
	@RequestMapping("updateProvider")
	@ResponseBody
	public Result updateProvider(Provider provider){
		Result result =new Result();
		try{
			providerService.updateByPrimaryKey(provider);
			result.setMsg("修改成功");
		}catch (Exception e){
			e.printStackTrace();
			result.setMsg(("修改失败"));
		}
		return result;
	}
	/**
	 * 删除供应商deleteProvider
	 */
	@RequestMapping("deleteProvider")
	@ResponseBody
	public Result deleteProvider(@RequestParam Integer id){
		Result result = new Result();
		try{
			providerService.deleteByPrimaryKey(id);
			result.setMsg("删除成功");
		}catch (Exception e){
			e.printStackTrace();
			result.setMsg("删除失败");
		}
		return result;
	}
	/**
	 * 批量删除供应商batchDeleteProvider
	 */
	@RequestMapping("batchDeleteProvider")
	@ResponseBody
	public Result batchDeleteProvider(@RequestParam Integer[] ids){
		Result result = new Result();
		try{
			for (Integer id : ids) {
				providerService.deleteByPrimaryKey(id);
			}
			result.setMsg("删除成功");
		}catch (Exception e){
			e.printStackTrace();
			result.setMsg("删除失败");
		}
		return result;
	}
	/**
	 * 加载所有可用的供应商providerForSelect
	 */
	@RequestMapping("providerForSelect")
	@ResponseBody
	public Result providerForSelect(){
		Result result = new Result();
		List<Provider> list=providerService.selectAvailable();
		result.setData(list);
		return result;
	}
	/**
	 * 导出功能exportProvider
	 */
	@RequestMapping("exportProvider")
	public ResponseEntity<Object> exportCustomer(Provider provider) {
		List<Provider> providers = providerService.allProviderForList(provider);//使用未分页的方法
		String fileName = "供应商数据.xls";
		String sheetName = "供应商数据";
		ByteArrayOutputStream bos = ExportProviderUtils.exportProvider(providers, sheetName);

		try {
			fileName = URLEncoder.encode(fileName, "UTF-8");//处理文件名乱码
			//创建响应头对象
			HttpHeaders httpHeaders = new HttpHeaders();
			//封装响应内容信息
			httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			//设置下载文件名称
			httpHeaders.setContentDispositionFormData("attachment", fileName);
			return new ResponseEntity<Object>(bos.toByteArray(), httpHeaders, HttpStatus.CREATED);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}

