package org.example.design.adaptation.socket.obj;

import org.example.design.adaptation.socket.AbstractSocket;

/**
 * @author: zyh
 * @date: 2023/2/8
 */
public class AdapterUsbSocket extends AbstractSocket{

    public USBInterface usb;

    public AdapterUsbSocket(USBInterface usb) {
        this.usb = usb;
    }

    @Override
    public void contact() {
        this.usb.contact();
    }
}
