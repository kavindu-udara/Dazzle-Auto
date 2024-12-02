package views.components.charts.com.raven.chart;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import views.components.charts.com.raven.charts.blankchart.BlankPlotChart;
import views.components.charts.com.raven.charts.blankchart.BlankPlotChatRender;
import views.components.charts.com.raven.charts.blankchart.SeriesSize;

public class Chart extends javax.swing.JPanel {

    public List<ModelLegend> legends = new ArrayList<>();
    public List<ModelChart> model = new ArrayList<>();
    private final int seriesSize = 25;
    private final int seriesSpace = 6;

    public Chart() {
        initComponents();
        blankPlotChart1.setBlankPlotChatRender(new BlankPlotChatRender() {
            @Override
            public String getLabelText(int index) {
                return model.get(index).getLabel();
            }

            @Override
            public void renderSeries(BlankPlotChart chart, Graphics2D g2, SeriesSize size, int index) {
                double totalSeriesWidth = (seriesSize * legends.size()) + (seriesSpace * (legends.size() - 1));
                double x = (size.getWidth() - totalSeriesWidth) / 2;
                for (int i = 0; i < legends.size(); i++) {
                    ModelLegend legend = legends.get(i);
                    g2.setColor(legend.getColor());
                    double seriesValues = chart.getSeriesValuesOf(model.get(index).getValues()[i], size.getHeight());
                    g2.fillRect((int) (size.getX() + x), (int) (size.getY() + size.getHeight() - seriesValues), seriesSize, (int) seriesValues);
                    x += seriesSpace + seriesSize;
                }
            }
        });
    }

    public void addLegend(String name, Color color) {
        ModelLegend data = new ModelLegend(name, color);
        legends.clear();
        legends.add(data);
        jPanel1.removeAll();
        jPanel1.repaint();
        jPanel1.revalidate();
        jPanel1.add(new LegendItem(data));
        jPanel1.repaint();
        jPanel1.revalidate();
    }

    public void addData(ModelChart data) {
        model.add(data);
        blankPlotChart1.setLabelCount(model.size());
        double max = data.getMaxValues();
        if (max > blankPlotChart1.getMaxValues()) {
            blankPlotChart1.setMaxValues(max);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        blankPlotChart1 = new views.components.charts.com.raven.charts.blankchart.BlankPlotChart();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(477, 353));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
            .addComponent(blankPlotChart1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(blankPlotChart1, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private views.components.charts.com.raven.charts.blankchart.BlankPlotChart blankPlotChart1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
