package com.yzb.test.socket;

import java.io.*;
import java.net.*;

public class SocketTest {

    public static void main(String[] args) {

    }

    public static void ipTest() throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        // 主机名
        System.out.println(ip.getHostName());
        // 获取主机IP
        System.out.println(ip.getHostAddress());

        InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
    }

    public static void tcpClient(String content, int sleepTime) throws UnknownHostException {
        int PORT = 10086;
        InetAddress IP = InetAddress.getLocalHost();
        Socket socket = null;
        try {
            // 建立连接
            socket = new Socket(IP, PORT);

            Thread.sleep(sleepTime);

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void tcpServer() {
        int PORT = 10086;
        ServerSocket serverSocket = null;
        InputStream inputStream = null;
        try {
            /**
             *  @param port 指定服务器要绑定的端口（服务器要监听的端口），
             *  @param backlog 指定客户连接请求队列的长度
             *  @param bindAddr 指定服务器要绑定的IP地址
             */
            serverSocket = new ServerSocket(PORT,3 );
            // ServerSocket的accept()方法从连接请求队列中取出一个客户的连接请求，然后创建与客户连接的Socket对象，并将它返回。
            // 如果队列中没有连接请求，accept()方法就会一直等待，直到接收到了连接请求才返回。
            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();
                System.out.println("Server Accept   " + System.currentTimeMillis());
                inputStream = socket.getInputStream();
                byte[] buf = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(buf)) != -1) {
                    System.out.println(new String(buf, 0, len) + "   " + System.currentTimeMillis());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void udpReceive() {
        int PORT = 10086;
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(PORT);
            while (true) {
                byte[] buf = new byte[1024];
                DatagramPacket dp = new DatagramPacket(buf, buf.length);
                ds.receive(dp);
                byte[] data = dp.getData();
                System.out.println(new String(data, 0, dp.getLength()));
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ds != null) {
                ds.close();
            }
        }
    }

    public static void udpSend() throws UnknownHostException {
        int PORT = 10086;
        InetAddress IP = InetAddress.getLocalHost();
        DatagramSocket ds = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            while ((line = br.readLine()) != null && !"!".equals(line)) {
                DatagramPacket dp = new DatagramPacket(
                        line.getBytes(),
                        line.length(),
                        IP,
                        PORT
                );
                ds.send(dp);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ds != null) {
                ds.close();
            }
        }
    }

}
