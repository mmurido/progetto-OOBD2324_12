package gui;

import java.util.List;
import controllers.GroupController;
import controllers.UserSession;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Gruppo;

public class HomepageSuggestedGroupsSection {
	
	private UserSession userSession;
	private GroupController groupController;
		
	public HomepageSuggestedGroupsSection(UserSession userSession) throws Exception {
		this.userSession = userSession;
		this.groupController = new GroupController();
	}
	
	public BorderPane setupSuggestedGroupsSection() throws Exception {
        BorderPane suggestionsContainer = new BorderPane();
        suggestionsContainer.setMinWidth(300);
        suggestionsContainer.setStyle(
        		"-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, white , #f9f9ff);" + 
        		"-fx-background-radius: 5;" +
        		"-fx-effect: dropshadow(gaussian, gray, 5, 0, 0, 0);"
        );

        StackPane labelBar = new StackPane();
        suggestionsContainer.setTop(labelBar);
        labelBar.setPrefHeight(30);
        labelBar.setStyle(
        		"-fx-background-color: #fbfbfb;" + 
        		"-fx-background-radius: 5 5 0 0;" + 
        		"-fx-border-color: #cecece;" +
        		"-fx-border-width: 0 0 1 0;"
        );
        
        Text label = new Text("Suggerimenti");
        labelBar.getChildren().add(label);
        label.setStyle(
				"-fx-font-family: 'Product Sans';" + 
				"-fx-font-size: 15;" + 
				"-fx-fill: #cecece;" +
				"-fx-font-weight: bold;"
        );
        
        ScrollPane scrollPane = new ScrollPane();
        suggestionsContainer.setCenter(scrollPane);
		scrollPane.setStyle(
				"-fx-background: transparent;" + 
				"-fx-background-color: transparent;" + 
				"-fx-viewport-background: transparent;" + 
				"-fx-control-inner-background: transparent;"
		);
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		String cssPath2 = getClass().getResource("scrollPaneStyle.css").toExternalForm();
        scrollPane.getStylesheets().add(cssPath2);
        
        List<Gruppo> suggestedGroups = groupController.getSuggestedGroups(userSession.getLoggedUser());
        
        VBox suggestedGroupsBox = new VBox(30);
        scrollPane.setContent(suggestedGroupsBox);
        suggestedGroupsBox.setPadding(new Insets(10, 20, 15, 20));
        
        for (Gruppo group : suggestedGroups) {
            suggestedGroupsBox.getChildren().add(createSuggestedGroupBox(group));
        }
        
        return suggestionsContainer;        
    }
    
    private VBox createSuggestedGroupBox(Gruppo group) throws Exception {
        VBox vbox = new VBox(5);
        vbox.setStyle(
        		"-fx-background-color: #fefefe;" + 
        		"-fx-background-radius: 5;" + 
        		"-fx-padding: 20;" + 
        		"-fx-effect: dropshadow(gaussian, gray, 5, 0, 0, 0);"
        );
        vbox.setPrefWidth(250);

        Text groupName = new Text(group.getNome());
        groupName.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 16; -fx-fill: #0e5460; -fx-font-weight: bold;");
		VBox.setMargin(groupName, new Insets(0, 0, -7, 0));
        
        Text groupTopic = new Text("Tema: " + group.getTema().getTema());
        groupTopic.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 14; -fx-fill: #e6ad38; -fx-font-weight: bold;");
        
        Text groupDescription = new Text(group.getDescrizione());
        groupDescription.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 13; -fx-fill: black;");
        groupDescription.setWrappingWidth(250);

        HBox memberCountBox = createMemberCountBox("membersIcon.png", groupController.getMemberCount(group));
        HBox postCountBox = createPostCountBox("postIcon.png", groupController.getPostCount(group));

        vbox.getChildren().addAll(groupName, groupTopic, groupDescription, memberCountBox, postCountBox);
        return vbox;
    }
    
    private HBox createMemberCountBox(String iconName, int count) {
        HBox infoBox = new HBox(4);

        ImageView icon = new ImageView(new Image(getClass().getResourceAsStream(iconName)));
        icon.setFitWidth(15);
        icon.setFitHeight(15);
        infoBox.getChildren().add(icon);

        Text countText = new Text();
        countText.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 13; -fx-fill: #999999;");
        
        if (count == 1) {
        	countText.setText(count + " membro");
        }
        else {
        	countText.setText(count + " membri");
        }
        
        infoBox.getChildren().add(countText);

        return infoBox;
    }
    
    private HBox createPostCountBox(String iconName, int count) {
        HBox infoBox = new HBox(4);

        ImageView icon = new ImageView(new Image(getClass().getResourceAsStream(iconName)));
        icon.setFitWidth(15);
        icon.setFitHeight(15);
        infoBox.getChildren().add(icon);

        Text countText = new Text();
        countText.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 13; -fx-fill: #999999;");
        countText.setText(count + " post");

        infoBox.getChildren().add(countText);

        return infoBox;
    }
}
