package vip.chencheng.servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vip.chencheng.dao.KindManageDao;
import vip.chencheng.entity.AllKindInfo;

@SuppressWarnings("serial")
public class KindManageServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String kind="";
		try {
			kind=request.getRequestURI().split("kindmanage")[1].split("/")[1];
		} catch (Exception e) {
			kind="index";
		}
		
		if(kind.equals("")){
		
		}else if(kind.equals("allKind")){
			allKind(request,response);	//账单类名管理
		}else if(kind.equals("allKindChange")){
			allKindChange(request,response);	//账单类目修改
		}else if(kind.equals("allKindAdd")){
			allKindAdd(request,response);	//账单类目修改
		}
		
	}
	

	private void allKindAdd(HttpServletRequest request,
			HttpServletResponse response) {

		int akkind=Integer.parseInt(request.getParameter("akkind"));
		String akvalue=request.getParameter("akvalue");
		int akfather=Integer.parseInt(request.getParameter("akfather"));
		String akdetail=request.getParameter("akdetail");
		int akstate=Integer.parseInt(request.getParameter("akstate"));
		
		KindManageDao importantRecordDao=new KindManageDao();
		importantRecordDao.addKindInfo(akkind, akvalue, akfather,akdetail,akstate);
		
		allKind(request,response);
		
	}
	private void allKindChange(HttpServletRequest request,
			HttpServletResponse response) {
		
		int akid=Integer.parseInt(request.getParameter("akid"));
		String akvalue=request.getParameter("akvalue");
		String akdetail=request.getParameter("akdetail");
		int akfather=Integer.parseInt(request.getParameter("akfather"));
		int akstate=Integer.parseInt(request.getParameter("akstate"));
		int akkind=Integer.parseInt(request.getParameter("akkind"));
		KindManageDao importantRecordDao=new KindManageDao();
		importantRecordDao.changeAllKindInfo(akid, akvalue,akdetail,akstate,akfather,akkind);	
		allKind(request,response);
	}
	/*管理类目*/
	private void allKind(HttpServletRequest request,
			HttpServletResponse response) {
		List<AllKindInfo> rootKindList=new ArrayList<AllKindInfo>();
		List<AllKindInfo> kindList=new ArrayList<AllKindInfo>();
		KindManageDao importantRecordDao=new KindManageDao();
		rootKindList=importantRecordDao.getAllKindList(false, 1, 0);
		request.setAttribute("rootKindList", rootKindList);
		int akkind=1;
		int isroot=0;
		try{
			akkind=Integer.parseInt(request.getParameter("akkind"));
			if(request.getParameter("isroot").toString().equals("false")){
				isroot=1;
			}
		}catch(Exception e){}
		kindList=importantRecordDao.getAllKindList(false,akkind, isroot);//获得含隐藏信息的
		request.setAttribute("kindList", kindList);
		try {
			request.getRequestDispatcher("/jsp/passage/allKindList.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
