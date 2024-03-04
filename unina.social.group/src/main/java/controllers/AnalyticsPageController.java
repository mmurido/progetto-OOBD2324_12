package controllers;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import DAO.GruppoDAO;
import DAO.PostDAO;
import gui.analyticsPage.AnalyticsFilterBox;
import gui.analyticsPage.AnalyticsPage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Gruppo;
import model.Post;
import model.Utente;

public class AnalyticsPageController {

	private AnalyticsPage analyticsPage;
	private GruppoDAO gruppoDAO;
	private PostDAO postDAO;
	public List<Gruppo> ownedGroups;
	public Gruppo selectedGroup;
	public int selectedYear;
	public int selectedMonth;
	public Post mostLikedPost ;
	public Post leastLikedPost;
	public Post mostCommentedPost;
	public Post leastCommentedPost;
	public int highestLikeCount;
	public int lowestLikeCount;
	public int highestCommentsCount;
	public int lowestCommentsCount;

    public AnalyticsPageController(AnalyticsPage analyticsPage) {
    	this.analyticsPage = analyticsPage;
        this.gruppoDAO = new GruppoDAO();
        this.postDAO = new PostDAO();
        ownedGroups = getGroupsOwned(UserSession.getLoggedUser());
    }

    public List<Gruppo> getGroupsOwned(Utente user) {
    	List<Gruppo> groups = new ArrayList<>();
    	
    	try {
        	groups = gruppoDAO.getGroupsOwned(user.getIdUtente());
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return groups;
    }
    
    public void getMostLikedPostOfGroupInMonthYear() {
    	Object[] postAndCount = postDAO.getMostLikedPostOfGroupInMonthYear(
    			selectedGroup.getIdGruppo(), selectedMonth, selectedYear);
    	
    	if (postAndCount != null) {
        	mostLikedPost = (Post) postAndCount[0];
        	highestLikeCount = (int) postAndCount[1];
    	}
    	else {
        	mostLikedPost = null;
        	highestLikeCount = 0;
    	}
    }
    
    public void getLeastLikedPostOfGroupInMonthYear() {
    	Object[] postAndCount = postDAO.getLeastLikedPostOfGroupInMonthYear(
    			selectedGroup.getIdGruppo(), selectedMonth, selectedYear);
    	
    	if (postAndCount != null) {
        	leastLikedPost = (Post) postAndCount[0];
        	lowestLikeCount = (int) postAndCount[1];
    	}
    	else {
        	leastLikedPost = null;
        	lowestLikeCount = 0;
    	}
    	
    }
    
    public void getMostCommentedPostOfGroupInMonthYear() {
    	Object[] postAndCount = postDAO.getMostCommentedPostOfGroupInMonthYear(
    			selectedGroup.getIdGruppo(), selectedMonth, selectedYear); 
    	
    	if (postAndCount != null) {
        	mostCommentedPost = (Post) postAndCount[0];
        	highestCommentsCount = (int) postAndCount[1];
    	}
    	else {
        	mostCommentedPost = null;
        	highestCommentsCount = 0;
    	}
    }
    
    public void getLeastCommentedPostOfGroupInMonthYear() {
    	Object[] postAndCount = postDAO.getLeastCommentedPostOfGroupInMonthYear(
    			selectedGroup.getIdGruppo(), selectedMonth, selectedYear);	
    	
    	if (postAndCount != null) {
        	leastCommentedPost = (Post) postAndCount[0];
        	lowestCommentsCount = (int) postAndCount[1];
    	}
    	else {
        	leastCommentedPost = null;
        	lowestCommentsCount = 0;
    	}
    }
    
    public double calculateAveragePosts() {
    	int totalPostCount = postDAO.getPostCountOfGroupInMonthYear(
    				selectedGroup.getIdGruppo(), selectedMonth, selectedYear);

        YearMonth yearMonth = YearMonth.of(selectedYear, selectedMonth);

        int numberOfDaysInMonth = yearMonth.lengthOfMonth();
        
        double averagePostsPerDay = (double) totalPostCount / numberOfDaysInMonth;
    	
    	return averagePostsPerDay;
    }
    
    public Map<Integer, Integer> getPostsCountPerDay() {
    	return postDAO.getPostsCountPerDay(
    			selectedGroup.getIdGruppo(), selectedMonth, selectedYear);
    }
    
	public void setItemsIntoGroupChoiceBox() {
		ObservableList<String> groupOptions = FXCollections.observableArrayList();

		for (Gruppo group : ownedGroups) {
			groupOptions.add(group.getNome());
		}

		analyticsPage.filterBox.groupChoiceBox.setItems(groupOptions);
	}
    
    public void onGroupSelected() {
		analyticsPage.filterBox.yearChoiceBox.setDisable(false);

		String selectedGroupName = analyticsPage.filterBox.groupChoiceBox.getValue();

		for (Gruppo group : ownedGroups) {
			if (group.getNome().equals(selectedGroupName)) {
				selectedGroup = group;
				break;
			}
		}
		
        analyticsPage.filterBox.blockSelection(analyticsPage.filterBox.groupChoiceBox);
		setItemsIntoYearChoiceBox();
    }
    
    public void onYearSelected() {
        selectedYear = Integer.parseInt(analyticsPage.filterBox.yearChoiceBox.getValue());
        analyticsPage.filterBox.monthChoiceBox.setDisable(false);
        analyticsPage.filterBox.blockSelection(analyticsPage.filterBox.yearChoiceBox);
        setItemsIntoMonthChoiceBox();
    }
    
	public void onMonthSelected() {
        List<String> allMonths = Arrays.asList(
                "Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno",
                "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"
        );
        
		selectedMonth = allMonths.indexOf(analyticsPage.filterBox.monthChoiceBox.getValue()) +1;
		
		analyticsPage.filterBox.blockSelection(analyticsPage.filterBox.monthChoiceBox);
    			
		analyticsPage.filterBox.addClearAndShowButton();
	}
    
	private void setItemsIntoYearChoiceBox() {
		int yearOfCreation = selectedGroup.getDataOraCreazione().getYear();

		int currentYear = LocalDate.now().getYear();

		ObservableList<String> yearOptions = FXCollections.observableArrayList();

		for (int year = yearOfCreation; year <= currentYear; year++) {
			yearOptions.add(String.valueOf(year));
		}

		analyticsPage.filterBox.yearChoiceBox.setItems(yearOptions);
	}
    
	private void setItemsIntoMonthChoiceBox() {
        int yearOfCreation = selectedGroup.getDataOraCreazione().getYear();
		int monthOfCreation = selectedGroup.getDataOraCreazione().getMonth().getValue();
        int currentYear = LocalDate.now().getYear();
        
        List<String> allMonths = Arrays.asList(
                "Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno",
                "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"
        );
        
        ObservableList<String> filteredMonths = FXCollections.observableArrayList();
        
        if (selectedYear == yearOfCreation) {
        	
        	if (yearOfCreation == currentYear) {
	            filteredMonths = FXCollections.observableArrayList(allMonths.subList(monthOfCreation-1, LocalDate.now().getMonthValue()));;
        	}
        	else {
	            filteredMonths = FXCollections.observableArrayList(allMonths.subList(monthOfCreation-1, allMonths.size()));
        	}

        } else if (selectedYear == currentYear) {
        	filteredMonths = FXCollections.observableArrayList(allMonths.subList(0, LocalDate.now().getMonthValue()));
        } else {
            filteredMonths = FXCollections.observableArrayList(allMonths);
        
        }

        analyticsPage.filterBox.monthChoiceBox.setItems(filteredMonths);
	}
	
	public void onClearChoicesButtonClicked() {
		AnalyticsFilterBox filterBox = analyticsPage.filterBox;
		
		//remove previous report
		analyticsPage.reportDisplay.setVisible(false);
		
		//remove clear choices button and show report button from filter box
		filterBox.getChildren().removeAll(
				filterBox.clearChoicesButton, 
				filterBox.showReportButton
		);
		
        //reset group choice box
		filterBox.unblockSelection(analyticsPage.filterBox.groupChoiceBox);
		filterBox.groupChoiceBox.setValue(null);
        
		//reset year choice box
		filterBox.unblockSelection(analyticsPage.filterBox.yearChoiceBox);
		filterBox.yearChoiceBox.getItems().clear();
		filterBox.yearChoiceBox.setDisable(true);
		filterBox.yearChoiceBox.setValue(null);
        
        //reset month choice box
		filterBox.unblockSelection(analyticsPage.filterBox.monthChoiceBox);
		filterBox.monthChoiceBox.getItems().clear();
		filterBox.monthChoiceBox.setDisable(true);
		filterBox.monthChoiceBox.setValue(null);
	}
	
	public void onShowReportButtonClicked() {
		analyticsPage.reportDisplay.displayReport();
		analyticsPage.reportDisplay.setVisible(true);
	}
    
}
