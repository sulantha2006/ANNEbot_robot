/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package networkstructure;

import ANN.ANN;
import ANN.Neuron;
import Utility.DataLogger;
import Utility.Matrix;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import javax.swing.JFrame;
import org.apache.commons.collections15.Transformer;

/**
 *
 * @author sulantha
 */
public class Main {
    static String dataFolder = System.getProperty("user.home")+"\\ANNEbot_Devel\\annebot\\Data\\";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ANN ann = null;
        
        //ann = DataLogger.readANNObjectFromFile(dataFolder+"bestANN_best.dat");
        //ann = DataLogger.readANNObjectFromFile(dataFolder+"bestANNBC2.dat"); 
        ann = DataLogger.readANNObjectFromFile(dataFolder+"bestANN.dat");
        
        Neuron [] neurons = ann.getNeurons();
        final int noInputNeurons = ann.getInputNeuronCount();
        final int noOutputNeurons = ann.getOutputNeuronCount();
        final int noHiddenNeurons = ann.getHiddenLNeuronCount();
        final int totalNeurons = ann.getTotalNeuronCount();
        final int maxGraphSize_h = 600;
        final int maxGraphSize_w = 900;
        MyNode[] nodeHolder = new MyNode[totalNeurons];
        Matrix weightMatrix = ann.getWeights();
        Graph g = new DirectedSparseMultigraph<MyNode, MyLink>();
        for (int i = 0; i < totalNeurons; i++) {
            nodeHolder[i] = new MyNode(i, Double.parseDouble(String.format("%.2g%n", neurons[i].getThreshold())));
        }
        int edgeId = 0;
        for (int i = 0; i < totalNeurons; i++) {
            for (int j = 0; j < totalNeurons; j++) {

                if (weightMatrix.get(i, j)== 0.0) {
                    continue;
                }else{
                    g.addEdge(new MyLink(weightMatrix.get(i, j),edgeId), nodeHolder[i], nodeHolder[j]);
                    edgeId++;
                }
            }
        }
        System.out.println("INPUT  - "+noInputNeurons);
        System.out.println("Hidden  - "+noHiddenNeurons);
        System.out.println("Out  - "+noOutputNeurons);
        System.out.println("Edge  - "+edgeId);

        int maxOfNeurons = Math.max(noInputNeurons, Math.max(noHiddenNeurons, noOutputNeurons));
        final int graphHeight = Math.max(maxOfNeurons*50,maxGraphSize_h);

        Transformer<MyNode, Point2D> locationTransformer = new Transformer<MyNode, Point2D>() {
            public Point2D transform(MyNode vertex) {
                if (vertex.getId() < noInputNeurons) {
                    int y_value = (vertex.getId()+1)*graphHeight/(noInputNeurons+1);
                    return new Point2D.Double((double)30,(double)y_value);
                }else if ((noInputNeurons<=vertex.getId())&&(vertex.getId() < noInputNeurons+noHiddenNeurons)) {
                    int y_value = ((vertex.getId()- noInputNeurons)+1)*graphHeight/(noHiddenNeurons+1);
                    return new Point2D.Double((double)435,(double)y_value);
                }else{
                    int y_value = ((vertex.getId()- (noInputNeurons+noHiddenNeurons))+1)*graphHeight/(noOutputNeurons+1);
                    return new Point2D.Double((double)870,(double)y_value);
                }
            }

        };
        Layout<Integer, String> layout = new StaticLayout(g,locationTransformer);
        layout.setSize(new Dimension(maxGraphSize_w,maxGraphSize_h));
        VisualizationViewer<Integer,String> vv = new VisualizationViewer<Integer,String>(layout);
        //BasicVisualizationServer<Integer,String> vv = new BasicVisualizationServer<Integer,String>(layout);
        vv.setPreferredSize(new Dimension(maxGraphSize_w+50,maxGraphSize_h+50));
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
        
        DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        try {
            vv.setGraphMouse(gm);
        } catch (Exception e) {
        }

        JFrame frame = new JFrame("Simple Graph View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);

        
    }

}
