package com.example;

/**
 * Created by sahara on 2017/4/24.
 */

public class StringDemo {

    public static void main(String[] args){
//        String sms = "【免费送1G】安徽移动手厅新用户见面送壕礼，点击 ah.10086.cn/dt/khd 。\n" +
//                "        您好！截至04月24日17时10分，您本月已使用移动数据流量1G241.21M，套餐内流量剩余2G386.52M，其中国内流量剩余2G386.52M(含国内\n" +
//                "结转流量386.52M)。\n" +
//                "        回复序号办理:\n" +
//                "        661.流量包\n" +
//                "        662.流量加油包\n" +
//                "        663.夜间流量包\n" +
//                "        664.季包/半年包\n" +
//                "        665.假日流量包\n" +
//                "        667.国内流量小时包\n" +
//                "        668.查询共享明细\n" +
//                "        669.查询上月结转流量\n" +
//                "        660.查询特殊流量\n" +
//                "        【中国移动】";
//        String sms = "尊敬的用户，截止到2017年05月10日\n" +
//                " 本月已使用流量（不含免费流量） 1224.83MB\n" +
//                " 本月产生的总流量（含免费流量） 1224.83MB\n" +
//                " 套餐内流量 1224.83MB\n" +
//                " 套餐内剩余流量 311.18MB\n" +
//                " 本数据仅供参考，详情以当地营业厅查询为准。\n" +
//                " 回复“5083”，查看流量半年包余量。\n" +
//                " 回复“2082”，查看套餐余量。\n" +
//                " 【发短信太麻烦？使用手机营业厅，查询、交费、办理更简单！下载点击：http://u.10010.cn/khddx 】";
        String sms = "【流量提醒】截止7月11日16时34分，本月您已使用的国内流量情况如下：国内通用流量共908.98M，已使用37.57M，还剩余871.4M。回复6606给流量加油，资费更优惠！具体流量资源有效期详见掌厅【中国移动】";
//        parseSms(sms);
//        parseSmsLT(sms);
        parseSmsYD_SH(sms);
        // 创建一个数值格式化对象

//        NumberFormat numberFormat = NumberFormat.getInstance();
//        // 设置精确到小数点后2位
//        numberFormat.setMaximumFractionDigits(2);
//        String result = numberFormat.format((float) 5 / (float) 9 * 100);
//        System.out.print(Float.valueOf(result).intValue());
    }

    public static void parseSmsYD_SH(String sms){
        String[] ms = sms.split("M");
        String substring = ms[2].substring(4, ms[2].length());
        System.out.print(substring);
    }


    /**
     * 解析联通短信内容
     */
    public static void parseSmsLT(String msg){
        String[] ms = msg.split("MB");
        String sms = ms[3].substring(10,ms[3].length());
        System.out.print(Float.valueOf(sms));
    }

    /**
     * 解析短信内容
     */
    public static void parseSms(String msg){
        String[] ms = msg.split("M");
        String sms = ms[1].substring(8,ms[1].length());
        System.out.print(sms+"\n");
        String[] gs = sms.split("G");
        if (gs.length>1){
            String gss = gs[0]+gs[1];
            System.out.print(Float.valueOf(gss));
        }else{
            System.out.print(Float.valueOf(gs[0]));
        }
    }
}
