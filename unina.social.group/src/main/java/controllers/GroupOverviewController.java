package controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import DAO.GroupDAO;
import DAO.PostDAO;
import gui.commonComponents.PostCard;
import gui.groupOverview.GroupOverview;
import model.Group;
import model.Post;

public class GroupOverviewController extends GroupController {

	private GroupOverview groupOverview;
	
	public GroupOverviewController(GroupOverview groupOverview) {
		super(); 
		this.groupOverview = groupOverview;
	}

	public void populateFeedBoxFor(String groupId) {
		boolean isMember = isUserMemberOfGroup(groupId, UserSession.getLoggedUserUsername());
		if (!isMember) {
			groupOverview.handleNonMemberScenario();
			return;
		}

		List<Post> allPosts = getAllPostsOfGroup(groupId);
		if (allPosts.isEmpty()) {
			groupOverview.handleNoPostsScenario();
			return;
		} 
		
		List<PostCard> postCardsToDisplayCards = new ArrayList<>();
		for(Post post: allPosts) {
			PostCard postCard = createPostCard(post);
			postCardsToDisplayCards.add(postCard);
		}		
		groupOverview.displayPostCards(postCardsToDisplayCards);
	}
	
    public List<Post> getAllPostsOfGroup(String groupId) {
    	PostDAO postDAO = new PostDAO();
    	return postDAO.getAllPostsOfGroup(groupId);
    }
	
	private PostCard createPostCard(Post post) {
		String postId = post.getId();
		String postText = post.getText();
		LocalDateTime createdAt = post.getCreatedAt();
		String groupId = post.getGroup().getId();
		String postAuthorUsername = post.getAuthor().getUsername();
		String postAuthorName = post.getAuthor().getName();
		String postAuthorSurname = post.getAuthor().getSurname();
    	
    	PostCard postCard = new PostCard(
    			postId, postText, createdAt, groupId, 
    			postAuthorUsername, postAuthorName, postAuthorSurname);
    	
        return postCard;
	}
	
	public void onPostButtonClicked(String groupId, String textEntered) {
		GroupDAO groupDAO = new GroupDAO();
		Group groupWherePosted = groupDAO.getById(groupId);
		
		Post post = new Post(
				textEntered, LocalDateTime.now(), groupWherePosted, UserSession.getLoggedUser());
		
		PostDAO postDAO = new PostDAO();
		postDAO.insert(post);
		
		populateFeedBoxFor(groupId);
	}
}
