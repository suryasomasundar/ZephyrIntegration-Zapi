package zapi.DOM.Folder;

import java.util.HashMap;

import org.json.JSONObject;

public class CreateTestFolderDOM {
	
	public String cycleId;
	public String name;
	public String description;
	public String projectId;
	public String versionId;
	public int clonedFolderId;
	
	public CreateTestFolderDOM() {
		
		description = "Java-Test-Folder-Creation";
		projectId = "10000";
		versionId = "18828";
		clonedFolderId = 1;
	}

	public String getCycleId() {
		return cycleId;
	}

	public void setCycleId(String cycleId) {
		this.cycleId = cycleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public int getClonedFolderId() {
		return clonedFolderId;
	}

	public void setClonedFolderId(int clonedFolderId) {
		this.clonedFolderId = clonedFolderId;
	}
	
	public String folder() {
		HashMap<String, Object> jsoninput = new HashMap();

		jsoninput.put("cycleId" , cycleId);
		jsoninput.put("name", name);
		jsoninput.put("description", description);
		jsoninput.put("projectId", projectId);
		jsoninput.put("versionId", versionId);
		jsoninput.put("clonedFolderId", clonedFolderId);

		JSONObject json = new JSONObject(jsoninput);
		String input = json.toString();
		return input;
	}
	

}
