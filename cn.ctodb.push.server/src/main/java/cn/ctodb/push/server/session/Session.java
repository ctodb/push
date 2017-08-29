package cn.ctodb.push.server.session;

import java.net.UnknownHostException;
import java.security.cert.Certificate;
import java.util.Date;

interface Session {

    static final int MAJOR_VERSION = 1;
    static final int MINOR_VERSION = 0;

    static final int STATUS_CLOSED = -1;
    static final int STATUS_CONNECTED = 1;
    static final int STATUS_AUTHENTICATED = 3;

    String getId();

    int getStatus();

    Date getCreationDate();

    Date getLastActiveDate();

    long getNumClientPackets();

    long getNumServerPackets();

    void close();

    boolean isClosed();

    boolean isSecure();

    Certificate[] getPeerCertificates();

    String getHostAddress() throws UnknownHostException;

    String getHostName() throws UnknownHostException;

    boolean validate();

}