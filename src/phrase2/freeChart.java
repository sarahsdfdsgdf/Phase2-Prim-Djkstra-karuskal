/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package phrase2;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;



public class freeChart extends JFrame{
    //intialize the vaiable
    private String firstAlgo;
    private String secondAlgo;
    
    public freeChart(String f,String s) {
        this.firstAlgo = f;
        this.secondAlgo = s;
    }
    public void initChart(long firstTime[],long secondTime[],int numberOfCase) {

        //store pixels X,Y
        XYDataset dataset;
       
        //looping casses for the first algorithme 
        XYSeries series2,series1 = new XYSeries(this.firstAlgo);
        for(int i=1;i<=numberOfCase;i++){
            series1.add(i, firstTime[i]);
        }
        
        //looping casses for the second algorithme 
        series2 = new XYSeries(this.secondAlgo);
        for(int i=1;i<=numberOfCase;i++){
            series2.add(i, secondTime[i]);
        }

        XYSeriesCollection dataset1 = new XYSeriesCollection();
        dataset1.addSeries(series1);
        dataset1.addSeries(series2);

        dataset = dataset1;
        //information for chart
        JFreeChart chart1 = ChartFactory.createXYLineChart(
        "Running Time",
        "Cases",
        "Time",
        dataset,
        PlotOrientation.VERTICAL,
        true,
        true,
        false
        );
        
        XYPlot plot = chart1.getXYPlot();
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        //control in chart and lines style
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);

        chart1.getLegend().setFrame(BlockBorder.NONE);
        //Title for Chartt
        chart1.setTitle(new TextTitle("Running Time Algorithem",
                        new Font("Serif", Font.BOLD, 18)
                )
        );
        
        JFreeChart chart = chart1;
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);
        pack();
        setTitle("Running Time Chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setVisible(true);
    }

}
