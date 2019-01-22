package com.yunkan.provider;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
//生产者代码
public class ProviderZooKeeper {
	public static void main(String[] args) throws Exception {
		// 1 连接zookeeper集群
		getConnect();
		// 2 注册节点
		regist(args[0]);
		// 3 业务逻辑处理
		business();
	}
	public static void business() throws InterruptedException {
		//用java代码连接zk服务器，那么每次的连接和断开就是一次会话
		Thread.sleep(Long.MAX_VALUE);
	}

	public static void regist(String hostname) throws KeeperException, InterruptedException {
		//在/servers这个目录下，创建有编号的临时的server***节点，并且节点的值为hostname.getBytes()
		String path = zkClient.create("/servers/server", hostname.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		System.out.println(path+"************");
		System.out.println(hostname +"is online ");
		
	}

	private static String connectString = "192.168.74.157:2181,192.168.74.158:2181,192.168.74.159:2181";
	private static int sessionTimeout = 2000;
	private static ZooKeeper zkClient;

	public static void getConnect() throws IOException {
		
		zkClient = new ZooKeeper(connectString , sessionTimeout , new Watcher() {
			
			@Override
			public void process(WatchedEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
