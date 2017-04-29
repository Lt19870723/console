package com.cxdai.console.util.custody.https;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

final class CustomSSLSocketFactory extends SSLSocketFactory {

	private SSLSocketFactory originalFactory;

	private int socketTimeout;

	boolean keepAlive = false;

	String protocols[] = { "TLS","SSLv3","SSL" };

	/**
	 * Constructor : set the socket factory and timeout period.
	 */
	public CustomSSLSocketFactory(SSLSocketFactory originalFactory,
			int timeout, boolean keepAlive) {
		super();

		this.originalFactory = originalFactory;
		socketTimeout = timeout;
		this.keepAlive = keepAlive;

	}

	/** ***Override the original methods to include the timeouts for sockets**** */

	public String[] getDefaultCipherSuites() {
		return originalFactory.getDefaultCipherSuites();
	}

	public String[] getSupportedCipherSuites() {
		return originalFactory.getSupportedCipherSuites();
	}

	public Socket createSocket(Socket s, String host, int port,
			boolean autoClose) throws IOException {

		SSLSocket socket = (SSLSocket) originalFactory.createSocket(s, host,
				port, autoClose);
		socket.setSoTimeout(socketTimeout);
		socket.setEnabledProtocols(protocols);

		return socket;
	}

	public Socket createSocket(String host, int port) throws IOException,
			UnknownHostException {
		SSLSocket socket = (SSLSocket) originalFactory.createSocket(host,port);
		socket.setSoTimeout(socketTimeout);
		socket.setEnabledProtocols(protocols);
		socket.setSoTimeout(socketTimeout);
		return socket;
	}

	public Socket createSocket(String host, int port, InetAddress localHost,
			int localPort) throws IOException, UnknownHostException {

		Socket socket = originalFactory.createSocket(host, port, localHost,
				localPort);
		socket.setSoTimeout(socketTimeout);

		return socket;
	}

	public Socket createSocket(InetAddress host, int port) throws IOException {
		SSLSocket socket = (SSLSocket) originalFactory.createSocket();
		socket.setSoTimeout(socketTimeout);
		socket.setEnabledProtocols(protocols);
		socket.setSoTimeout(socketTimeout);
		socket.connect(new InetSocketAddress(host, port));
		return socket;
	}

	public Socket createSocket(InetAddress address, int port,
			InetAddress localAddress, int localPort) throws IOException {

		Socket socket = originalFactory.createSocket(address, port,
				localAddress, localPort);
		socket.setSoTimeout(socketTimeout);
		return socket;
	}

}
