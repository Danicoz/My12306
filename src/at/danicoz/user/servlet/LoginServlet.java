package at.danicoz.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import at.danicoz.user.po.User;
import at.danicoz.user.service.UserService;
import at.danicoz.util.MD5Utils;
import at.danicoz.util.TextUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		//System.out.println("action="+action);
		if(action == null || "login".equals(action)){
			doLogin(request,response);
		}else if("autoLogin".equals(action)){
			doAutoLogin(request,response);
		}
	}

	//�ж� URL ���Ĳ��������ǵ����¼�����Զ���¼
	private void doAutoLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String username = "";
		String autoLogin = "";
		
		Cookie cookies[] = request.getCookies();
		if(cookies != null && cookies.length>0){
			for(Cookie c : cookies){
				if("username".equals(c.getName())){
					username = c.getValue();
				}else if("autoLogin".equals(c.getName())){
					autoLogin = c.getValue();
				
				}
			}
		}
		
		
		
		if(TextUtils.isEmpty(username) || TextUtils.isEmpty(autoLogin)){
			response.sendRedirect(request.getContextPath()+"/Login.jsp");
		}else{
			UserService userService = UserService.getInstance();
			User one = new User();
			one.setUsername(username);
			
			User dbUser = userService.findUser(one);
			if(autoLogin.equals(MD5Utils.md5(username+","+dbUser.getPassword()))){
				//���� IP
				User user = new User();
				populate(request, user);
				
				one = new User();
				one.setId(dbUser.getId());
				one.setLoginIp(user.getLoginIp());
				userService.updateUser(one);
				
				dbUser.setLoginIp(user.getLoginIp());
				
				HttpSession session = request.getSession();
				session.setAttribute("user", dbUser);
				
				//System.out.println(username+autoLogin);
				response.sendRedirect(request.getContextPath()+"/User/Index.jsp");
			}else{
				Cookie c = new Cookie("username","");
				c.setMaxAge(0);
				c.setPath("/");
				response.addCookie(c);
				
				Cookie c1 = new Cookie("autoLogin","");
				c1.setMaxAge(0);
				c1.setPath("/");
				response.addCookie(c1);
				
				response.sendRedirect(request.getContextPath()+"/Login.jsp");
				
			}
		}
	}

	private void doLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,IOException{
		// TODO Auto-generated method stub
		//���� Session �������ڱ�������
		//System.out.println("doLogin....");
		HttpSession session = request.getSession();
		
		//��ȡ�������
		User user = new User();
		//System.out.println("---pass001---");
		
		populate(request,user);
		//System.out.println("---pass002---");
		
		//��������֤
		String msg = validate(user);
		//System.out.println("---pass003---"+msg);
	
		if(TextUtils.isEmpty(msg)){
			String code = (String) session.getAttribute("code");
		//System.out.println("code="+code);
			//��֤��Ƚ�
			if(!user.getCode().equalsIgnoreCase(code)){
				//System.out.println("Code...");
				msg = "��֤�����";
				request.setAttribute("msg", msg);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			}else{
				//����Service ����
				UserService userService = UserService.getInstance();
				
				//����û��Ƿ����
				User dbUser = userService.login(user.getUsername(),user.getPassword());
				if(dbUser == null){
					msg = "�û������������";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}else{
					User one = new User();
					one.setId(dbUser.getId());
					one.setLoginIp(user.getLoginIp());
					userService.updateUser(one);
					
					dbUser.setLoginIp(user.getLoginIp());
					session.setAttribute("user", dbUser);
					//response.sendRedirect(request.getContextPath()+"/main.jsp");
					
					//�����ϴε�¼���û���
					Cookie c =new Cookie("username",dbUser.getUsername());
					c.setMaxAge(365*24*60);
					c.setPath("/");//������
					response.addCookie(c);
					
					//�Զ���½ �ж�
					if(user.isAutoLogin()){
						Cookie cLogin = new Cookie("autoLogin",MD5Utils.md5(dbUser.getUsername()+","+dbUser.getPassword()));
						cLogin.setMaxAge(365*24*60);
						cLogin.setPath("/");//������
						response.addCookie(cLogin);
					}
					else{
						Cookie cLogin = new Cookie("autoLogin","");
						cLogin.setMaxAge(0);
						cLogin.setPath("/");//������
						response.addCookie(cLogin);
					}
					response.sendRedirect(request.getContextPath()+"/User/Index.jsp");
				}
			}
		}
		else{
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/login.jsp").forward(request,response);
		}
		
	}
	
	//��֤
	private String validate(User user) {
		String errorMsg = null;
		
		if(TextUtils.isEmpty(user.getUsername())){
			errorMsg = "�������û���";
		}else if(user.getUsername().length()<6
				|| user.getUsername().length()>30){
			errorMsg = "�û���������6~30λ֮��";
		}else if(!user.getUsername().matches("[a-zA-Z0-9_]{6,30}")){
			errorMsg = "�û���ֻ������ĸ�����ֻ� ��_��";
		}
		//�����ж�
				else if(TextUtils.isEmpty(user.getPassword())){
					errorMsg = "����������";
				}
		//��֤��
				else if(TextUtils.isEmpty(user.getCode())){
					errorMsg = "��������֤��";
				}
		return errorMsg;
	}

	//��ȡ�������������װ����
	private void populate(HttpServletRequest request, User user) {
		// TODO Auto-generated method stub
		//��ȡ�ͻ��˵� IP
		String loginIP = request.getRemoteAddr();
		//��ȡ����
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String autoLogin = request.getParameter("autoLogin");
		String code = request.getParameter("code");
		
		//System.out.println(username+"   "+password+"  "+autoLogin+"  "+code);
		
		user.setLoginIp(loginIP);
		user.setUsername(username);
		
		//��ȫ�����
		if(password!=null){
			user.setPassword(MD5Utils.md5(password));
		}
		
		if(TextUtils.isEmpty(autoLogin)){
			user.setAutoLogin(false);
		}else{
			user.setAutoLogin(true);
		}
		
		user.setCode(code);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
