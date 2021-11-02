package logic.controller.applicationcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.engineeringclasses.dao.PostDAO;
import logic.model.Post;
import logic.model.UserSingleton;

public class PostController {

	private final PostDAO post = new PostDAO();
	private final UserSingleton sg = UserSingleton.getInstance();
	
	public ObservableList<Post> loadPost() {
		ObservableList<Post> list = FXCollections.observableArrayList();
		try {
			list = post.checkListPost(sg.getAddress());
			return list ;
		}catch(Exception e) {
			return list;
		}
	}
}
