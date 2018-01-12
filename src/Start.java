

import java.io.IOException;
import java.io.Writer;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Start
 */
@WebServlet(urlPatterns={"/Start/*", "/StartUp","/StartUp/*"})

public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Writer resOut = response.getWriter();
		resOut.write("<html>Hello, Got a GET request <br /> </html>");
		resOut.write("Served at " +request.getContextPath()+"<html><br /></html>");
		resOut.write("Remote Address: "+ request.getRemoteAddr()+"<html>&emsp;</html>");
		resOut.write("Port number: "+request.getLocalPort()+"<html><br /></html>");
		resOut.write("Protocol: "+request.getProtocol()+"<html>&emsp;</html>Method: "+request.getMethod()+"<html><br /></html>");
		String param = request.getParameter("dankmemes");
		resOut.write("Parameter dankmemes contains: " + param+"<html><br /></html>");
		resOut.write("URI: "+request.getRequestURI()+"<html><br /></html>");
		resOut.write("Context: "+request.getServletContext().getContextPath()+"<html><br /></html>");
		//resOut.write("Extra path: "+request.1+"\n");
		boolean yb= request.getRequestURI().contains("Startup/YorkBank");
		if(yb){
			response.sendRedirect(this.getServletContext().getContextPath()+"/Start");
		}
		//double principal=Double.parseDouble(this.getServletContext().getInitParameter("principle"));
		String principle=request.getParameter("principal");
		String period=request.getParameter("period");
		String rate=request.getParameter("interest");
	//	resOut.write("Principle: " + principle+"\tPeriod: "+period+"\tRate: "+rate+"\n");
			
		double prin;
		double per;
		double ratee;
		if(principle!=null){
			prin=Double.parseDouble(principle);
		}else{
			prin=Double.parseDouble(this.getServletContext().getInitParameter("principal"));
		}
		
		resOut.write("<html><br /></html>"+"<html>---------- Monthly Payments ----------<br /></html>");
		resOut.write("Principal: " + prin+"<html>&emsp;</html>");
		if(period!=null){
			per=Double.parseDouble(period);
		}else{
			per=Double.parseDouble(this.getServletContext().getInitParameter("period"));
		}
		resOut.write("Period: " + per+"<html>&emsp;</html>");
		if(rate!=null){
			ratee=Double.parseDouble(rate);
		}else{
			ratee=Double.parseDouble(this.getServletContext().getInitParameter("interest"));
		}
		resOut.write("Rate: " + ratee+"<html><br /></html>");
		
		double payments=  ((0.01*ratee)/12)*prin/(1-Math.pow(1+((0.01*ratee)/12), (-1)*per));
		
		DecimalFormat df = new DecimalFormat("#.####");
//		df.setRoundingMode(RoundingMode.CEILING);

		df.setMaximumFractionDigits(2);
		resOut.write("Monthly payments: $" + df.format(payments)+"<html><br /></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}