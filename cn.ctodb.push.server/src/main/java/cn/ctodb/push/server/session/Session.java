package cn.ctodb.push.server.session;

import java.net.UnknownHostException;
import java.security.cert.Certificate;
import java.util.Date;
import java.util.Locale;

public interface Session {

    public static final int MAJOR_VERSION = 1;
    public static final int MINOR_VERSION = 0;

    public static final int STATUS_CLOSED = -1;
    public static final int STATUS_CONNECTED = 1;
    public static final int STATUS_AUTHENTICATED = 3;

    public String getId();

    public int getStatus();

    public Date getCreationDate();

    public Date getLastActiveDate();

    public long getNumClientPackets();

    public long getNumServerPackets();

    public void close();

    public boolean isClosed();

    public boolean isSecure();

    public Certificate[] getPeerCertificates();

    public String getHostAddress() throws UnknownHostException;

    public String getHostName() throws UnknownHostException;

    public boolean validate();

}