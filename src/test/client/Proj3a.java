package test.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.core.client.JsonUtils;
import java.util.ArrayList;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.VerticalPanel;


public class Proj3a implements EntryPoint, ClickHandler
{
	VerticalPanel mainPanel = new VerticalPanel();
	String baseURL = "http://localhost:3000";
	Button loginButton = new Button("Login");
	TextBox unBox = new TextBox();
	PasswordTextBox pwdBox = new PasswordTextBox();
	
	private static class Login
	{
		private int id;
		private String username;
		private String password;
		
		public Login(int id, String un, String pwd)
		{
			this.id = id;
			this.username = un;
			this.password = pwd;
		}
	}
	public void onModuleLoad()
	{
       //String url = baseURL + "/students/index.json";
       //getRequest(url,"getStudents");
       loginButton.addClickHandler(this);
       RootPanel.get().add(mainPanel);
       setupLogin();
       //setupAddStudent();
	}
	public void onClick(ClickEvent e)
	{
	   Object source = e.getSource();
	   if (source == loginButton) {
		  String postData = URL.encode("username") + "=" + 
	         URL.encode(unBox.getText()) + "&" + 
		     URL.encode("password") + "=" +
	         URL.encode(pwdBox.getText());
	      String url = baseURL + "/pages/login";
	      postRequest(url,postData,"postLogin");
	   }
	}
	public void getRequest(String url, final String getType) {
	   final RequestBuilder rb = new
	      RequestBuilder(RequestBuilder.GET,url);
	   try {
		   rb.sendRequest(null, new RequestCallback()
		   {
			   public void onError(final Request request,
			      final Throwable exception)
			   {
				  Window.alert(exception.getMessage());
			   }
			   public void onResponseReceived(final Request request, final Response response)
			   {
				   if (getType.equals("postData")) {
					   //Window.alert(response.getText());
					   //showStudents(response.getText());
				   }
			   }
		   });
	   }
	   catch (final Exception e) {
		  Window.alert(e.getMessage());
	   }
	} // end getRequest
	public void postRequest(String url, String data,
	   final String postType) 
	{
	   final RequestBuilder rb = new
	      RequestBuilder(RequestBuilder.POST,url);
	   rb.setHeader("Content-type", 
	      "application/x-www-form-urlencoded");
	   try {
	      rb.sendRequest(data, new RequestCallback()
		  {
		     public void onError(final Request request,
			    final Throwable exception)
		     {
			    Window.alert(exception.getMessage());
			 }
		     public void onResponseReceived(final Request request, final Response response)
		     {
			    if (postType.equals("postStudent") || 
			        postType.equals("deleteStudent") ||
			        postType.equals("editStudent")) {
			       mainPanel.clear();
			       String url = baseURL + "/students/index.json";
			       getRequest(url,"getStudents");
		        }
			    else if (postType.equals("postLogin")) {
			       int id = Integer.parseInt(response.getText());
			       if (id < 0){
			          Window.alert("Invalid User");
			       }
		           if (id > 0)
		        	  Window.alert("User Login Sucessfully");
			    }
		     }
          });
	   }
	   catch (final Exception e) {
	      Window.alert(e.getMessage());
	   }
	}///end postRequest
	
	private void setupLogin()
	{
	   mainPanel.clear();
	   VerticalPanel addStudentPanel = new VerticalPanel();
	   Label fnLabel = new Label("Username");
	   HorizontalPanel fnRow = new HorizontalPanel();
	   fnRow.add(fnLabel);
	   fnRow.add(unBox);
	   addStudentPanel.add(fnRow);
	   Label lnLabel = new Label("Password");
	   HorizontalPanel lnRow = new HorizontalPanel();
	   lnRow.add(lnLabel);
	   lnRow.add(pwdBox);
	   addStudentPanel.add(lnRow);
	   addStudentPanel.add(loginButton);
	   mainPanel.add(addStudentPanel);
	}
	//private JsArray<Student> getData(String json)
	//{
	//	return JsonUtils.safeEval(json);
	//}
}   


