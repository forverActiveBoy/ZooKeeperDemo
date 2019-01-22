package com.yunkan.test;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZooKeeperClientTest {
	public static void main(String[] args) throws KeeperException, InterruptedException{
		String connection = "192.168.74.157:2181,192.168.74.158:2181,192.168.74.159:2181";
		int sessionOut = 2000;
		 ZooKeeper client = null;
		Watcher watcher = new Watcher(){
			public void process(WatchedEvent event) {
				System.out.println();
			}
		};
		//连接zk集群
		try {
			 client = new ZooKeeper(connection,sessionOut,watcher);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//创建节点
/*	try {
			String string = client.create("/baizhi", "lijiaxue".getBytes(),Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT_SEQUENTIAL);
			System.out.println(string);
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//监听/目录下的节点
/*			List<String> children = client.getChildren("/", true);
			Thread.sleep(Long.MAX_VALUE);*/
	
		
	}
}
