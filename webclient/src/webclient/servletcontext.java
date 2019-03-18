package webclient;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class servletcontext implements ServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
	 database.createTable();
	}
}
