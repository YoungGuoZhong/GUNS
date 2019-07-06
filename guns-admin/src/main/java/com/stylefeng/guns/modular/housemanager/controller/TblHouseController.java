package com.stylefeng.guns.modular.housemanager.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.ToolUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.TblHouse;
import com.stylefeng.guns.modular.housemanager.service.ITblHouseService;

/**
 * 房屋管理控制器
 *
 * @author fengshuonan
 * @Date 2019-07-06 20:34:34
 */
@Controller
@RequestMapping("/tblHouse")
public class TblHouseController extends BaseController {

    private String PREFIX = "/housemanager/tblHouse/";

    @Autowired
    private ITblHouseService tblHouseService;

    /**
     * 跳转到房屋管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "tblHouse.html";
    }

    /**
     * 跳转到添加房屋管理
     */
    @RequestMapping("/tblHouse_add")
    public String tblHouseAdd() {
        return PREFIX + "tblHouse_add.html";
    }

    /**
     * 跳转到修改房屋管理
     */
    @RequestMapping("/tblHouse_update/{tblHouseId}")
    public String tblHouseUpdate(@PathVariable Integer tblHouseId, Model model) {
        TblHouse tblHouse = tblHouseService.selectById(tblHouseId);
        model.addAttribute("item",tblHouse);
        LogObjectHolder.me().set(tblHouse);
        return PREFIX + "tblHouse_edit.html";
    }

    /**
     * 获取房屋管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
      if(ToolUtil.isEmpty(condition)){
          return tblHouseService.selectList(null);
      }else {
          EntityWrapper<TblHouse> entityWrapper =new EntityWrapper<>();
          Wrapper<TblHouse> wrapper = entityWrapper.like("house_user",condition);
          return tblHouseService.selectList(wrapper);
      }
    }

    /**
     * 新增房屋管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TblHouse tblHouse) {
        tblHouseService.insert(tblHouse);
        return SUCCESS_TIP;
    }

    /**
     * 删除房屋管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer tblHouseId) {
        tblHouseService.deleteById(tblHouseId);
        return SUCCESS_TIP;
    }

    /**
     * 修改房屋管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TblHouse tblHouse) {
        tblHouseService.updateById(tblHouse);
        return SUCCESS_TIP;
    }

    /**
     * 房屋管理详情
     */
    @RequestMapping(value = "/detail/{tblHouseId}")
    @ResponseBody
    public Object detail(@PathVariable("tblHouseId") Integer tblHouseId) {
        return tblHouseService.selectById(tblHouseId);
    }
}
