package gui;

import java.util.ArrayList;
import java.util.List;
import controllers.GroupController;
import controllers.GroupSearchController;
import controllers.JoinRequestController;
import controllers.UserSession;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Gruppo;

public class SearchPage {
		
	private static final String UNHIGHLIGHTED_STYLE = 
			"-fx-font-family: 'Product Sans'; -fx-font-size: 11; -fx-text-fill: black;" +
			"-fx-background-color: white; -fx-background-radius: 20;" +
			"-fx-border-color: #dbdbdb; -fx-border-radius: 20;";
	
	private static final String HIGHLIGHTED_STYLE =  					
			"-fx-font-family: 'Product Sans'; -fx-font-size: 11; -fx-text-fill: white;" +
			"-fx-background-color: #0e5460; -fx-background-radius: 20;" +
			"-fx-border-color: #136472; -fx-border-radius: 20;";
	
	private static final String PULSATING_STYLE = 
			"-fx-font-family: 'Product Sans'; -fx-font-size: 11; -fx-text-fill: white;" +
			"-fx-background-color: red; -fx-background-radius: 20;" +
			"-fx-border-color: red; -fx-border-radius: 20;";
	
	private IconUtils iconUtils = new IconUtils();
    private ScrollPane scrollPane;
    private BorderPane borderPane;
	private VBox searchSection;
	private HBox filterBox;
	private ToggleGroup filterButtonsToggleGroup;
	private TextField textField;
	private ToggleButton nameFilterToggleButton;
	private ToggleButton topicFilterToggleButton;
	private TilePane resultsTilePane;
	
    private GroupSearchController groupSearchController;
    private GroupController groupController;
    private JoinRequestController joinRequestController;
    private UserSession userSession;

    public SearchPage(UserSession userSession) throws Exception {
        this.groupSearchController = new GroupSearchController();
        this.groupController = new GroupController();
        this.joinRequestController = new JoinRequestController();
        this.userSession = userSession;
    }
  	
	public BorderPane setupSearchPage() {

		//CREATE BASE PANE
		borderPane = new BorderPane();
		borderPane.setStyle("-fx-background-color: white;");
		AnchorPane.setTopAnchor(borderPane, 0.0);
		AnchorPane.setLeftAnchor(borderPane, 0.0);
		AnchorPane.setRightAnchor(borderPane, 0.0);
		AnchorPane.setBottomAnchor(borderPane, 0.0);
		
		this.setupTopSection(borderPane);
		
		this.setupCenterSection(borderPane);
        
        this.configureSearchBehavior();

        return borderPane;
	}
	
    private void setupTopSection(BorderPane borderPane) {

		AnchorPane topSection = new AnchorPane();
		borderPane.setTop(topSection);
		
		//CREATE BANNER
		StackPane banner = new StackPane();
		topSection.getChildren().add(banner);

		banner.setPrefSize(0, 155);
		banner.setStyle("-fx-background-color: #00958c");
		AnchorPane.setLeftAnchor(banner, 0.0);
		AnchorPane.setTopAnchor(banner, 0.0);
		AnchorPane.setRightAnchor(banner, 0.0);
        banner.prefWidthProperty().bind(borderPane.widthProperty());
        
		//VBOX
		searchSection = new VBox(10);
		topSection.getChildren().add(searchSection);

		AnchorPane.setTopAnchor(searchSection, 20.0);
		AnchorPane.setLeftAnchor(searchSection, 130.0);
		
		//TITLE
		Text title = new Text("Trova nuovi gruppi!");
		searchSection.getChildren().add(title);

		title.setStyle(
				"-fx-font-family: 'Product Sans';" + 
				"-fx-font-size: 30;" + 
				"-fx-fill: white;" +
				"-fx-font-weight: bold;"
		);
		
		
		//SUBTITLE
		Text subtitle = new Text("Ricerca gruppi per nome o per tema.");
		searchSection.getChildren().add(subtitle);

		subtitle.setStyle(
				"-fx-font-family: 'Product Sans';" + 
				"-fx-font-size: 16;" + 
				"-fx-fill: #f4f4f4;"
		);
		VBox.setMargin(subtitle, new Insets(0, 0, 20, 5));
		
		//SEARCH BAR
		this.addSearchBar();
		
		//SEARCH FILTERS
		this.addSearchFilters();
    }
	
