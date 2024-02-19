package gui;

import controllers.UserSession;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Homepage {
	
	private UserSession userSession;
	private HomepageSearchAndSortBox hpSearchAndSortBox;
	private HomepageSuggestedGroupsSection hpSuggestedGroupsSection;
	private HomepageGroupDisplay hpGroupDisplay;
	
	public Homepage(UserSession userSession) throws Exception {
		this.userSession = userSession;
		this.hpSearchAndSortBox = new HomepageSearchAndSortBox(userSession);
		this.hpSuggestedGroupsSection = new HomepageSuggestedGroupsSection(userSession);
		this.hpGroupDisplay = new HomepageGroupDisplay(userSession);
	}

	public Pane setupHomepage() throws Exception {

		//CREATE BORDER PANE
		BorderPane mainBorderPane = new BorderPane();
		mainBorderPane.setStyle("-fx-background-color: white;");
		AnchorPane.setTopAnchor(mainBorderPane, 0.0);
		AnchorPane.setLeftAnchor(mainBorderPane, 0.0);
		AnchorPane.setRightAnchor(mainBorderPane, 0.0);
		AnchorPane.setBottomAnchor(mainBorderPane, 0.0);
		
		this.setupTopSection(mainBorderPane);

		this.setupCenterSection(mainBorderPane);
		
        return mainBorderPane;
	}
    
    private void setupTopSection(BorderPane mainBorderPane) {

    	AnchorPane topSection = new AnchorPane();
		mainBorderPane.setTop(topSection);
		
		//BANNER
		StackPane banner = new StackPane();
		topSection.getChildren().add(banner);

		banner.setPrefSize(0, 80);
        banner.prefWidthProperty().bind(mainBorderPane.widthProperty());
		banner.setStyle("-fx-background-color: #00958c");
		AnchorPane.setLeftAnchor(banner, 0.0);
		AnchorPane.setTopAnchor(banner, 0.0);
		AnchorPane.setRightAnchor(banner, 0.0);
        
		//TITLE
		Text title = new Text("I tuoi gruppi:");
		topSection.getChildren().add(title);

		title.setStyle(
				"-fx-font-family: 'Product Sans';" + 
				"-fx-font-size: 30;" + 
				"-fx-fill: white;" +
				"-fx-font-weight: bold;"
		);
		
		AnchorPane.setTopAnchor(title, 20.0);
		AnchorPane.setLeftAnchor(title, 130.0);
    }
    
    private void setupCenterSection(BorderPane mainBorderPane) throws Exception {

    	BorderPane innerBorderPane = new BorderPane();
		mainBorderPane.setCenter(innerBorderPane);
		
		this.setupUserGroupsPane(innerBorderPane);
		
		this.setupRightSection(innerBorderPane);       
    }
    
    private void setupUserGroupsPane(BorderPane innerBorderPane) throws Exception {
    	//GROUPS TILE PANE
        TilePane groupsTilePane = new TilePane();
        groupsTilePane.setOrientation(Orientation.HORIZONTAL);
        groupsTilePane.setPadding(new Insets(20, 30, 40, 130));
        groupsTilePane.setHgap(20);
        groupsTilePane.setVgap(20);
        groupsTilePane.setPrefColumns(3);
        
        //SCROLL PANE
		ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(groupsTilePane);
        innerBorderPane.setCenter(scrollPane);

		AnchorPane.setLeftAnchor(scrollPane, 0.0);
		AnchorPane.setRightAnchor(scrollPane, 0.0);
		AnchorPane.setBottomAnchor(scrollPane, 0.0);
		
		scrollPane.setStyle(
				"-fx-background: transparent;" + 
				"-fx-background-color: transparent;" + 
				"-fx-viewport-background: transparent;" +
				"-fx-control-inner-background: transparent;"
		);
        
		String cssPath = getClass().getResource("scrollPaneStyle.css").toExternalForm();
        scrollPane.getStylesheets().add(cssPath);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
       
        groupsTilePane.prefWidthProperty().bind(scrollPane.widthProperty());
        
        this.hpGroupDisplay.setGroupsTilePane(groupsTilePane);
        this.hpGroupDisplay.displayAll();
    }
    
    private void setupRightSection(BorderPane innerBorderPane) throws Exception {
        VBox vbox = new VBox(30);
        innerBorderPane.setRight(vbox);
        BorderPane.setMargin(vbox, new Insets(20, 30, 20, 30)); 

        vbox.getChildren().add(hpSearchAndSortBox.setupSearchAndSortBox());
        
        vbox.getChildren().add(hpSuggestedGroupsSection.setupSuggestedGroupsSection());
    }
    
//    public HBox setupSearchAndSortBox() {
//		HBox searchAndSortBox = new HBox(10);
//		searchAndSortBox.setStyle(
//        		"-fx-background-color: #fbfbfb;" + 
//        		"-fx-background-radius: 5;" +
//        		"-fx-effect: dropshadow(gaussian, gray, 5, 0, 0, 0);"
//        );
//		searchAndSortBox.setPadding(new Insets(10, 20, 10, 20));
//		
//		//ADD SEARCH BAR
//        StackPane searchBar = this.setupSearchBar();
//		searchAndSortBox.getChildren().add(searchBar);
//
//		//ADD SORT BUTTONS
//		ToggleButton sortAZButton = this.setupSortButton(new ImageView(iconUtils.sortAZ));
//		ToggleButton sortZAButton = this.setupSortButton(new ImageView(iconUtils.sortZA));
//		sortButtons = new ToggleGroup();		
//		sortAZButton.setToggleGroup(sortButtons);
//		sortZAButton.setToggleGroup(sortButtons);
//		searchAndSortBox.getChildren().addAll(sortAZButton, sortZAButton);
//
//		
//		
//		
//		//------------------------------------------------------------------------------------------
//		sortAZButton.setOnAction(event -> {
//            if (sortAZButton.isSelected()) {
//            	try {
//					this.sortAZ();
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//            } else {
//            	try {
//					this.displayGroups(currentlyDisplayedGroups);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//            }
//        });
//		
//		sortZAButton.setOnAction(event -> {
//            if (sortZAButton.isSelected()) {
//            	try {
//					this.sortZA();
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//            } else {
//            	try {
//					this.displayGroups(currentlyDisplayedGroups);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//            }
//        });
//		
//		//------------------------------------------------------------------------------------------
//		
//		
//		
//		
//		return searchAndSortBox;
//    }
//    
//    private void sortAZ() throws Exception {
//    	List<Gruppo> sorted = new ArrayList<>(currentlyDisplayedGroups);
//        Collections.sort(sorted, Comparator.comparing(Gruppo::getNome));
//        
//		groupsTilePane.getChildren().clear();
//        for (Gruppo group : sorted) {
//            VBox groupVBox = createGroupVBox(group);
//            groupsTilePane.getChildren().add(groupVBox);
//        }
//    }
//    
//    private void sortZA() throws Exception {
//    	List<Gruppo> sorted = new ArrayList<>(currentlyDisplayedGroups);
//        Collections.sort(sorted, Comparator.comparing(Gruppo::getNome, Collections.reverseOrder()));
//        
//		groupsTilePane.getChildren().clear();
//        for (Gruppo group : sorted) {
//            VBox groupVBox = createGroupVBox(group);
//            groupsTilePane.getChildren().add(groupVBox);
//        }    
//    }
    
//    private StackPane setupSearchBar() {
//		StackPane searchBar = new StackPane();
//		
//		//TEXT FIELD
//		TextField textField = new TextField();
//		textField.setMinSize(220, 30);
//		textField.setStyle(
//				"-fx-background-radius: 20;" +
//				"-fx-background-color: white;" +
//				"-fx-border-color: #dbdbdb;" +
//				"-fx-border-radius: 20;" +
//				"-fx-effect: dropshadow(three-pass-box, gray, 2, 0, 0, 0);"
//		);
//		StackPane.setAlignment(textField, Pos.CENTER_LEFT);
//		textField.setPadding(new Insets(0, 5, 0, 35));
//
//		//MAGNIFYING GLASS ICON
//		ImageView magnifyingGlassIcon = new ImageView(iconUtils.searchBarIcon);
//		magnifyingGlassIcon.setFitWidth(15);
//		magnifyingGlassIcon.setFitHeight(15);				
//		StackPane.setAlignment(magnifyingGlassIcon, Pos.CENTER_LEFT);
//		magnifyingGlassIcon.setTranslateX(10);
//
//		//ADD TEXT FIELD AND MAGNIFYING GLASS ICON TO SEARCH BAR
//		searchBar.getChildren().addAll(textField, magnifyingGlassIcon);
//		
//		
//		//-----------------------------------------------------------------------------------------------------
//		textField.setOnKeyPressed(event -> {
//            if (event.getCode() == KeyCode.ENTER) {
//                String textEntered = textField.getText();
//
//
//                                
//                if (!textEntered.isEmpty()) {
//                    textField.clear();
//                    textField.setEditable(false);
//                	
//                    sortButtons.getToggles().forEach(toggle -> ((ToggleButton) toggle).setSelected(false));
//
//                	
//                	try {
//						this.search(textEntered);
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//                	
//                	//HIGHLIGHT TEXT ENTERED
//                	Button highlightedText = new Button();
//                	highlightedText.setPadding(new Insets(0, 3, 0, 3));
//                	highlightedText.setMaxHeight(8);
//
//                	searchBar.getChildren().add(highlightedText);
//                	StackPane.setAlignment(highlightedText, Pos.CENTER_LEFT);
//                	highlightedText.setTranslateX(35);
//                	highlightedText.setStyle("-fx-background-color: #E3E6E8; -fx-background-radius: 5; -fx-font-family: 'Product Sans'; -fx-font-size: 14; -fx-fill: #444444; -fx-alignment: baseline-left;");
//                	                	
//                	if (textEntered.length()>13) {
//                		highlightedText.setText(textEntered.substring(0, 10) + "...");
//                	}
//                	else {
//                		highlightedText.setText(textEntered);
//                	}
//                	              	
//                	
//                	Button deleteButton = new Button();
//                	deleteButton.setText("✕");
//                	deleteButton.setStyle("-fx-background-color: transparent; -fx-font-family: 'Comfortaa'; -fx-font-size: 14; -fx-text-fill: #5b5b5b; -fx-font-weight: bold;");
//                	searchBar.getChildren().add(deleteButton);
//            		StackPane.setAlignment(deleteButton, Pos.CENTER_RIGHT);
//            		deleteButton.setCursor(Cursor.HAND);
//            		
//            		deleteButton.setOnAction(e -> {
//            			searchBar.getChildren().remove(highlightedText);
//            			searchBar.getChildren().remove(deleteButton);
//                        textField.setEditable(true);
//                        try {
//							this.displayGroups(allUserGroups);
//						} catch (Exception e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//            		});
//
//
//                }
//            }
//                
//         });
//		
//		
//		
//		//-----------------------------------------------------------------------------------------------------
//
//		
//		
//		
//		
//		
//		return searchBar;
//    }
    
//    private static final String TEXT_FIELD_STYLE =
//            "-fx-background-radius: 20;" +
//            "-fx-background-color: white;" +
//            "-fx-border-color: #dbdbdb;" +
//            "-fx-border-radius: 20;" +
//            "-fx-effect: dropshadow(three-pass-box, gray, 2, 0, 0, 0);";
//
//    private static final String HIGHLIGHTED_TEXT_STYLE =
//            "-fx-background-color: #E3E6E8; -fx-background-radius: 5; -fx-font-family: 'Product Sans'; " +
//            "-fx-font-size: 14; -fx-fill: #444444; -fx-alignment: baseline-left;";
//
//    private static final String DELETE_BUTTON_STYLE =
//            "-fx-background-color: transparent; -fx-font-family: 'Comfortaa'; -fx-font-size: 14; " +
//            "-fx-text-fill: #5b5b5b; -fx-font-weight: bold;";
//
//    private StackPane setupSearchBar() {
//        StackPane searchBar = new StackPane();
//        
//        TextField textField = new TextField();
//        textField.setMinSize(220, 30);
//        textField.setStyle(TEXT_FIELD_STYLE);
//        StackPane.setAlignment(textField, Pos.CENTER_LEFT);
//        textField.setPadding(new Insets(0, 5, 0, 35));
//
//        ImageView magnifyingGlassIcon = new ImageView(iconUtils.searchBarIcon);
//        magnifyingGlassIcon.setFitWidth(15);
//        magnifyingGlassIcon.setFitHeight(15);
//        StackPane.setAlignment(magnifyingGlassIcon, Pos.CENTER_LEFT);
//        magnifyingGlassIcon.setTranslateX(10);
//        
//        searchBar.getChildren().addAll(textField, magnifyingGlassIcon);
//
//        setTextFieldEventHandlers(textField, searchBar);
//        
//        return searchBar;
//    }
//    
//    private void setTextFieldEventHandlers(TextField textField, StackPane searchBar) {
//        textField.setOnKeyPressed(event -> {
//            if (event.getCode() == KeyCode.ENTER) {
//                handleEnterKey(textField, searchBar);
//            }
//        });
//    }
//
//    private void handleEnterKey(TextField textField, StackPane searchBar) {
//        String textEntered = textField.getText();
//
//        if (!textEntered.isEmpty()) {
//            textField.clear();
//            textField.setEditable(false);
//            sortButtons.getToggles().forEach(toggle -> ((ToggleButton) toggle).setSelected(false));
//
//            try {
//                this.search(textEntered);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            Button highlightedText = highlightText(searchBar, textEntered);
//            addDeleteButton(searchBar, highlightedText, textField);
//        }
//    }
//
//    private Button highlightText(StackPane searchBar, String textEntered) {
//        Button highlightedText = new Button();
//        highlightedText.setPadding(new Insets(0, 3, 0, 3));
//        highlightedText.setMaxHeight(8);
//
//        searchBar.getChildren().add(highlightedText);
//        StackPane.setAlignment(highlightedText, Pos.CENTER_LEFT);
//        highlightedText.setTranslateX(35);
//        highlightedText.setStyle(HIGHLIGHTED_TEXT_STYLE);
//
//        if (textEntered.length() > 13) {
//            highlightedText.setText(textEntered.substring(0, 10) + "...");
//        } else {
//            highlightedText.setText(textEntered);
//        }
//        
//        return highlightedText;
//    }
//
//    private void addDeleteButton(StackPane searchBar, Button highlightedText, TextField textField) {
//        Button deleteButton = new Button("✕");
//        deleteButton.setStyle(DELETE_BUTTON_STYLE);
//
//        searchBar.getChildren().add(deleteButton);
//        StackPane.setAlignment(deleteButton, Pos.CENTER_RIGHT);
//        deleteButton.setCursor(Cursor.HAND);
//
//        deleteButton.setOnAction(e -> {
//            searchBar.getChildren().remove(highlightedText);
//            searchBar.getChildren().remove(deleteButton);
//            textField.setEditable(true);
//
//            try {
//                this.displayGroups(allUserGroups);
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//        });
//    }
//    
//    
//    
//    
//    private void search(String text) throws Exception {
//    	List<Gruppo> groups = new ArrayList<>(allUserGroups);
//    	
//    	groups.removeIf(element -> !element.getNome().toLowerCase().contains(text.toLowerCase()));
//    	
//        this.displayGroups(groups);
//    }
//    
//    
//    
//    
//	private void displayGroups(List<Gruppo> groupList) throws Exception {
//		groupsTilePane.getChildren().clear();
//        for (Gruppo group : groupList) {
//            VBox groupVBox = createGroupVBox(group);
//            groupsTilePane.getChildren().add(groupVBox);
//        }
//        currentlyDisplayedGroups = groupList;
//	}
//    
//    private VBox createGroupVBox(Gruppo group) throws Exception {
//        VBox groupVBox = new VBox(5);
//        groupVBox.setPrefWidth(250);
//        groupVBox.setStyle(
//        		"-fx-background-color: #fefefe;" + 
//        		"-fx-background-radius: 5;" + 
//        		"-fx-padding: 20;" + 
//        		"-fx-effect: dropshadow(gaussian, gray, 5, 0, 0, 0);"
//        );
//        
//        HBox name = new HBox(5);
//        System.out.println("per gruppo " + group.getNome() + " l'owner è " + group.getOwner().getIdUtente());
//        System.out.println("e l'id del logged user è: " + userSession.getLoggedUser().getIdUtente());
//        if ( group.getOwner().getIdUtente().equals(userSession.getLoggedUser().getIdUtente()) ) {
//        	ImageView crown = new ImageView(iconUtils.crown);
//        	crown.setFitWidth(18);
//        	crown.setFitHeight(18);
//        	name.getChildren().add(crown);
//        }
//        
//        Text groupName = new Text(group.getNome());
//        groupName.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 16; -fx-fill: #0e5460; -fx-font-weight: bold;");
//		VBox.setMargin(groupName, new Insets(0, 0, -7, 0));
//		name.getChildren().add(groupName);
//
//        Text groupTopic = new Text("Tema: " + group.getTema().getTema());
//        groupTopic.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 14; -fx-fill: #e6ad38; -fx-font-weight: bold;");
//        
//        Text groupDescription = new Text(group.getDescrizione());
//        groupDescription.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 13; -fx-fill: black;");
//        groupDescription.setWrappingWidth(250);
//
//        groupVBox.getChildren().addAll(name, groupTopic, groupDescription);
//        return groupVBox;
//    }
//    
//   
//    
//
//    
//
//    
//
//    
//    private ToggleButton setupSortButton(ImageView icon) {
//    	String HIGHLIGHTED_STYLE = "-fx-background-color: #e1e1e1; -fx-background-radius: 10;";
//    	String UNHIGHLIGHTED_STYLE = "-fx-background-color: transparent; -fx-background-radius: 10;";
//    	
//		ToggleButton sortButton = new ToggleButton();
//		iconUtils.setIcon(sortButton, icon);
//		sortButton.setStyle(UNHIGHLIGHTED_STYLE);
//		
//		sortButton.setOnMouseEntered(e -> {sortButton.setStyle(HIGHLIGHTED_STYLE); });
//		
//		sortButton.setOnMouseExited(e -> {
//			if (!sortButton.isSelected()) {
//				sortButton.setStyle(UNHIGHLIGHTED_STYLE);
//			}
//		});
//
//		sortButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
//			if (newValue) {sortButton.setStyle(HIGHLIGHTED_STYLE);}
//			else {sortButton.setStyle(UNHIGHLIGHTED_STYLE);}
//		});
//
//		return sortButton;
//    }
}
