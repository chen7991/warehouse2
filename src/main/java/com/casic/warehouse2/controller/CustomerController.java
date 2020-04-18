package com.casic.warehouse2.controller;

import com.casic.warehouse2.bean.Customer;
import com.casic.warehouse2.common.CustomerQuery;
import com.casic.warehouse2.common.ExportCustomerUtils;
import com.casic.warehouse2.common.Result;
import com.casic.warehouse2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @RequestMapping("/toCoustomerList")
    public String toCoustomerList(){
        return "/views/customer/customerList";
    }

    @RequestMapping("getPageList")
    @ResponseBody
    public Result getPageList(@RequestBody CustomerQuery customerQuery){
        customerQuery.setPage(customerQuery.getPage()-1);
        List<Customer> customerList = customerService.getPageList(customerQuery);
        Long count = customerService.getCount(customerQuery);
        Result result = new Result();
        result.setCode(0);
        result.setCount(count);
        result.setData(customerList);
        return result;
    }

    @RequestMapping("customerLayer")
    public String customerLayer(@RequestParam(required = false,value = "id")String id, Model model){
        Customer customer=customerService.getCustomer(id);
        model.addAttribute("customer",customer);
        return "views/customer/customerLayer";
    }

    @RequestMapping("addOrUpdateCustomer")
    @ResponseBody
    public Result addOrUpdateCustomer(@RequestBody Customer customer){
        boolean flag=false;
        if(customer.getId()==null){
            flag=customerService.addCustomer(customer);
        }else {
            flag=customerService.updateCustomer(customer);
        }
        Result result =new Result();
        if(flag==true){

            result.setMsg("操作成功");
        }else {
            result.setMsg(("操作失败"));
        }
        return result;
    }

    @RequestMapping("deleteCustomerByIds")
    @ResponseBody
    public Result deleteCustomerByIds(@RequestParam List<Long> ids){
        boolean flag=customerService.deleteCustomerByIds(ids);
        Result result = new Result();
        result.setMsg("成功删除"+ids.size()+"条数据");
        return result;

    }
    //导出客户数据,同步下载,拿到数据后响应出去,想成下载的方式
    @RequestMapping("exportCustomer")
    public ResponseEntity<Object> exportCustomer(Customer customer) {
        List<Customer> customers = customerService.allCustomerForList(customer);//使用未分页的方法
        String fileName = "客户数据.xls";
        String sheetName = "客户数据";
        ByteArrayOutputStream bos = ExportCustomerUtils.exportCustomer(customers, sheetName);

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
