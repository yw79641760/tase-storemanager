/**
 * 
 */
package com.softsec.tase.store.domain;

import java.util.concurrent.ConcurrentSkipListSet;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * NodeItemTest.java
 * @author yanwei
 * @date 2013-3-25 下午3:30:07
 * @description
 */
public class NodeItemTest extends TestCase {

	@Test
	public void testNodeItem() {
		NodeItem item1 = new NodeItem(30);
		NodeItem item2 = new NodeItem(80);
		NodeItem item3 = new NodeItem(79);
		NodeItem item4 = new NodeItem(81);
		ConcurrentSkipListSet<NodeItem> nodeSet = new ConcurrentSkipListSet<NodeItem>();
		nodeSet.add(item2);
		nodeSet.add(item1);
		nodeSet.add(item3);
		nodeSet.add(item4);
		System.out.println(nodeSet.first());
		System.out.println(nodeSet.last());
		System.out.println(nodeSet.headSet(new NodeItem(80), true));
	}
}
