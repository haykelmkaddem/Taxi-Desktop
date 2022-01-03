/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.Service;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author anest
 */
public class Statistiques extends Application {
   private final ObservableList<PieChart.Data> graph=FXCollections.observableArrayList();
   private BorderPane root ;
   private PieChart pieChart;
    @Override
    public void start(Stage primaryStage) throws Exception {
     ServiceReservation RV=new ServiceReservation();

      graph.addAll(new PieChart.Data("Accepté", 20),
  new PieChart.Data("Refusé", 5),
     new PieChart.Data("Traité", 10),
     new PieChart.Data("Non traité", 15)
      
      );
      root=new BorderPane();
        Scene scene= new Scene(root,600,500);
      pieChart =new PieChart();
      pieChart.setData(graph);
      pieChart.setTitle("Test");
      pieChart.setLegendSide(Side.BOTTOM);
      pieChart.setLabelsVisible(true);
      root.setCenter(pieChart);
      primaryStage.setScene(scene);
      primaryStage.show();
    }
    
}
