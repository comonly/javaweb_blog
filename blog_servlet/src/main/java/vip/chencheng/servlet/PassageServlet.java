package vip.chencheng.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import vip.chencheng.dao.PassageDao;
import vip.chencheng.entity.AllKindInfo;
import vip.chencheng.entity.CommentInfo;
import vip.chencheng.entity.PassageInfo;

@SuppressWarnings("serial")
public class PassageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String kind="";
		try {
			kind=request.getRequestURI().split("passage")[1].split("/")[1];
		} catch (Exception e) {
			kind="index";
		}
		if(kind.equals("")){
		
		}else if(kind.equals("passIndex")){
			passIndex(request,response);
		}else if(kind.equals("passDetail")){
			passDetail(request,response);
		}else if(kind.equals("passChange")){
			passChange(request,response);
		}else if(kind.equals("passChangeSubmit")){
			passChangeSubmit(request,response);
		}else if(kind.equals("passAdd")){
			passAdd(request,response);
		}else if(kind.equals("passAddSubmit")){
			passAddSubmit(request,response);
		}else if(kind.equals("commentAddSubmit")){
			commentAddSubmit(request,response);
		}else if(kind.equals("commentChange")){
			commentChange(request,response);
		}else if(kind.equals("commentChangeSubmit")){
			commentChangeSubmit(request,response);
		}
	}
	
	/*评论修改提交*/
	private void commentChangeSubmit(HttpServletRequest request,
			HttpServletResponse response) {
		int cnumber=Integer.parseInt(request.getParameter("cnumber"));
		String ctitle=request.getParameter("ctitle");
		String content=request.getParameter("content");
		
		PassageDao passageDao=new PassageDao();
		passageDao.changeCommentInfo(cnumber,ctitle,content);

		try {
			response.sendRedirect("/bloglow_for_git/passage/commentChange/"+cnumber);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	/*进入评论修改页*/
	private void commentChange(HttpServletRequest request,
			HttpServletResponse response) {
		int cnumber=0;
		try {
			cnumber=Integer.parseInt(request.getRequestURI().split("commentChange")[1].split("/")[1]);
		} catch (Exception e) {}
		
		PassageDao passageDao=new PassageDao();
		CommentInfo commentInfo=new CommentInfo();
		commentInfo=passageDao.getCommentById(cnumber);
		request.setAttribute("commentInfo", commentInfo);

		try {
			request.getRequestDispatcher("/jsp/passage/commentChange.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	private void commentAddSubmit(HttpServletRequest request,
			HttpServletResponse response) {
		String content=request.getParameter("content");
		String title=request.getParameter("title");
		int pnumber=Integer.parseInt(request.getParameter("pnumber"));
		
		PassageDao passageDao=new PassageDao();
		passageDao.addCommentInfo(pnumber,title,content);
		try {
			response.sendRedirect("/bloglow_for_git/passage/passDetail/"+pnumber);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*提交新增文章*/
	private void passAddSubmit(HttpServletRequest request,
			HttpServletResponse response) {
		String title=request.getParameter("title");
		String image=request.getParameter("image");
		String keyword=request.getParameter("keyword");
		String content=request.getParameter("content");
		String coming=request.getParameter("coming");
		String comingUrl=request.getParameter("comingUrl");
		int passageKind=Integer.parseInt(request.getParameter("passageKind"));
		int passageBelong=Integer.parseInt(request.getParameter("passageBelong"));
		
		PassageDao passageDao=new PassageDao();
		passageDao.addPassageInfo(title,image,keyword,content,coming,comingUrl,passageKind,passageBelong);
		try {
			response.sendRedirect("/bloglow_for_git/passage/passIndex?pageIndex=1");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/*新加文章信息*/
	private void passAdd(HttpServletRequest request,
			HttpServletResponse response) {
		PassageDao passageDao=new PassageDao();
		List<AllKindInfo> kindList=new ArrayList<AllKindInfo>();
		kindList=passageDao.getAllKindList(true, 107,0);
		request.setAttribute("kindList", kindList);
		
		//获取归属列表
		List<AllKindInfo> belongList=new ArrayList<AllKindInfo>();
		belongList=passageDao.getAllKindList(true, 2,0);
		request.setAttribute("belongList", belongList);
		
		try {
			request.getRequestDispatcher("/jsp/passage/passageAdd.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	//提交文章更新信息
	private void passChangeSubmit(HttpServletRequest request,
			HttpServletResponse response) {
		int number=Integer.parseInt(request.getParameter("number"));
		String title=request.getParameter("title");
		String image=request.getParameter("image");
		String keyword=request.getParameter("keyword");
		String content=request.getParameter("content");
		String coming=request.getParameter("coming");
		String comingUrl=request.getParameter("comingUrl");
		int passageKind=Integer.parseInt(request.getParameter("passageKind"));
		int passageBelong=Integer.parseInt(request.getParameter("passageBelong"));
		
		
		PassageDao passageDao=new PassageDao();
		passageDao.changePassageInfo(number,title,image,keyword,content,coming,comingUrl,passageKind,passageBelong);

		try {
			response.sendRedirect("/bloglow_for_git/passage/passChange/"+number);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*修改文章信息*/
	private void passChange(HttpServletRequest request,
			HttpServletResponse response) {
		int pnumber=0;
		try {
			pnumber=Integer.parseInt(request.getRequestURI().split("passChange")[1].split("/")[1]);
		} catch (Exception e) {}
		
		PassageDao passageDao=new PassageDao();
		List<AllKindInfo> kindList=new ArrayList<AllKindInfo>();
		kindList=passageDao.getAllKindList(true, 107,0);
		request.setAttribute("kindList", kindList);
		
	
		PassageInfo passageInfo=new PassageInfo();
		passageInfo=passageDao.getPassageById(pnumber);
		request.setAttribute("passageInfo", passageInfo);
		
		//获取归属列表
		List<AllKindInfo> belongList=new ArrayList<AllKindInfo>();
		belongList=passageDao.getAllKindList(true, 2,0);
		request.setAttribute("belongList", belongList);
		
		try {
			request.getRequestDispatcher("/jsp/passage/passageChange.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/*获取某页的详细信息*/
	private void passDetail(HttpServletRequest request,
			HttpServletResponse response) {
		int pnumber=0;
		try {
			pnumber=Integer.parseInt(request.getRequestURI().split("passDetail")[1].split("/")[1]);
		} catch (Exception e) {}
		

		PassageDao passageDao=new PassageDao();
		PassageInfo passageInfo=new PassageInfo();
		passageInfo=passageDao.getPassageById(pnumber);
		
		
		String count=passageInfo.getPcount();//用来分解的
		String countS=passageInfo.getPcount();//修改的在页面显示
		//处理文章内容，找出h2标题
		//System.out.println(count);
		String []h2title=new String[20];
		int begin=0;
		int end=0;
		int i=0;
		while(count.indexOf("<h2>", begin)!=-1){
			begin=count.indexOf("<h2>", begin);
			end=count.indexOf("</h2>", end);
			//System.out.println(begin+" "+end);
			
			h2title[i]=count.substring(begin+5, end);
			h2title[i]=h2title[i].replaceAll("[<span>|</span>| |\\s*|\t|\r|\n]", "");//取消里面的不需要的字符
			//System.out.println(h2title[i]);
			
			countS=countS.replaceFirst("<h2>", "<h2 id='"+h2title[i]+"'>");
			begin=end+5;
			end=end+5;
			i++;
		}
		passageInfo.setPcount(countS);
		request.setAttribute("passageInfo", passageInfo);
		request.setAttribute("h2title", h2title);//h2标题作为锚点列表！！

		List<CommentInfo> commentInfoList=new ArrayList<CommentInfo>();
		commentInfoList=passageDao.getCommentInfoListByPid(pnumber);//默认获取全部记录
		request.setAttribute("commentInfoList", commentInfoList);
		
		try {
			request.getRequestDispatcher("/jsp/passage/passageDetail.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/*获得文章列表*/
	private void passIndex(HttpServletRequest request,
			HttpServletResponse response) {
		PassageDao passageDao=new PassageDao();
		List<AllKindInfo> kindList=new ArrayList<AllKindInfo>();
		kindList=passageDao.getAllKindList(true, 107,0);
		request.setAttribute("kindList", kindList);
		
		//获取归属列表
		List<AllKindInfo> belongList=new ArrayList<AllKindInfo>();
		belongList=passageDao.getAllKindList(true, 2,0);
		request.setAttribute("belongList", belongList);
		
		int kind=0;
		String select=null;
		int pageIndex=1;//当前页数，0表示不分页,1表示显示所有内容
		int belong=0;

		try {	//获取文章归属
			if(request.getParameter("belong").equals("null")){
				belong=0;
			}else{
				belong = Integer.parseInt(request.getParameter("belong"));
			}
		} catch (Exception e) {}		
		try {
			if(request.getParameter("kind").equals("null")){
				kind=0;
			}else{
				kind = Integer.parseInt(request.getParameter("kind"));
			}
		} catch (Exception e) {}
		try {
			if(request.getParameter("select").equals("null")){
				select=null;
			}else{
				select = request.getParameter("select");
			}
		} catch (Exception e) {}
		try {
			pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		} catch (Exception e) {}

		List<PassageInfo> passageInfoList=new ArrayList<PassageInfo>();
		//passageInfoList=
				passageDao.getPassageInfoList(kind,select,pageIndex,20,belong,passageInfoList);//默认获取全部记录
		request.setAttribute("passageInfoList", passageInfoList);
		
		try {
			request.getRequestDispatcher("/jsp/passage/passageList.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();System.out.println(e);
		} catch (IOException e) {
			e.printStackTrace();System.out.println(e);
		}
		
	}


}
