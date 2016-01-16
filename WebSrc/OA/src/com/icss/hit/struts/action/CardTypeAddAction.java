/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.icss.hit.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.icss.hit.bean.CardTypeBean;
import com.icss.hit.hibernate.vo.CardType;
import com.icss.hit.hibernate.vo.SysUser;
import com.icss.hit.struts.form.CardTypeAddForm;

/** 
 * MyEclipse Struts
 * Creation date: 08-01-2009
 * 
 * XDoclet definition:
 * @struts.action path="/cardTypeAdd" name="cardTypeAddForm" input="/form/cardType.jsp" scope="request" validate="true"
 * @struts.action-forward name="cardTypeAdd.success" path="/card/cardType.jsp"
 */
public class CardTypeAddAction extends Action {
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
	 * @author ����
	 * ʵ����Ƭ�е�����
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CardTypeAddForm cardTypeAddForm = (CardTypeAddForm) form;// TODO Auto-generated method stub

		long userId = 1L;//�洢�û���Id
		if( request.getSession().getAttribute("UserId") != null ){
			userId = Long.parseLong(request.getSession().getAttribute("UserId").toString());
		}else{
			return mapping.findForward("NullLogin");
		}
		CardTypeBean getter = null;
		
		getter = new CardTypeBean();//ʵ����Bean
		
		
		CardType newCardType = new CardType();//ʵ������Ƭ��ʵ��
		SysUser currentUser = new SysUser();
		currentUser.setSuId(userId);//�����û�ID��ʼ���û�ʵ��
		newCardType.setSysUser(currentUser);
		newCardType.setCtName(cardTypeAddForm.getNewCardType());//��ʼ��Ҫ���ӵ���Ƭ����Ϣ
		
		
		
		
		
		System.out.print(cardTypeAddForm.getNewCardType().length());
		
			if(getter.updateCardType(newCardType) == 0){
				//�ɹ�������Ϣ��ת��
				return mapping.findForward("CardTypeAdd.success");
			}
			else if(getter.updateCardType(newCardType) == 1){
				//������������
				ActionErrors errors = new ActionErrors();
				errors.add("newCardTypeTooLong",new ActionMessage("NewCardType.cardTypeNameRepeat"));
				saveErrors(request,errors);
				return mapping.findForward("CardTypeAdd.error");
			}
			else {
				//����δ֪���쳣
				return mapping.findForward("CardTypeAdd.error");
			}
		
	
		
	}
}