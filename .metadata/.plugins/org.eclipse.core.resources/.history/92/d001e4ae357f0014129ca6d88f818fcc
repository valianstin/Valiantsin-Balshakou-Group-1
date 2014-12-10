package com.epam.jmp.hibernate.DAO;

import java.util.List;

import com.epam.pmc.beans.Activity;
import com.epam.pmc.beans.Assignment;
import com.epam.pmc.beans.Member;
import com.epam.pmc.beans.Position;
import com.epam.pmc.beans.Project;
import com.epam.pmc.beans.ProjectAttachment;
import com.epam.pmc.beans.Role;
import com.epam.pmc.beans.Status;
import com.epam.pmc.beans.Task;
import com.epam.pmc.beans.Employee;
import com.epam.pmc.beans.TaskAttachment;

public interface PMCDAO {
	Employee getEmployee(String login, String password);

	List<Project> getProjects(Employee user);

	List<Task> getTasks(int projectId);

	Task getTask(int taskId);

	List<Member> getTaskMembers(int taskId);

	List<Member> getProjectMembers(int projectId);

	Position getPosition(String name);
	Status getStatus(String name);
	List<Assignment> getAssignments(int projectId);

	Project getProject(int projectId);

	void addProject(Project project);

	List<Status> getStatuses();

	void updateProject(Project project);

	void addTask(Task task);

	void updateTask(Task task);

	List<Employee> getEmployees(int projectId);

	List<Role> getRoles();

	Role getRole(String roleName);

	Employee getEmployee(int employeeId);

	void addMember(Member member);

	Member getMember(int memberId);

	void addAssignment(Assignment ass) ;


	Assignment getAssignment(int taskId, int employeeId);

	List<Activity> getActivities(Assignment ass);
	
	Assignment getAssignment(int assignmentId);

	void addActivity(Activity act);

	Role getRole(Employee employee, int id);

	List<Position> getPositions();

	void addEmployee(Employee employee);

	List<Assignment> getEmployeeAssignments(int employeeId, String date);

	Activity getActivity(int assId, String date);

	List<ProjectAttachment> getProjectAttachments(int projectId);

	void addProjectAttachments(ProjectAttachment att);

	ProjectAttachment getProjectAttachment(int attachmentId);

	List<TaskAttachment> getTaskAttachments(int taskId);

	void addTaskAttachment(TaskAttachment att);

	TaskAttachment getTaskAttachment(int attachmentId);
}
