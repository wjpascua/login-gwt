package test.client;

import com.google.gwt.core.client.JavaScriptObject;
class Login extends JavaScriptObject
{
   protected Login()
   {}
   public final native int getID()
   /*-{
      return this.login.id;
   }-*/;
   public final native int getUsername()
   /*-{
      return this.login.username;
   }-*/;
   public final native int getPassword()
   /*-{
      return this.login.password;
   }-*/;
}
