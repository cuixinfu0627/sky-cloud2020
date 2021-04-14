package com.sky.cloud.other.reflect;

import java.lang.reflect.Method;

/**
 * @name: OtherProxyDemo <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2021/4/2 10:24 <tb>
 */
public class OtherProxyDemo {

    public static void main(String[] args) {
        try {
            Class c = Class.forName(args[0]);

            Method m[] = c.getDeclaredMethods();

            for (int i = 0; i < m.length; i++)

                System.out.println(m[i].toString());

        } catch (Throwable e) {
            System.err.println(e);

        }
        alarm();
        reset();

        String title = "厨房41D于2021-04-07 16:33:19发生甲烷告警，请及时处理！" ;
        System.out.println(getEventName(title));
        System.out.println(getEventContext(title));
    }
    public static void alarm(){
        String title = "厨房41D于2021-04-07 16:33:19发生甲烷告警，请及时处理！" ;
        String  deviceName = "41D";
        System.out.println("设备名称：" + deviceName);
        String[]  strs = title.split(deviceName);
        String roomName  = strs[0].toString();
        System.out.println("房间名称：" + roomName);
        String[]  strs2 = title.split("发生");
        String eventName  = strs2[1].toString();
        System.out.println("报警事件：" + eventName);
        System.out.println(roomName+deviceName+"发生"+eventName);
        String[]  strs3 = title.split("于");
        String date1 = strs3[1].toString();
        String[]  strs4 = date1.split("发");
        String date2 = strs4[0].toString();
        System.out.println("时间：" +date2);
    }

    public static void reset(){
        String title = "厨房41D于2021-04-07 16:33:19发生甲烷告警已复位" ;
        String  deviceName = "41D";
        System.out.println("设备名称：" + deviceName);
        String[]  strs = title.split(deviceName);
        String roomName  = strs[0].toString();
        System.out.println("房间名称：" + roomName);
        String[]  strs2 = title.split("发生");
        String eventName  = strs2[1].toString();
        System.out.println("报警事件：" + eventName);
        System.out.println(roomName+deviceName+"发生"+eventName);
        String[]  strs3 = title.split("于");
        String date1 = strs3[1].toString();
        String[]  strs4 = date1.split("发");
        String date2 = strs4[0].toString();
        System.out.println("时间：" +date2);
    }

    public static String getEventName(String title){
        String[]  strs2 = title.split("发生");
        String eventName  = strs2[1].toString();
        String[] split = eventName.split("，");
        return split[0];
    }

    public static String getEventContext(String title){
        String[] str = title.split("发生");
        String eventName  = str[1].toString();
        return eventName;
    }


}
