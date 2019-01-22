package com.yunkan.consumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
//消费者端代码
public class ConsumerZooKeeper {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		// 1 获取zookeeper集群连接
		getConnect();
		
		// 2 注册监听
		getChlidren();
		
		// 3 业务逻辑处理
		business();
		
	}

	public static void business() throws InterruptedException {
		Thread.sleep(Long.MAX_VALUE);
	}

	public static void getChlidren() throws KeeperException, InterruptedException {
		//监听/servers这个目录下的子节点（即：节点的添加）
		List<String> children = zkClient.getChildren("/servers", true);
		
		// 存储服务器节点主机名称集合
		ArrayList<String> hosts = new ArrayList<>();
		
		for (String child : children) {
			
			byte[] data = zkClient.getData("/servers/"+child, false, null);
			
			hosts.add(new String(data));
		}
		
		// 将所有在线主机名称打印到控制台
		System.out.println(hosts);
		
	}

	private static String connectString = "192.168.74.157:2181,192.168.74.158:2181,192.168.74.159:2181";
	private static int sessionTimeout = 2000;
	private static ZooKeeper zkClient;

	public static void getConnect() throws IOException {
	
		zkClient = new ZooKeeper(connectString , sessionTimeout , new Watcher() {
			//监听回调函数
			@Override
			public void process(WatchedEvent event) {
				
				try {
					//调用这个方法，则会再次监听。否则，只监听一次
					getChlidren();
				} catch (KeeperException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
