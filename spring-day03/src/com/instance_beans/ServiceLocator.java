package com.instance_beans;

/**
 * 实例工厂方法
 * @author 王志坚
 *
 */
public class ServiceLocator {
	private ClientService clientService = new ClientServiceImpl();
	
	public ClientService createClientService() {
		return clientService;
	}

	@Override
	public String toString() {
		return "ServiceLocator [clientService=" + clientService + "]";
	}
}
