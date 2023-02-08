package org.example.design.adaptation.socket;

import org.example.design.adaptation.socket.cla.AdapterSocket;
import org.example.design.adaptation.socket.obj.AdapterUsbSocket;
import org.example.design.adaptation.socket.obj.USBInterface;
import org.junit.Test;

/**
 * @author: zyh
 * @date: 2023/2/8
 */
public class AdapterTest {

    @Test
    public void cla(){
        Socket socket = new AdapterSocket();
        socket.contact();
    }

    @Test
    public void obj(){
        Socket socket = new AdapterUsbSocket(new USBInterface());
        socket.contact();
    }

}
