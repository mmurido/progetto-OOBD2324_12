package gui.analyticsPage;

import java.time.YearMonth;
import java.util.Map;

import controllers.AnalyticsPageController;
import gui.commonComponents.PostCard;
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
	VBox vbox;
	LineChart<Number, Number> monthlyPostActivityChart;
	NumberAxis xAxis;
	NumberAxis yAxis;
	XYChart.Series<Number, Number> series;
	TilePane engagementSnapshotTilePane;
	VBox mostLikedPostTile;
	VBox leastLikedPostTile;
	VBox mostCommentedPostTile;
	VBox leastCommentedPostTile;
	HBox dailyAveragePostCountText;
	Text average;
	
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
		setupDailyAveragePostCountText();
		setupMonthlyPostActivityChart();
		setupEngagementSnapshotTilePane();
		
		this.setVisible(false);
	}
	
	private void layoutComponents() {
		this.setContent(vbox);
		vbox.getChildren().addAll(monthlyPostActivityChart, dailyAveragePostCountText, engagementSnapshotTilePane);
	}
	
	private void setupVBox() {
		vbox = new VBox(50);
		vbox.prefWidthProperty().bind(this.widthProperty());
		vbox.setPadding(new Insets(60, 65, 60, 65));
	}
	
	private void setupDailyAveragePostCountText() {
		Text text = new Text("Numero medio di contenuti postati:");
		text.setStyle(
				"-fx-font-family: 'Product Sans'; -fx-font-size: 16;" + 
				"-fx-fill: #0e5460; -fx-font-weight: bold;"
		);

		average = new Text();
		average.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 16; -fx-fill: black;");
		
		dailyAveragePostCountText = new HBox(5, text, average);
		dailyAveragePostCountText.setAlignment(Pos.CENTER);
	}
	
	private void setupMonthlyPostActivityChart() {
		xAxis = new NumberAxis(1, 0, 1);
		xAxis.setLabel("Giorni del mese");
		xAxis.setMinorTickVisible(false);

		yAxis = new NumberAxis(0, 50, 5);
		yAxis.setLabel("Numero di post");

		monthlyPostActivityChart = new LineChart<>(xAxis, yAxis);
		monthlyPostActivityChart.setTitle("Andamento mensile dei post");
		monthlyPostActivityChart.setPadding(new Insets(0, 125, -50, 80));
		monthlyPostActivityChart.setPrefHeight(245);
		monthlyPostActivityChart.setLegendVisible(false);
		monthlyPostActivityChart.getStylesheets().add(
				ReportDisplay.class.getResource("/css/lineChartStyle.css").toExternalForm());

		series = new XYChart.Series<>();

		monthlyPostActivityChart.getData().add(series);
	}
	
	private void setupEngagementSnapshotTilePane() {
		engagementSnapshotTilePane = new TilePane();
		engagementSnapshotTilePane.setAlignment(Pos.CENTER);
		engagementSnapshotTilePane.setOrientation(Orientation.HORIZONTAL);
		engagementSnapshotTilePane.setHgap(50);
		engagementSnapshotTilePane.setVgap(50);
		engagementSnapshotTilePane.setPrefColumns(2);
	}

	public void displayReport() {
		populateChart();
		setDailyAveragePostCount();
		populateEngagementSnapshotTilePane();
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

	private void setDailyAveragePostCount() {
		int newPostsAverage = (int) controller.calculateAveragePosts();
		if (newPostsAverage == 1) {
			average.setText(newPostsAverage + " nuovo post al giorno");
		} else {
			average.setText(newPostsAverage + " nuovi post al giorno");
		}
	}
	
	private void populateEngagementSnapshotTilePane() {
		engagementSnapshotTilePane.getChildren().clear();
		addMostLikedPostTile();
		addLeastLikedPostTile();
		addMostCommentedPostTile();
		addLeastCommentedPostTile();
	}

	private void addMostLikedPostTile() {
		PostCard mostLikedPostCard = controller.getMostLikedPostOfGroupInMonthYear();
		mostLikedPostTile = createReportTile("Post con più like:", mostLikedPostCard);
		engagementSnapshotTilePane.getChildren().add(mostLikedPostTile);
	}
	
	private void addLeastLikedPostTile() {
		PostCard leastLikedPostCard = controller.getLeastLikedPostOfGroupInMonthYear();
		leastLikedPostTile = createReportTile("Post con meno like:", leastLikedPostCard);
		engagementSnapshotTilePane.getChildren().add(leastLikedPostTile);
	}

	private void addMostCommentedPostTile() {
		PostCard mostCommentedPostCard = controller.getMostCommentedPostOfGroupInMonthYear();	
		mostCommentedPostTile = createReportTile("Post con più commenti:", mostCommentedPostCard);
		engagementSnapshotTilePane.getChildren().add(mostCommentedPostTile);
	}
	
	private void addLeastCommentedPostTile() {
		PostCard leastCommentedPostCard = controller.getLeastCommentedPostOfGroupInMonthYear();	
		leastCommentedPostTile = createReportTile("Post con meno commenti:", leastCommentedPostCard);
		engagementSnapshotTilePane.getChildren().add(leastCommentedPostTile);
	}

	private VBox createReportTile(String description, PostCard postCard) {
		VBox reportTile = new VBox(10);
		reportTile.setPadding(new Insets(15, 15, 15, 15));
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
