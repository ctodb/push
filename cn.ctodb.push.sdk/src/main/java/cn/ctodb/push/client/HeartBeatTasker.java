package cn.ctodb.push.client;

import cn.ctodb.push.core.Command;

/**
 * Created by cc on 2017/7/6.
 */
public class HeartBeatTasker implements Runnable {

    private Client client;
    private int waitTime = 10 * 1000;

    public HeartBeatTasker(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
//        for (; ; ) {
//            if (client.getStatus().equals(Client.Status.STOP)) break;
//            if (client.getStatus().equals(Client.Status.START)) {
//                try {
//                    Packet packet = new Packet(Command.HEARTBEAT.cmd);
//                    client.send(packet);
//                } catch (Throwable e) {
//
//                } finally {
//                    try {
//                        Thread.sleep(waitTime);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
    }
}
