package impl.tewrrss.business;

import com.tewrrss.business.*;

public class ServicesFactoryImpl implements ServicesFactory {

	@Override
	public LoginService createLoginService() {
		return new LoginServiceImpl();
	}

	@Override
	public DatabaseService createDatabaseService() {
		return new DatabaseServiceImpl();
	}
  
	@Override
	public CommunityService createCommunityService() {
		return new CommunityServiceImpl();
	}
	
	@Override
	public UserService createUserService() {
		return new UserServiceImpl();
	}
	
	@Override
	public PostsService createPostsService() {
		return new PostsServiceImpl();
	}
}
