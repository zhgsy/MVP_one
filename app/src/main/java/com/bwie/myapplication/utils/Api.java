package com.bwie.myapplication.utils;

public class Api {
      /*
      * 开发环境
      * */
    //public static final String BASE_URL="http://172.17.8.100";
    /*http://172.17.8.100/small/commodity/v1/commodityList
    * 测试环境
    * */
    public static final String BASE_URL="http://172.17.8.100";
    //首页商品信息列表
    public static String SHOPLIST=BASE_URL+"/small/commodity/v1/commodityList";

    //登录页面
    public static String LOGIN=BASE_URL+"/small/user/v1/login";
    //注册页面
    public static String REGISTER=BASE_URL+"/small/user/v1/register";
    //圈子展示
    public static String QZSHOW=BASE_URL+"/small/circle/v1/findCircleList";
    //详情
    public static String XQING=BASE_URL+"/small/commodity/v1/findCommodityDetailsById?commodityId=";


}
