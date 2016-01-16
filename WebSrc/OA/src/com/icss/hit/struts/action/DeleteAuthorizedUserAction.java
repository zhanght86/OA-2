/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.icss.hit.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.icss.hit.bean.ConfigWorkBean;
import com.icss.hit.bean.DepartmentBean;
import com.icss.hit.bean.interfaces.ConfigWork;
import com.icss.hit.bean.interfaces.Department;
import com.icss.hit.component.PageBean;
import com.icss.hit.hibernate.vo.ScheduleConfig;
import com.icss.hit.hibernate.vo.SysDept;

/** 
 * MyEclipse Struts
 * Creation date: 08-03-2009
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="deleteAuthorizedUser.succeed" path="/work/authorizedUser.jsp"
 */
public class DeleteAuthorizedUserAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		// �������Ȩ��ID
		long deleteId=-1;
		// ��ǰ��¼�û�ID
		long userId = -1;
		if( request.getSession().getAttribute("UserId") != null ){
			userId = Long.parseLong(request.getSession().getAttribute("UserId").toString());
		}else{
			return mapping.findForward("NullLogin");
		}
		
		// �ܼ�¼����
		int count=0;
		// ��ҳ��
		int pageCount = 0;
		// ��ѯҳ��
		int pageNo = 1;
		try{
			deleteId = Long.parseLong(request.getParameter("deleteId"));
		}
		catch(Exception e){
			deleteId=-1;
		}
		try{
			pageNo = Integer.parseInt(request.getParameter("page"));
		}
		catch(Exception e){
			pageNo = 1;
		}
		ConfigWork work = new ConfigWorkBean();
		List<ScheduleConfig> list = null;
		if(deleteId!=-1){
			// �����Ȩ
			work.deleteAuthorizedUser(deleteId,userId);
		}
		count = work.getAllConfigWorkUserCount(userId);
		pageCount = work.getPageCount(count, ConfigWorkBean.PAGE_SIZE);
			
		// ����ҳ��ķ�Χ
		pageNo = pageNo < 1? 1:pageNo;
		pageNo = pageNo > pageCount? pageCount: pageNo;
			
		list = work.getAllConfigWorkUser(userId, pageNo);
		request.setAttribute("authorizedUserList", list);
						
		// ����ǰ���ҳ����ʾ
		PageBean pagebean = new PageBean();
		pagebean.addParam("userId=" + userId );
		pagebean.setLink("authorizedUser.do");
		pagebean.setTotal(pageCount);
		pagebean.setThispage(pageNo);
			
		request.setAttribute("pageString", pagebean.getPageString());
		request.setAttribute("pageNo",pageNo );
		
		return mapping.findForward("deleteAuthorizedUser.succeed");
		
	}
}