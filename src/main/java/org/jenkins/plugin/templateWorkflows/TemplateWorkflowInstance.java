package org.jenkins.plugin.templateWorkflows;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.kohsuke.stapler.export.Exported;
import org.kohsuke.stapler.export.ExportedBean;

@ExportedBean
public class TemplateWorkflowInstance implements Comparable<TemplateWorkflowInstance> {

	// mark if the job was create during the template definition - for delete logic
	private Map<String, Boolean> isNewJobMap;
	private String instanceName;
	private String templateName;
	private Map<String, String> jobParameters;
	private Map<String, String> relatedJobs;

	public TemplateWorkflowInstance(final String templateName, final String instanceName, final Map<String, Boolean> isNewJobMap) {
		this.templateName = templateName;
		this.instanceName = instanceName;
		this.isNewJobMap = isNewJobMap;
		this.jobParameters = new ConcurrentHashMap<String, String>();
		this.relatedJobs = new ConcurrentHashMap<String, String>();
	}

	@Exported
	public String getInstanceName() {
		return this.instanceName;
	}

	@Exported
	public String getTemplateName() {
		return this.templateName;
	}

	@Exported
	public int getRelatedJobsSize() {
		return this.relatedJobs.size();
	}

	@Exported
	public Map<String, String> getRelatedJobs() {
		return this.relatedJobs;
	}

	@Exported
	public Map<String, String> getJobParameters() {
		return this.jobParameters;
	}

	@Exported
	public boolean isJobWasCreateByWorkflow(final String jobName) {
		return this.isNewJobMap.get(jobName);
	}

	public void setJobParameters(final Map<String, String> jobParameters) {
		this.jobParameters = jobParameters;
	}

	public void setRelatedJobs(final Map<String, String> relatedJobs) {
		this.relatedJobs = relatedJobs;
	}

	public int compareTo(final TemplateWorkflowInstance o) {
		return this.instanceName.compareTo(o.instanceName);
	}
}
