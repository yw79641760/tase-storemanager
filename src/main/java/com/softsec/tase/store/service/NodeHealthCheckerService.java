/**
 * 
 */
package com.softsec.tase.store.service;

import java.text.DecimalFormat;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softsec.tase.common.rpc.domain.node.NodeRuntime;

/**
 * NodeHealthCheckerService.java
 * <p></p>
 * @author yanwei
 * @since 2013-4-22 上午11:35:57
 * @version
 */
public class NodeHealthCheckerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(NodeHealthCheckerService.class);
	
	public static NodeRuntime getNodeRuntime() {
		
		LOGGER.info("Start collecting node runtime info ...");
		Sigar sigar = new Sigar();
		NodeRuntime nodeRuntime = new NodeRuntime();
		
		// node local info using sigar
		try {
			int cpuCount = sigar.getCpuInfoList().length;
			CpuInfo cpuInfo = sigar.getCpuInfoList()[0];
			nodeRuntime.setCpuCount(cpuCount);
			nodeRuntime.setCpuCores(cpuInfo.getTotalCores());
			nodeRuntime.setCpuMhz(cpuInfo.getMhz());

			CpuPerc cpuPerc = sigar.getCpuPerc();
			double cpuUsedPerc = 0;
			if (cpuPerc.getCombined() == 0) {
				cpuUsedPerc = 0;
			} else {
				cpuUsedPerc = Double.parseDouble(new DecimalFormat("#.00").format(cpuPerc.getCombined() * 100));
			}
			nodeRuntime.setCpuUsedPerc(cpuUsedPerc);
			
			nodeRuntime.setFreeMem(sigar.getMem().getFree() / 1024 / 1024);
			nodeRuntime.setJvmFreeMem(Runtime.getRuntime().freeMemory() / 1024 / 1024);
			
		} catch (SigarException se) {
			throw new RuntimeException("Failed to get node heart beat [ " + nodeRuntime + " ] : " + se.getMessage(), se);
		} finally {
			LOGGER.info("Finished collecting node runtime info : " + nodeRuntime);
			sigar = null;
		}
		
		return nodeRuntime;
	}
}
