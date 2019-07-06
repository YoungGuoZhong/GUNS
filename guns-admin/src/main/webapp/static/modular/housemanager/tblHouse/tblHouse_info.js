/**
 * 初始化房屋管理详情对话框
 */
var TblHouseInfoDlg = {
    tblHouseInfoData : {}
};

/**
 * 清除数据
 */
TblHouseInfoDlg.clearData = function() {
    this.tblHouseInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TblHouseInfoDlg.set = function(key, val) {
    this.tblHouseInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TblHouseInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TblHouseInfoDlg.close = function() {
    parent.layer.close(window.parent.TblHouse.layerIndex);
}

/**
 * 收集数据
 */
TblHouseInfoDlg.collectData = function() {
    this
    .set('id')
    .set('houseUser')
    .set('houseAddress')
    .set('houseDate')
    .set('houseDesc');
}

/**
 * 提交添加
 */
TblHouseInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tblHouse/add", function(data){
        Feng.success("添加成功!");
        window.parent.TblHouse.table.refresh();
        TblHouseInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tblHouseInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TblHouseInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tblHouse/update", function(data){
        Feng.success("修改成功!");
        window.parent.TblHouse.table.refresh();
        TblHouseInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tblHouseInfoData);
    ajax.start();
}

$(function() {

});
