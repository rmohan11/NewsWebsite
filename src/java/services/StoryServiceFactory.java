package services;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;

public class StoryServiceFactory {
	
	private StoryServiceFactory() {}
        
	public static StoryService getInstance() {
            
            if (__storyservice == null)
            {
		__storyservice = new InMemory();
            }
	
            return __storyservice;
	}

    private static StoryService __storyservice;
    
	static {
		try {
			Properties dbProperties = new Properties();
			Class<?> initClass = null;
			
                        Context ctx = new InitialContext();
                        Context env = (Context) ctx.lookup("java:comp/env");
                        final String fileName = (String) env.lookup("daoPropertiesFile");
                        
			dbProperties.load(StoryServiceFactory.class.getClassLoader().getResourceAsStream(fileName));
			String serviceImpl = dbProperties.getProperty("serviceImpl");
			
			if (serviceImpl != null) {
				initClass = Class.forName(serviceImpl);
			} else {
				initClass = Class.forName("services.InMemory");
			}
			__storyservice = (StoryService)initClass.newInstance();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
		}
}

}
