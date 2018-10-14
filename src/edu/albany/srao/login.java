package edu.albany.srao;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "login")
@SessionScoped

public class login {
	
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	boolean isLoginPage = (FacesContext.getCurrentInstance().getViewRoot().getViewId().lastIndexOf("login.xhtml") > -1);
	
	public String logindb(){
		boolean result=userDAO.login(username, password);
		if(result){
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", username);
			return "loginSuccess";
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid Login!", "Please Try Again!"));
			return "loginFail";
		}
	}
	public String logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "index.xhtml?faces-redirect=true";
	}
	
	
}