	private void addSearchBar() {
		//SEARCH BAR (STACK PANE WITH TEXTFIELD + ICON)
		StackPane searchBar = new StackPane();
		VBox.setMargin(searchBar, new Insets(0, 0, 5, 0));
		
		//TEXT FIELD
		textField = new TextField();
		textField.setMinSize(250, 30);
		textField.setMaxWidth(520);
		textField.setStyle(
				"-fx-background-radius: 20;" +
				"-fx-background-color: #fbfbfb;" +
				"-fx-border-color: #dbdbdb;" +
				"-fx-border-radius: 20;" +
				"-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 8, 0, 0, 0);"
		);
		textField.setPadding(new Insets(0, 5, 0, 35));
		
		textField.prefWidthProperty().bind(Bindings.createDoubleBinding(
                () -> textField.getText().length() * 7.5 + 5, 
                textField.textProperty()
                
        ));
		
		//MAGNIFYING GLASS ICON
		ImageView magnifyingGlassIcon = new ImageView(iconUtils.searchBarIcon);
		magnifyingGlassIcon.setFitWidth(15);
		magnifyingGlassIcon.setFitHeight(15);				
		StackPane.setAlignment(magnifyingGlassIcon, Pos.CENTER_LEFT);
		magnifyingGlassIcon.setTranslateX(10);

		//ADD TEXT FIELD AND MAGNIFYING GLASS ICON TO SEARCH BAR
		searchBar.getChildren().addAll(textField, magnifyingGlassIcon);

		//ADD FINISHED SEARCH BAR TO CONTENT VBOX
		searchSection.getChildren().add(searchBar);
		
	}
	
	private void addSearchFilters() {
		
		filterBox = new HBox(10);
		VBox.setMargin(filterBox, new Insets(0, 0, 20, 0));
		searchSection.getChildren().add(filterBox);
		
		Text text = new Text("Ricerca per: ");
		text.setStyle(
				"-fx-font-family: 'Product Sans';" + 
				"-fx-font-size: 13;" + 
				"-fx-fill: black;"
		);
		HBox.setMargin(text, new Insets(3, 0, 0, 10));
		filterBox.getChildren().add(text);	
		
		filterButtonsToggleGroup = new ToggleGroup();		
		nameFilterToggleButton = this.addFilterToggleButton("nome");
		topicFilterToggleButton = this.addFilterToggleButton("tema");

	}
	
