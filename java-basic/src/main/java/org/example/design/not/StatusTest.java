package org.example.design.not;

/**
 * @author: zyh
 * @date: 2023/3/22
 */
public class StatusTest {

    //空闲状态
    public static final int FREEMTIME_STATE = 0;
    //已预订状态
    public static final int BOOKED_STATE = 1;
    //入住状态
    public static final int CHECKIN_STATE = 2;

    //初始状态
    int state = FREEMTIME_STATE;

    // 房间数量
    static int count = 2;


    /**
     * @desc  预订
     */
    public void bookedRoom(){
        if (state == FREEMTIME_STATE){
            if (count > 0){
                System.out.println("空闲房间，完成预订...");
                //改变状态：已预订
                state =  BOOKED_STATE;
                count --;
                //房间预订完了,提示客户没有房源了
                if(count == 0){
                    System.out.println("不好意思,房间已经预订完,欢迎您下次光临...");
                }
            } else {
                System.out.println("不好意思,已经没有房间了....");
            }
        }
        else if (state == BOOKED_STATE){
            System.out.println("该房间已经被预订了...");
        }
        else if (state == CHECKIN_STATE){
            System.out.println("该房间已经有人入住了...");
        }
    }

    /**
     * @desc 入住
     */
    public void checkInRoom(){
        if (state == FREEMTIME_STATE){
            if (count > 0){
                System.out.println("空闲房间，完成预订...");
                //改变状态：已预订
                state =  BOOKED_STATE;
                count --;
                //房间预订完了,提示客户没有房源了
                if(count == 0){
                    System.out.println("不好意思,房间已经预订完,欢迎您下次光临...");
                }
            } else {
                System.out.println("不好意思,已经没有房间了....");
            }
        }
        else if (state == BOOKED_STATE){
            // 如果该房间是您预订的
            if(true){
                System.out.println("入住....");
                state = CHECKIN_STATE;
            }
            else{
                System.out.println("您没有预订该房间,请先预订...");
            }
        }
        else if (state == CHECKIN_STATE){
            System.out.println("该房间已经入住了...");
        }
    }

    /**
     * @desc 退订
     */
    public void unsubscribeRoom(){
        if(state == FREEMTIME_STATE){}
        else if(state == CHECKIN_STATE){}
        else if(state == BOOKED_STATE){
            System.out.println("已退订房间...");
            state = FREEMTIME_STATE;
            count ++;
        }
    }

    /**
     * @desc 退房
     */
    public void checkOutRoom(){
        if(state == FREEMTIME_STATE){}
        else if(state == BOOKED_STATE){}
        else if(state == CHECKIN_STATE){
            System.out.println("已退房..");
            state = FREEMTIME_STATE;
            count++;
        }
    }



}
