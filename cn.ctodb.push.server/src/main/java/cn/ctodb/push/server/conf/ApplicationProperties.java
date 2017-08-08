package cn.ctodb.push.server.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application") // 接收application.yml中的myProps下面的属性
public class ApplicationProperties {

	private final Server server = new Server();

	public Server getServer() {
		return server;
	}

	public static class Server {
		private int port = 9901;

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}
	}

}