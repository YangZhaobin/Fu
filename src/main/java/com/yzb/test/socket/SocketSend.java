package com.yzb.test.socket;

import java.net.UnknownHostException;

public class SocketSend {

    public static void main(String[] args) throws UnknownHostException {

        SocketTest.tcpClient("Client1", 5000);

    }

}
