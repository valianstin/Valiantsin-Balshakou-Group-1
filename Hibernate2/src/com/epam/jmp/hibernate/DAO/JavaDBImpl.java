package com.epam.jmp.hibernate.DAO;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
import com.epam.pmc.constants.Constants;
import com.epam.pmc.exception.DaoException;

@Repository
public class JavaDBImpl implements PMCDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Position getPosition(String name) {
		String sql = "select * from position where name='" + name + "'";

		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Position.class);
		return (Position) sqlquery.uniqueResult();
	}

	@Override
	public Status getStatus(String name) {
		String sql = "select * from status where name='" + name + "'";

		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Status.class);
		return (Status) sqlquery.uniqueResult();
	}
	
	@Override
	public List<Status> getStatuses() {
		String sql = "select * from status";

		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Status.class);
		return (List<Status>) sqlquery.list();
	}
	
	@Override
	public Employee getEmployee(String login, String password) {
		String sql = "select * from employee where employee.login='" + login
				+ "' and employee.password='" + password + "'";

		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Employee.class);
		return (Employee) sqlquery.uniqueResult();
	}

	@Override
	public List<Project> getProjects(Employee employee) {
		String getProjectsRequest = "Select * from project,status where project.status_id=status.id ";
		if (!employee.getPosition().getName().equals("Administrator")&&!employee.getPosition().getName().equals("Lead")) {
			getProjectsRequest += " and project.id in(select project_id from member where employee_id=" + employee.getId()+")";
		}
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(getProjectsRequest).addEntity(Project.class);
		return (List<Project>) sqlquery.list();
	}

	@Override
	public Project getProject(int projectId) {
		String getProjectRequest = "Select * from project,status where project.status_id=status.id and project.id="
				+ projectId;
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(getProjectRequest).addEntity(Project.class);
		return (Project) sqlquery.uniqueResult();
	}

	@Override
	public List<Task> getTasks(int projectId) {
		String getTasksRequest = "Select * from task,status where task.status_id=status.id and project_Id="
				+ projectId;
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(getTasksRequest).addEntity(Task.class);
		return (List<Task>) sqlquery.list();
	}

	@Override
	public Task getTask(int taskId) {
		String getTaskRequest = "Select * from task,status where task.status_id=status.id and task.id="
				+ taskId;
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(getTaskRequest).addEntity(Task.class);
		return (Task) sqlquery.uniqueResult();
	}

	@Override
	public List<Member> getTaskMembers(int taskId) {
		String getTaskMembersRequest = "Select * from member,task,assignment,employee,position,role where task.id=assignment.task_id and assignment.member_id=member.id and member.employee_id=employee.id and role.id=member.role_id  and employee.position_id=position.id and task.id="
				+ taskId;
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(getTaskMembersRequest).addEntity(Member.class);
		return (List<Member>) sqlquery.list();
	}

	@Override
	public List<Member> getProjectMembers(int projectId) {
		String getProjectMembersRequest = "Select * from member where member.project_id="
				+ projectId;
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(getProjectMembersRequest)
				.addEntity(Member.class);
		return (List<Member>) sqlquery.list();
	}

	@Override
	public List<Assignment> getAssignments(int projectId) {
		String getAssignmentsRequest = "Select * from assignment,task,member,employee where task.id=assignment.task_id and assignment.member_id=member.id and employee.id=member.employee_id and  member.project_id="
				+ projectId;

		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(getAssignmentsRequest)
				.addEntity(Assignment.class);
		return (List<Assignment>) sqlquery.list();
	}

	@Override
	public void addProject(Project project) {
		sessionFactory.getCurrentSession().save(project);
		}

	@Override
	public void updateProject(Project project) {
		sessionFactory.getCurrentSession().update(project);
	}

	@Override
	public void addTask(Task task) {
		sessionFactory.getCurrentSession().save(task);
		
	}

	@Override
	public void updateTask(Task task) {
		sessionFactory.getCurrentSession().update(task);
	}

	@Override
	public List<Employee> getEmployees(int projectId) {
		String sql = "select * from employee where id not in(select employee_id from member where project_id="+projectId+")";

		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Employee.class);
		return (List<Employee>) sqlquery.list();
	}

	@Override
	public List<Role> getRoles() {
	
		String sql = "select * from role";

		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Role.class);
		return (List<Role>) sqlquery.list();
	}

	@Override
	public Role getRole(String roleName) {
		String sql = "select * from role where name='"+roleName+"'";

		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Role.class);
		return (Role) sqlquery.uniqueResult();
	}

	@Override
	public Employee getEmployee(int employeeId) {
		String sql = "select * from employee where id="+employeeId;

		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Employee.class);
		return (Employee) sqlquery.uniqueResult();
	}

	@Override
	public void addMember(Member member) {
		sessionFactory.getCurrentSession().save(member);
	}

	@Override
	public Member getMember(int memberId) {
		String sql = "select * from member where id="+memberId;

		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Member.class);
		return (Member) sqlquery.uniqueResult();
	}

	@Override
	public void addAssignment(Assignment ass) {
		sessionFactory.getCurrentSession().save(ass);
	}

	@Override
	public Assignment getAssignment(int taskId, int employeeId) {
		String sql = "select * from assignment,member where assignment.member_id=member.id and assignment.task_id="+taskId+" and member.employee_id="+employeeId;

		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Assignment.class);
		return (Assignment) sqlquery.uniqueResult();
	}

	@Override
	public List<Activity> getActivities(Assignment ass) {
		String sql = "select * from activity where activity.assignment_id="+ass.getId();

		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Activity.class);
		return (List<Activity>) sqlquery.list();
	}

	@Override
	public void addActivity(Activity activity) {
		sessionFactory.getCurrentSession().saveOrUpdate(activity);
	}

	@Override
	public Assignment getAssignment(int assignmentId) {
		String sql = "select * from assignment where assignment.id="+assignmentId;
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Assignment.class);
		return (Assignment) sqlquery.uniqueResult();
	}

	@Override
	public Role getRole(Employee employee, int id) {
		String sql = "select * from role,member where role.id=member.role_id and member.employee_id="+employee.getId()+" and member.project_id="+id;
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Role.class);
		return (Role) sqlquery.uniqueResult();
	}

	@Override
	public List<Position> getPositions() {
		String sql = "select * from position";
		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Position.class);
		return (List<Position>) sqlquery.list();
	}

	@Override
	public void addEmployee(Employee employee) {
		sessionFactory.getCurrentSession().save(employee);
	}

	@Override
	public List<Assignment> getEmployeeAssignments(int employeeId,String date) {
		String sql = "select * from assignment,member where assignment.member_id=member.id and member.employee_id="+employeeId+" and assignment.assignment_start_date<='"+date+"' and assignment_end_date>='"+date+"'";

		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Assignment.class);
		return (List<Assignment>) sqlquery.list();
	}

	@Override
	public Activity getActivity(int assId, String date) {
		// TODO Auto-generated method stub
		String sql = "select * from activity where assignment_id="+assId+" and activity_date='"+date+"'";

		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Activity.class);
		return (Activity) sqlquery.uniqueResult();
	}

	@Override
	public List<ProjectAttachment> getProjectAttachments(int projectId) {
		// TODO Auto-generated method stub
		String sql = "select * from attachment_project where project_id="+projectId;

		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(ProjectAttachment.class);
		return (List<ProjectAttachment>) sqlquery.list();
	}

	@Override
	public void addProjectAttachments(ProjectAttachment att) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(att);
	}

	@Override
	public ProjectAttachment getProjectAttachment(int attachmentId) {
		// TODO Auto-generated method stub
		String sql = "select * from attachment_project where id="+attachmentId;

		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(ProjectAttachment.class);
		return (ProjectAttachment) sqlquery.uniqueResult();
	}

	@Override
	public List<TaskAttachment> getTaskAttachments(int taskId) {
		// TODO Auto-generated method stub
		String sql = "select * from attachment_task where task_id="+taskId;

		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(TaskAttachment.class);
		return (List<TaskAttachment>) sqlquery.list();
	}

	@Override
	public void addTaskAttachment(TaskAttachment att) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(att);
	}

	@Override
	public TaskAttachment getTaskAttachment(int attachmentId) {
		// TODO Auto-generated method stub
		String sql = "select * from attachment_task where id="+attachmentId;

		SQLQuery sqlquery = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(TaskAttachment.class);
		return (TaskAttachment) sqlquery.uniqueResult();
	}

}
