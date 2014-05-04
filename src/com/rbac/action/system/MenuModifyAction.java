/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.rbac.action.system;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.rbac.common.BaseAction;
import com.rbac.entity.SysAction;
import com.rbac.entity.SysMenu;
import com.rbac.form.system.ActionModifyForm;
import com.rbac.form.system.MenuModifyForm;
import com.rbac.service.ActionService;
import com.rbac.service.MenuService;
import com.rbac.util.CommonUtils;

/** 
 * MyEclipse Struts
 * Creation date: 05-04-2014
 * 
 * XDoclet definition:
 * @struts.action path="/menuModify" name="menuModifyForm" input="/system/menuModify.jsp" scope="request" validate="true"
 * @struts.action-forward name="success" path="/system/menuList.jsp"
 */
public class MenuModifyAction extends BaseAction {
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
		MenuModifyForm menuModifyForm = (MenuModifyForm) form;
		MenuService menuService = (MenuService) super.getBean("menuService");
		if(CommonUtils.isNotBlank(menuModifyForm.getSubmit())){
			SysMenu menu = new SysMenu();
			if(CommonUtils.isNotBlank(menuModifyForm.getId())){
				Long id = CommonUtils.parseLong(menuModifyForm.getId());
				menu = menuService.getMenuById(id);
				menu.setModifierId(super.getCurrentAccountId(request));
				menu.setModifyTime(new Date());
			}
			else{
				menu.setCreatorId(super.getCurrentAccountId(request));
				menu.setCreateTime(new Date());
			}
			menu.setName(menuModifyForm.getName());
			menu.setUrl(menuModifyForm.getUrl());
			menu.setParentId(CommonUtils.parseLong(menuModifyForm.getParentId()));
			menu.setOrderSeq(CommonUtils.parseInteger(menuModifyForm.getOrderSeq()));
			menuService.saveOrUpdateMenu(menu);
			return mapping.findForward("success");
		}
		return mapping.getInputForward();
	}
}