package view.characters;

import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import utils.Orientation;
import utils.Position;
import utils.Updatable;

public class PacManView implements Updatable {
    
    // arco que representa o pacman
    private final Arc arc;
    
    private final double radiusX;
    private final double radiusY;
    private boolean opening;
    private boolean moving;
    
    // multiplicador de tamanho para o pacman ser maior que uma celula
    private static final double SIZE_MULTIPLIER = 1.2;

    public PacManView(double cellWidth, double cellHeight) {
        // inicia o arco
        arc = new Arc();
        arc.setType(ArcType.ROUND);   
        arc.setFill(Color.YELLOW);
        arc.setStartAngle(30);
        arc.setLength(300);
        
        this.radiusX = (cellWidth * SIZE_MULTIPLIER) / 2;
        this.radiusY = (cellHeight * SIZE_MULTIPLIER) / 2;
        moving = false;
    }
    
    // consider the position inside a map cell
    public void setCellPosition (Position position) {
        arc.setCenterX(position.getX() + radiusX * (1-(SIZE_MULTIPLIER-1)/2));
        arc.setCenterY(position.getY() + radiusY * (1-(SIZE_MULTIPLIER-1)/2));
    }

    //set image orientation
    public void setOrientation(Orientation orientation){
        switch(orientation){
            case UP:
                arc.setRotate(-90);
                arc.setRadiusX(radiusY);
                arc.setRadiusY(radiusX);
                break;

            case RIGHT:
                arc.setRotate(0);
                arc.setRadiusX(radiusX);
                arc.setRadiusY(radiusY);
                break;

            case DOWN:
                arc.setRotate(90);
                arc.setRadiusX(radiusY);
                arc.setRadiusY(radiusX);
                break;

            case LEFT:
                arc.setRotate(180);
                arc.setRadiusX(radiusX);
                arc.setRadiusY(radiusY);
                break;
        }
    }

    public Arc getArc() {
        return arc;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    // atualiza a abertura da boca do pacman
    @Override
    public void update() {
        if (moving) {
            // verifica se deve estar abrindo ou fechando
            if (arc.getStartAngle() <= 0)
                opening = true;
            else if (arc.getStartAngle() >= 40)
                opening = false;

            // realiza o movimento
            if (opening) {
                arc.setStartAngle(arc.getStartAngle()+2);
                arc.setLength(arc.getLength()-4);
            } else {
                arc.setStartAngle(arc.getStartAngle()-2);
                arc.setLength(arc.getLength()+4);
            }
       }
    }
}
