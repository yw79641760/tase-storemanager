/**
 * 
 */
package com.softsec.tase.store.domain;

/**
 * ProgramItem.java
 * @author yanwei
 * @date 2013-3-15 下午9:42:26
 * @description
 */
public class ProgramItem {
	
	private long programId;

	private String programName;
	
	private int nodeType;
	
	private String scriptName;
	
	private String scriptPath;
	
	private String scriptMd5;
	
	private String executableName;
	
	private String executablePath;
	
	private String executableMd5;
	
	private String envVariables;

	public long getProgramId() {
		return programId;
	}

	public void setProgramId(long programId) {
		this.programId = programId;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public int getNodeType() {
		return nodeType;
	}

	public void setNodeType(int nodeType) {
		this.nodeType = nodeType;
	}

	public String getScriptName() {
		return scriptName;
	}

	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}

	public String getScriptPath() {
		return scriptPath;
	}

	public void setScriptPath(String scriptPath) {
		this.scriptPath = scriptPath;
	}

	public String getScriptMd5() {
		return scriptMd5;
	}

	public void setScriptMd5(String scriptMd5) {
		this.scriptMd5 = scriptMd5;
	}

	public String getExecutableName() {
		return executableName;
	}

	public void setExecutableName(String executableName) {
		this.executableName = executableName;
	}

	public String getExecutablePath() {
		return executablePath;
	}

	public void setExecutablePath(String executablePath) {
		this.executablePath = executablePath;
	}

	public String getExecutableMd5() {
		return executableMd5;
	}

	public void setExecutableMd5(String executableMd5) {
		this.executableMd5 = executableMd5;
	}

	public String getEnvVariables() {
		return envVariables;
	}

	public void setEnvVariables(String envVariables) {
		this.envVariables = envVariables;
	}
}