	private ToggleButton addFilterToggleButton(String filter) {
		
		ToggleButton filterToggleButton = new ToggleButton(filter);
		filterToggleButton.setStyle(UNHIGHLIGHTED_STYLE);
		filterToggleButton.setToggleGroup(filterButtonsToggleGroup);
		filterBox.getChildren().add(filterToggleButton);
		
		//HIGHLIGHT BUTTON WHEN YOU HOVER OVER IT
		filterToggleButton.setOnMouseEntered(e -> {
			filterToggleButton.setStyle(HIGHLIGHTED_STYLE);
		});
		
		//UNHIGHLIGHT BUTTON WHEN YOU STOP HOVERING OVER IT
		filterToggleButton.setOnMouseExited(e -> {
			if(!filterToggleButton.isSelected()) {
				filterToggleButton.setStyle(UNHIGHLIGHTED_STYLE);
			}
		});
		
		//HIGHLIGHT BUTTON WHEN SELECTED
		filterToggleButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {filterToggleButton.setStyle(HIGHLIGHTED_STYLE);}
			else {filterToggleButton.setStyle(UNHIGHLIGHTED_STYLE);}
		});
		
		return filterToggleButton;
	}
	
    private void setupCenterSection(BorderPane borderPane) {
    	
        resultsTilePane = new TilePane();
        resultsTilePane.setOrientation(Orientation.HORIZONTAL);
        resultsTilePane.setPadding(new Insets(20, 130, 40, 130));
        resultsTilePane.setHgap(20);
        resultsTilePane.setVgap(20);
        resultsTilePane.setPrefColumns(3);
        
		scrollPane = new ScrollPane();
		scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-viewport-background: transparent; -fx-control-inner-background: transparent;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		
		String cssPath = getClass().getResource("scrollPaneStyle.css").toExternalForm();
        scrollPane.getStylesheets().add(cssPath);
        
		AnchorPane.setLeftAnchor(scrollPane, 0.0);
		AnchorPane.setRightAnchor(scrollPane, 0.0);
		AnchorPane.setBottomAnchor(scrollPane, 0.0);
        
        resultsTilePane.prefWidthProperty().bind(scrollPane.widthProperty());
        scrollPane.setContent(resultsTilePane);

        borderPane.setCenter(scrollPane);
    }
     
    private void configureSearchBehavior() {
        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
					handleSearch();
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        });
    }

    private void handleSearch() throws Exception {
        String textEntered = textField.getText();

        if (!textEntered.isEmpty()) {
            ToggleButton selectedButton = (ToggleButton) filterButtonsToggleGroup.getSelectedToggle();

            if (selectedButton == null) {
                pulsateButton(nameFilterToggleButton);
                pulsateButton(topicFilterToggleButton);
            } else {
                performSearch(selectedButton.getText(), textEntered);
            }
        }
    }
    
	private void pulsateButton(ToggleButton toggleButton) {
		
		toggleButton.setStyle(PULSATING_STYLE);   
		
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(toggleButton.opacityProperty(), 0.3)),
                new KeyFrame(Duration.seconds(0.8), new KeyValue(toggleButton.opacityProperty(), 0.8)),
                new KeyFrame(Duration.seconds(1.3), new KeyValue(toggleButton.opacityProperty(), 0.0))
        );
        
        timeline.setCycleCount(1);                        
        timeline.play();   
        
        timeline.setOnFinished(e -> {
        	toggleButton.setOpacity(1);
        	toggleButton.setStyle(UNHIGHLIGHTED_STYLE);} );
	}

    private void performSearch(String searchBy, String textEntered) throws Exception {
        textField.clear();

        List<Gruppo> searchResults = new ArrayList<>();

        if (searchBy.equals("nome")) {
            searchResults = groupSearchController.searchGroupsByName(textEntered);
        }
        
        if (searchBy.equals("tema")) {
            searchResults = groupSearchController.searchGroupsByTopic(textEntered);
        }

        if (searchResults.isEmpty()) {
        	displayNoResultsMessage();
        }
        
        if (!searchResults.isEmpty()) {
        	displaySearchResults(searchResults);
        }  
    }
    
    private void displayNoResultsMessage() {
    	StackPane pane = new StackPane();
        pane.setMinWidth(borderPane.getWidth());
        
    	VBox noResultsFoundMessage = new VBox();
    	noResultsFoundMessage.setAlignment(Pos.CENTER);
    	
    	ImageView png = new ImageView(new Image(getClass().getResourceAsStream("noResultsFound.png")));
    	png.setFitWidth(250);
    	png.setFitHeight(150);
    	
    	Text line1 = new Text("Nessun risultato.");
        line1.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 20; -fx-fill: #656565; -fx-font-weight: bold;");

    	Text line2 = new Text("Ci dispiace, non abbiamo trovato risultati");
        line2.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 16; -fx-fill: #999999;");

    	Text line3 = new Text("corrispondenti alla tua ricerca");
        line3.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 16; -fx-fill: #999999;");

    	noResultsFoundMessage.getChildren().addAll(png, line1, line2, line3);
    	pane.getChildren().add(noResultsFoundMessage);
    	borderPane.setCenter(pane);
    }
    
    private void displaySearchResults(List<Gruppo> searchResults) throws Exception {
        resultsTilePane.getChildren().clear();
        borderPane.setCenter(scrollPane);

        for (Gruppo group : searchResults) {
            VBox result = createResultVBox(group);
            resultsTilePane.getChildren().add(result);
        }
    }

    private VBox createResultVBox(Gruppo group) throws Exception {
        VBox result = new VBox(5);
        result.setStyle(
        		"-fx-background-color: #fefefe;" + 
        		"-fx-background-radius: 5;" + 
        		"-fx-padding: 20;" + 
        		"-fx-effect: dropshadow(gaussian, gray, 5, 0, 0, 0);"
        );
        result.setPrefWidth(250);

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

        Button join = createJoinGroupButton(group);

        result.getChildren().addAll(groupName, groupTopic, groupDescription, memberCountBox, postCountBox, join);
        return result;
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

    private Button createJoinGroupButton(Gruppo group) throws Exception {
        Button joinGroupButton = new Button();
        VBox.setMargin(joinGroupButton, new Insets(10, 0, 0, 0));

        boolean isUserMember = groupController.isUserMemberOfGroup(group, userSession.getLoggedUser());

        if (isUserMember) {
        	joinGroupButton.setText("Sei già un membro");
        	joinGroupButton.setStyle("-fx-background-color: #eeeeee; -fx-text-fill: #bcbcbc; -fx-font-family: 'Product Sans'; -fx-font-size: 11; -fx-font-style: italic;");
        }
        
        if(!isUserMember) {
            boolean userHasPendingRequest = joinRequestController.checkIfUserHasPendingRequest(group, userSession.getLoggedUser());

            if (userHasPendingRequest) {
            	joinGroupButton.setText("✓ Richiesta inviata");
            	joinGroupButton.setStyle("-fx-background-color: #eeeeee; -fx-text-fill: #bcbcbc; -fx-font-family: 'Product Sans'; -fx-font-size: 11; -fx-font-style: italic;");
            }
            	
            if (!userHasPendingRequest) {
            	joinGroupButton.setText("Richiedi accesso");
            	joinGroupButton.setStyle("-fx-background-color: #63b448; -fx-text-fill: white; -fx-font-family: 'Product Sans'; -fx-font-size: 11; -fx-font-style: italic; -fx-font-weight: bold");

            	joinGroupButton.setOnMouseEntered(event -> joinGroupButton.setCursor(Cursor.HAND));
            	joinGroupButton.setOnMouseExited(event -> joinGroupButton.setCursor(Cursor.DEFAULT));

            	joinGroupButton.setOnAction(event -> {
					try {
						handleJoinGroupButtonClick(group, joinGroupButton);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
            }
        }

        return joinGroupButton;
    }

    private void handleJoinGroupButtonClick(Gruppo group, Button joinGroupButton) throws Exception {
    	boolean insertedSuccessfully = joinRequestController.sendJoinRequest(group, userSession);

        if (insertedSuccessfully) {
        	joinGroupButton.setText("✓ Richiesta inviata");
        	joinGroupButton.setStyle("-fx-background-color: #eeeeee; -fx-text-fill: #bcbcbc; -fx-font-family: 'Product Sans'; -fx-font-size: 11; -fx-font-style: italic;");
        }
    }
    
 

}
