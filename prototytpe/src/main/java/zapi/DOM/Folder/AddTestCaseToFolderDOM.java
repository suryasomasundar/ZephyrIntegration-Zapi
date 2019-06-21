package zapi.DOM.Folder;

import java.util.HashMap;

import org.json.JSONObject;

public class AddTestCaseToFolderDOM {


	public String cycleId;
	public String issueId;
	public String projectId;
	public String versionId;
	public Integer folderId;
	public String assigneeType;
	public String assignee;
	
	public AddTestCaseToFolderDOM() {
		
		projectId = "10000";
		versionId = "18828";
		assigneeType = "assignee";
		assignee = "ssuryanarayanan";
	}

	public String getCycleId() {
		return cycleId;
	}

	public void setCycleId(String cycleId) {
		this.cycleId = cycleId;
	}

	public String getIssueId() {
		return issueId;
	}

	public void setIssueId(String issueId) {
		this.issueId = issueId;
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

	public Integer getFolderId() {
		return folderId;
	}

	public void setFolderId(Integer folderId2) {
		this.folderId = folderId2;
	}

	public String getAssigneeType() {
		return assigneeType;
	}

	public void setAssigneeType(String assigneeType) {
		this.assigneeType = assigneeType;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	
	public String putTestCase() {
		HashMap<String, Object> jsoninput = new HashMap();

		jsoninput.put("cycleId" , cycleId);
		jsoninput.put("issueId", issueId);
		jsoninput.put("projectId", projectId);
		jsoninput.put("versionId", versionId);
		jsoninput.put("folderId", folderId);
		jsoninput.put("assigneeType", assigneeType);
		jsoninput.put("assignee", assignee);

		JSONObject json = new JSONObject(jsoninput);
		String input = json.toString();
		return input;
	}
	
}

