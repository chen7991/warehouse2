package com.casic.warehouse2.controller;

import com.casic.warehouse2.bean.Customer;
import com.casic.warehouse2.bean.Goods;
import com.casic.warehouse2.bean.Provider;
import com.casic.warehouse2.common.*;
import com.casic.warehouse2.service.GoodsService;
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

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ProviderService providerService;
    /**
     * 跳转到商品管理
     */
    @RequestMapping("toGoodsManager")
    public String toGoodsManager() {
        return "views/goods/goodsManager";
    }
    /**
     * 加载商品
     * @param goodsQuery
     * @return
     */
    @RequestMapping("loadAllGoods")
    @ResponseBody
    public Result loadAllGoods(@RequestBody GoodsQuery goodsQuery){
        goodsQuery.setPage(goodsQuery.getPage()-1);
        List<Goods> goodsList = goodsService.getPageList(goodsQuery);
        Long count = goodsService.getCount(goodsQuery);
        for (Goods goods : goodsList) {
            Provider provider = providerService.selectByPrimaryKey(goods.getProviderid());
            if (null !=provider){
                goods.setProvidername(provider.getProvidername());
            }
        }
        Result result = new Result();
        result.setCode(0);
        result.setCount(count);
        result.setData(goodsList);
        return result;
    }
    /**
     * 添加商品addGoods
     */
    @RequestMapping("addGoods")
    @ResponseBody
    public Result addGoods(Goods goods){
        Result result =new Result();
        try{
            if(goods.getGoodsimg() != null && goods.getGoodsimg().endsWith("_picture")){
                String newName = AppFileUtils.renameFile(goods.getGoodsimg());
                goods.setGoodsimg(newName);
            }
            goodsService.insert(goods);
            result.setMsg("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setMsg(("添加失败"));
        }
        return result;
    }
    /**
     * 修改商品updateGoods
     */
    @RequestMapping("updateGoods")
    @ResponseBody
    public Result updateGoods(Goods goods){
        Result result =new Result();
        try{
            //说明是默认图片
            if(!(goods.getGoodsimg() != null && goods.getGoodsimg().equals("images/default.jpg"))){
                if(goods.getGoodsimg().endsWith("_picture")){
                    String newName = AppFileUtils.renameFile(goods.getGoodsimg());
                    goods.setGoodsimg(newName);
                    //删除以前图片
                    String oldPath =goodsService.selectByPrimaryKey(goods.getId()).getGoodsimg();

                    AppFileUtils.removeFileByPath(oldPath);
                }
            }
            goodsService.updateByPrimaryKey(goods);
            result.setMsg("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setMsg(("修改失败"));
        }
        return result;
    }
    /**
     * 删除商品deleteGoods
     */
    @RequestMapping("deleteGoods")
    @ResponseBody
    public Result deleteGoods(Integer id,String goodsimg){
        Result result = new Result();
        try{
            AppFileUtils.removeFileByPath(goodsimg);
            goodsService.deleteByPrimaryKey(id);
            result.setMsg("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setMsg("删除失败");
        }
        return result;
    }
    /**
     * 导出功能exportGoods
     */
    @RequestMapping("exportGoods")
    public ResponseEntity<Object> exportCustomer(Goods goods) {
        List<Goods> goodss = goodsService.allGoodsForList(goods);//使用未分页的方法
        for (Goods gs : goodss) {
            Provider provider = providerService.selectByPrimaryKey(gs.getProviderid());
            if (null !=provider){
                gs.setProvidername(provider.getProvidername());
            }
        }
        String fileName = "商品数据.xls";
        String sheetName = "商品数据";
        ByteArrayOutputStream bos = ExportGoodsUtils.exportGoods(goodss, sheetName);

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
