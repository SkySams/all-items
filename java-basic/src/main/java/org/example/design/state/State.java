package org.example.design.state;

/**
 * 状态类
 * @author: zyh
 * @date: 2023/3/22
 */
public interface State {

    /**
     * 预定房间
     */
    void bookRoom();


    /**
     * 退订房间
     */
    void unsubscribeRoom();

    /**
     * 入住
     */
    void checkInRoom();

    /**
     * 退房
     */
    void checkOutRoom();
}
