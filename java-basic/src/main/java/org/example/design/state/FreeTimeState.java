package org.example.design.state;

/**
 * @author: zyh
 * @date: 2023/3/22
 */
/**
 * @Description: 空闲状态只能预订和入住
 */
public class FreeTimeState implements State {

    Room hotelManagement;

    public FreeTimeState(Room hotelManagement){
        this.hotelManagement = hotelManagement;
    }


    @Override
    public void bookRoom() {
        System.out.println("您已经成功预订了...");
        hotelManagement.setState(hotelManagement.getBookedState());   //状态变成已经预订
    }

    @Override
    public void checkInRoom() {
        System.out.println("您已经成功入住了...");
        hotelManagement.setState(hotelManagement.getCheckInState());   //状态变成已经入住
    }

    @Override
    public void checkOutRoom() {
        //不需要做操作
    }

    @Override
    public void unsubscribeRoom() {
        //不需要做操作
    }
}
