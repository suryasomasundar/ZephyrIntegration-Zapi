package zapi.DOM.Cycle;

import java.util.HashMap;

import org.json.JSONObject;

public class CreateTestCycleDOM {
	
	public int clonedCycleId;
	public String name;
	public String build;
	public String environment;
	public String description;
	public String startDate;
	public String endDate;
	public String projectId;
	public String versionId;
	public int sprintId;
	
	public CreateTestCycleDOM() {
		
		build = "Test";
		environment = "Test";
		description = "Java-Test-Cycle-Creation";
		startDate = "18/Jul/19";
		endDate = "30/Dec/19";
		projectId = "10000";
		versionId = "18828";
		sprintId = 1;
	}

	public int getClonedCycleId() {
		return clonedCycleId;
	}

	public void setClonedCycleId(int clonedCycleId) {
		this.clonedCycleId = clonedCycleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBuild() {
		return build;
	}

	public void setBuild(String build) {
		this.build = build;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public int getSprintId() {
		return sprintId;
	}

	public void setSprintId(int sprintId) {
		this.sprintId = sprintId;
	}

	public String cycle() {
		HashMap<String, Object> jsoninput = new HashMap();

		jsoninput.put("name", name);
		jsoninput.put("build", build);
		jsoninput.put("environment", environment);
		jsoninput.put("description", description);
		jsoninput.put("startDate", startDate);
		jsoninput.put("endDate", endDate);
		jsoninput.put("projectId", projectId);
		jsoninput.put("versionId", versionId);
		jsoninput.put("sprintId", sprintId);

		JSONObject json = new JSONObject(jsoninput);
		String input = json.toString();
		return input;
	}
}
