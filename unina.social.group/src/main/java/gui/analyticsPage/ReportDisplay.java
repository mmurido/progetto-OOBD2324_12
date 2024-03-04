package gui.analyticsPage;

import java.time.YearMonth;
import java.util.Map;
import controllers.AnalyticsPageController;
import gui.PostCard;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ReportDisplay extends ScrollPane {
	
	private AnalyticsPageController controller;
	public VBox vbox;
	public LineChart<Number, Number> lineChart;
	public NumberAxis xAxis;
	public NumberAxis yAxis;
	public XYChart.Series<Number, Number> series;
	public TilePane tilePane;
	public VBox mostLikedPostTile;
	public VBox leastLikedPostTile;
	public VBox mostCommentedPostTile;
	public VBox leastCommentedPostTile;
	public HBox hbox;
	public Text average;
	
	public ReportDisplay(AnalyticsPageController controller) {
		this.controller = controller;
		initializeComponents();
		layoutComponents();
	}
	
	private void initializeComponents() {
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		String cssPath = ReportDisplay.class.getResource("/css/scrollPaneStyle.css").toExternalForm();
		this.getStylesheets().add(cssPath);
		this.setStyle("-fx-background: #f9f9f9;");
		
		setupVBox();
		setupHBox();
		setupLineChart();
		setupTilePane();
		
		this.setVisible(false);
	}
	
	private void layoutComponents() {
		this.setContent(vbox);
		vbox.getChildren().addAll(lineChart, hbox, tilePane);
	}
	
	private void setupVBox() {
		vbox = new VBox(50);
		vbox.prefWidthProperty().bind(this.widthProperty());
		vbox.setPadding(new Insets(60, 65, 60, 65));
	}
	
	private void setupHBox() {
		hbox = new HBox(5);
		hbox.setAlignment(Pos.CENTER);

		Text text = new Text("Numero medio di contenuti postati:");
		text.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 16; -fx-fill: #0e5460; -fx-font-weight: bold;");

		average = new Text();
		average.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 16; -fx-fill: black;");
		
		hbox.getChildren().addAll(text, average);
	}
	
	private void setupTilePane() {
		tilePane = new TilePane();
		tilePane.setAlignment(Pos.CENTER);
		tilePane.setOrientation(Orientation.HORIZONTAL);
		tilePane.setHgap(50);
		tilePane.setVgap(50);
		tilePane.setPrefColumns(2);
	}

	public void displayReport() {
		populateChart();
		tilePane.getChildren().clear();
		addMostLikedPostTile();
		addLeastLikedPostTile();
		addMostCommentedPostTile();
		addLeastCommentedPostTile();
		addDailyNewPostsAverage();
	}

	private void setupLineChart() {
		xAxis = new NumberAxis(1, 0, 1);
		xAxis.setLabel("Giorni del mese");
		xAxis.setMinorTickVisible(false);

		yAxis = new NumberAxis(0, 50, 5);
		yAxis.setLabel("Numero di post");

		lineChart = new LineChart<>(xAxis, yAxis);
		lineChart.setTitle("Andamento mensile dei post");
		lineChart.setPadding(new Insets(0, 125, -50, 80));
		lineChart.setPrefHeight(245);
		lineChart.setLegendVisible(false);
		lineChart.getStylesheets().add(
				ReportDisplay.class.getResource("/css/lineChartStyle.css").toExternalForm());

		series = new XYChart.Series<>();

		lineChart.getData().add(series);
	}

	private void populateChart() {
		YearMonth yearMonth = YearMonth.of(controller.selectedYear, controller.selectedMonth);
		int numberOfDaysInMonth = yearMonth.lengthOfMonth();

        xAxis.setUpperBound(numberOfDaysInMonth);		

		Map<Integer, Integer> postsPerDay = controller.getPostsCountPerDay();

		int lastDayOfMonth = YearMonth.of(controller.selectedYear, controller.selectedMonth).lengthOfMonth();
		
		series.getData().clear();
		
		for (int day = 1; day <= lastDayOfMonth; day++) {
			int postsCount = postsPerDay.getOrDefault(day, 0);
			series.getData().add(new XYChart.Data<>(day, postsCount));
		}
	}

	private void addDailyNewPostsAverage() {
		int newPostsAverage = (int) controller.calculateAveragePosts();
		if (newPostsAverage == 1) {
			average.setText(newPostsAverage + " nuovo post al giorno");
		} else {
			average.setText(newPostsAverage + " nuovi post al giorno");
		}
	}

	private void addMostLikedPostTile() {
		PostCard mostLikedPostCard = null;
		
		controller.getMostLikedPostOfGroupInMonthYear();
		
		if (controller.mostLikedPost != null) {
			mostLikedPostCard = new PostCard(controller.mostLikedPost);
			mostLikedPostCard.displayLikeCount(controller.highestLikeCount);
		}
		
		mostLikedPostTile = createReportTile("Post con più like:", mostLikedPostCard);

		tilePane.getChildren().add(mostLikedPostTile);
	}
	
	private void addLeastLikedPostTile() {
		PostCard leastLikedPostCard = null;
		
		controller.getLeastLikedPostOfGroupInMonthYear();
		
		if (controller.leastLikedPost != null) {
			leastLikedPostCard = new PostCard(controller.leastLikedPost);
			leastLikedPostCard.displayLikeCount(controller.lowestLikeCount);
		}

		leastLikedPostTile = createReportTile("Post con meno like:", leastLikedPostCard);
		
		tilePane.getChildren().add(leastLikedPostTile);
	}

	private void addMostCommentedPostTile() {
		PostCard mostCommentedPostCard = null;
		
		controller.getMostCommentedPostOfGroupInMonthYear();	
		
		if (controller.mostCommentedPost != null) {
			mostCommentedPostCard = new PostCard(controller.mostCommentedPost);
			mostCommentedPostCard.displayCommentsCount(controller.highestCommentsCount);
		}

		mostCommentedPostTile = createReportTile("Post con più commenti:", mostCommentedPostCard);
		
		tilePane.getChildren().add(mostCommentedPostTile);
	}
	
	private void addLeastCommentedPostTile() {
		PostCard leastCommentedPostCard = null;
		
		controller.getLeastCommentedPostOfGroupInMonthYear();	
		
		if (controller.leastCommentedPost != null) {
			leastCommentedPostCard = new PostCard(controller.leastCommentedPost);
			leastCommentedPostCard.displayCommentsCount(controller.lowestCommentsCount);
		}
			
		leastCommentedPostTile = createReportTile("Post con meno commenti:", leastCommentedPostCard);
		
		tilePane.getChildren().add(leastCommentedPostTile);
	}

	private VBox createReportTile(String description, PostCard postCard) {
		VBox reportTile = new VBox(10);
		reportTile.setPadding(new Insets(15, 10, 15, 10));
		reportTile.setPrefWidth(350);
		reportTile.setStyle(
				"-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, white , #f9f9ff);" +
				"-fx-background-radius: 5;" + "-fx-effect: dropshadow(gaussian, gray, 5, 0, 0, 0);"
		);

		Label reportDescription = new Label(description);
		reportTile.getChildren().add(reportDescription);
		reportDescription.setStyle(
				"-fx-font-family: 'Product Sans'; -fx-font-size: 16;" + 
			    "-fx-text-fill: #0e5460; -fx-font-weight: bold;"
		);
		
		if (postCard != null) {
			reportTile.getChildren().add(postCard);
		}
		else {
			Label noPostLabel = new Label("Nessun post da mostrare!");
			noPostLabel.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 16; -fx-text-fill: #bcbcbc;");
			reportTile.getChildren().addAll(noPostLabel);
		}

		return reportTile;
	}

}
