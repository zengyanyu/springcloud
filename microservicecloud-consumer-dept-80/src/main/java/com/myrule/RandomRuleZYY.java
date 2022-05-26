package com.myrule;

import java.util.List;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

//重写源码,自定义自己的算法(每台机器执行5次操作)
public class RandomRuleZYY extends AbstractLoadBalancerRule {

	//total=0;  //当total==5以后,我们指针才能往下走,
	//index =0; //当前对外提供服务的服务器地址

	private int total = 0;//总共被调用次数,目前要求每次机器被调用5次

	private int currentIndex = 0;//当前提供机器的服务数

	/**
	 * 真正做事情的方法就是choose
	 * 返回具体服务的哪一个服务器
	 * Randomly choose from all living servers
	 */
	public Server choose(ILoadBalancer lb, Object key) {
		if (lb == null) {
			return null;
		}
		Server server = null;

		while (server == null) {
			//线程中断
			if (Thread.interrupted()) {
				//直接返回null
				return null;
			}
			//还能用的服务(server),活着的服务.
			List<Server> upList = lb.getReachableServers();
			//所有的服务(server)
			List<Server> alls = lb.getAllServers();

			int serverCount = alls.size();
			//没有服务
			if (serverCount == 0) {
				/*
				 * No servers. End regardless of pass, because subsequent passes
				 * only get more restrictive.
				 */
				return null;
			}

			////获取服务索引
			//int index = rand.nextInt(serverCount);//java.util.Random.nextInt(3);
			////根据索引获取对应的服务
			//server = upList.get(index);

			if (total < 5) {
				server = upList.get(currentIndex);
				total++;
			} else {
				total = 0;
				currentIndex++;
				//如果机器索引大于等于在活状态下的机器总数,那么把索引置为0
				if (currentIndex >= upList.size()) {
					currentIndex = 0;
				}
			}

			if (server == null) {
				/*
				   * 只有当服务器列表
				   *  不知怎么修剪的。这是一种瞬态情况。之后重试
				   *  屈服。
				 * The only time this should happen is if the server list were
				 * somehow trimmed. This is a transient condition. Retry after
				 * yielding.
				 */
				//线程中断
				Thread.yield();
				continue;//执行下一轮操作
			}

			//服务是活着的
			if (server.isAlive()) {
				//将服务返回
				return server;
			}

			// Shouldn't actually happen.. but must be transient or a bug.
			server = null;
			Thread.yield();
		}

		return server;

	}

	//choose:表示选择
	@Override
	public Server choose(Object key) {
		return choose(getLoadBalancer(), key);
	}

	//初始化配置
	@Override
	public void initWithNiwsConfig(IClientConfig clientConfig) {
		//什么都不做
	}
}
