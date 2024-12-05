package Software.ulpgc.kata4.view.swing;

import Software.ulpgc.kata4.model.MovieRating;
import Software.ulpgc.kata4.model.RatingRepository;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InteractiveHistogramView {
    private JFrame frame;
    private JPanel buttonPanel;
    private ChartPanel chartPanel;

    public void renderHistogram(Map<Integer, Integer> histogram, File file){
        CategoryDataset dataset = createDataset(histogram);

        final JFreeChart chart = ChartFactory.createBarChart(
                "Histograma Interactivo de Notas",
                "Rango de Notas",
                "Frecuencia",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        chartPanel = new ChartPanel(chart);

        frame = new JFrame("Visualizacion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        buttonPanel = new JPanel();
        JButton reloadButton = new JButton("Actualizar datos");
        JButton exitButton = new JButton("Salir");

        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RatingRepository repository = new RatingRepository();
                List<MovieRating> ratings = repository.getAllRatings();
                HashMap<Integer, Integer> newHistogram = new HashMap<>();
                for(MovieRating rating : ratings){
                    int roundedRating = (int) Math.floor(rating.getAverageRating());
                    newHistogram.put(roundedRating, newHistogram.getOrDefault(roundedRating, 0) + 1);
                }

                CategoryDataset newDataset = createDataset(newHistogram);
                JFreeChart chart = ChartFactory.createBarChart(
                        "Histograma Interactivo de Notas",
                        "Rango de Notas",
                        "Frecuencia",
                        newDataset,
                        PlotOrientation.VERTICAL,
                        false,
                        true,
                        false
                );
                chartPanel.setChart(chart);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        buttonPanel.add(reloadButton);
        buttonPanel.add(exitButton);

        frame.add(chartPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    private CategoryDataset createDataset(Map<Integer, Integer> histogram) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(Map.Entry<Integer, Integer> entry : histogram.entrySet()){
            String range = entry.getKey() + " - " + (entry.getKey() + 1);
            dataset.addValue(entry.getValue(), "Frecuencia", range);
        }
        return dataset;
    }
}
