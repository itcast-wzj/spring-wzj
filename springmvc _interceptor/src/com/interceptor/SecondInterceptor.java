package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SecondInterceptor implements HandlerInterceptor{
	
	/**
	 	Ŀ�귽��ִ��֮ǰִ��
	 	�������ֵΪfalse, �򲻻���ú�������������Ŀ�귽��
	 	�������ֵΪtrue, ��������ú�������������Ŀ�귽��
	 	��������Ȩ�ޣ���־�������
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		System.out.println("��SecondInterceptor�� preHandle" );
		return false;
	}
	
	/**
	 	Ŀ�귽��ִ��֮����ͼ��Ⱦ֮ǰִ��
	 	���Զ��������е����Ի���ͼ�����޸ġ���Ϊ�����и�ModelAndView��
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("��SecondInterceptor�� postHandle" );
		
	}

	/**
	  	��Ⱦ��ͼ֮�󱻵���
	 	�����ͷ���Դ
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("��SecondInterceptor�� afterCompletion" );
	}

}
