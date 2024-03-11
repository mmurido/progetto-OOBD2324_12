package controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import DAO.GroupDAO;
import DAO.PostDAO;
import gui.analyticsPage.AnalyticsPage;
import gui.commonComponents.PostCard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Group;
import model.Post;

public class AnalyticsPageController {

	private AnalyticsPage analyticsPage;
	private GroupDAO gruppoDAO;
	private PostDAO postDAO;
	public List<Group> ownedGroups;
	public Group selectedGroup;
	public int selectedYear;
	public int selectedMonth;

    public AnalyticsPageController(AnalyticsPage analyticsPage) {
    	this.analyticsPage = analyticsPage;
        this.gruppoDAO = new GroupDAO();
        this.postDAO = new PostDAO();
    }

    public void getGroupsOwned(String username) {
    	ownedGroups = gruppoDAO.getGroupsOwned(username);
    }
    
    public PostCard getMostLikedPostOfGroupInMonthYear() {
    	Object[] postAndCount = postDAO.getMostLikedPostOfGroupInMonthYear(
    			selectedGroup.getId(), selectedMonth, selectedYear);
    	
    	if (postAndCount == null)
    		return null;

    	Post mostLikedPost = (Post) postAndCount[0];
    	int highestLikeCount = (int) postAndCount[1];

		PostCard postCard = createPostCard(mostLikedPost);
		postCard.displayLikeCount(highestLikeCount);
		
		return postCard;	
    }
    
    public PostCard getLeastLikedPostOfGroupInMonthYear() {
    	Object[] postAndCount = postDAO.getLeastLikedPostOfGroupInMonthYear(
    			selectedGroup.getId(), selectedMonth, selectedYear);
    	
    	if (postAndCount == null) 
    		return null;
    	
    	Post leastLikedPost = (Post) postAndCount[0];
    	int lowestLikeCount = (int) postAndCount[1];

		PostCard postCard = createPostCard(leastLikedPost);
		postCard.displayLikeCount(lowestLikeCount);
		
		return postCard;    	
    }
    
    public PostCard getMostCommentedPostOfGroupInMonthYear() {
    	Object[] postAndCount = postDAO.getMostCommentedPostOfGroupInMonthYear(
    			selectedGroup.getId(), selectedMonth, selectedYear); 
    	
    	if (postAndCount == null) 
    		return null;
    	
        Post mostCommentedPost = (Post) postAndCount[0];
        int highestCommentsCount = (int) postAndCount[1];
        
		PostCard postCard = createPostCard(mostCommentedPost);
		postCard.displayCommentsCount(highestCommentsCount);
		
		return postCard;
    }
    
    public PostCard getLeastCommentedPostOfGroupInMonthYear() {
    	Object[] postAndCount = postDAO.getLeastCommentedPostOfGroupInMonthYear(
    			selectedGroup.getId(), selectedMonth, selectedYear);	
    	
    	if (postAndCount == null)
    		return null;
    		
        Post leastCommentedPost = (Post) postAndCount[0];
        int lowestCommentsCount = (int) postAndCount[1];
        
		PostCard postCard = createPostCard(leastCommentedPost);
		postCard.displayCommentsCount(lowestCommentsCount);
		
		return postCard;  
	}
    
    public double calculateAveragePosts() {
    	int totalPostCount = postDAO.getPostCountOfGroupInMonthYear(
    				selectedGroup.getId(), selectedMonth, selectedYear);

        YearMonth yearMonth = YearMonth.of(selectedYear, selectedMonth);

        int numberOfDaysInMonth = yearMonth.lengthOfMonth();
        
        double averagePostsPerDay = (double) totalPostCount / numberOfDaysInMonth;
    	
    	return averagePostsPerDay;
    }
    
    public Map<Integer, Integer> getPostsCountPerDay() {
    	return postDAO.getPostsCountPerDay(
    			selectedGroup.getId(), selectedMonth, selectedYear);
    }
    
    public void getGroupOptions() {
        getGroupsOwned(UserSession.getLoggedUserUsername());
        
        ObservableList<String> groupOptions = FXCollections.observableArrayList();
        
        for(Group group: ownedGroups) {
        	String groupName = group.getName();
        	groupOptions.add(groupName);
        }
        
        analyticsPage.setGroupOptions(groupOptions);
    }
    
	public void onGroupSelected(String selectedGroupName) {
		for (Group group : ownedGroups) {
			if (group.getName().equals(selectedGroupName)) {
				selectedGroup = group;
				break;
			}
		}
		
		getYearOptionsBasedOn(selectedGroup);
	}
	
	private void getYearOptionsBasedOn(Group selectedGroup) {
		int yearOfCreation = selectedGroup.getCreatedAt().getYear();

		int currentYear = LocalDate.now().getYear();

		ObservableList<String> yearOptions = FXCollections.observableArrayList();

		for (int year = yearOfCreation; year <= currentYear; year++) {
			yearOptions.add(String.valueOf(year));
		}

		analyticsPage.setYearOptions(yearOptions);
	}
	
	public void onYearSelected(int selectedYear) {
		this.selectedYear = selectedYear;
		getMonthOptionsBasedOn(selectedYear);
	}
	
	private void getMonthOptionsBasedOn(int selectedYear) {
        int yearOfCreation = selectedGroup.getCreatedAt().getYear();
		int monthOfCreation = selectedGroup.getCreatedAt().getMonth().getValue();
        int currentYear = LocalDate.now().getYear();
        
        List<String> allMonths = Arrays.asList(
                "Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno",
                "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"
        );
        
        ObservableList<String> monthOptions = FXCollections.observableArrayList();
        
        if (selectedYear == yearOfCreation && yearOfCreation == currentYear) 
        	monthOptions = FXCollections.observableArrayList(
        			allMonths.subList(monthOfCreation-1, LocalDate.now().getMonthValue()));;
	          
	    if (selectedYear == yearOfCreation && yearOfCreation != currentYear)
	    	monthOptions = FXCollections.observableArrayList(
	    			allMonths.subList(monthOfCreation-1, allMonths.size()));
        	
	    if (selectedYear != yearOfCreation && selectedYear == currentYear)
        	monthOptions = FXCollections.observableArrayList(
        			allMonths.subList(0, LocalDate.now().getMonthValue()));

	    if (selectedYear != yearOfCreation && selectedYear != currentYear)
            monthOptions = FXCollections.observableArrayList(allMonths);

        analyticsPage.setMonthOptions(monthOptions);
	}
	
	public void onMonthSelected(int selectedMonth) {
		this.selectedMonth = selectedMonth;
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
    
}
