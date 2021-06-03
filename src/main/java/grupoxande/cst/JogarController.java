/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoxande.cst;

import static grupoxande.cst.App.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Pedrão Barros
 */
public class JogarController implements Initializable {

       @FXML
    private AnchorPane telaJogar;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i < tamanhoTabul; i++) {
            for (int j = 0; j < tamanhoTabul; j++) {
                tabuleiro[i][j] = new Rectangle(i*50, j*50, 50, 50);
                
                if((i%2==0 && j%2==0) || (i%2==1 && j%2==1)){
                    tabuleiro[i][j].setStyle("-fx-fill : white;");
                }else{
                    tabuleiro[i][j].setStyle("-fx-fill : green;");
                }
                    
                telaJogar.getChildren().add(tabuleiro[i][j]);
                
            }
        }
        inicialiarTabulImagens();
        Image img = null;
           try {
              
            img = new Image(new FileInputStream("C:\\Users\\Pedrão Barros\\Documents\\NetBeansProjects\\CST\\src\\main\\resources\\grupoxande\\cst\\imagem\\leaonojogo.png"));
           } catch (FileNotFoundException ex) {
               ex.printStackTrace();
           }
        imagens[0][0].setImage(img);
        imagens[1][4].setImage(img);
        
    }
void inicialiarTabulImagens(){
    for (int i = 0; i < tamanhoTabul; i++) {
        for (int j = 0; j < tamanhoTabul; j++) {
            imagens[i][j] = new ImageView();
            imagens[i][j].setFitHeight(50);
            imagens[i][j].setFitWidth(50);
            imagens[i][j].setLayoutX(0);
            imagens[i][j].setLayoutY(0);
            imagens[i][j].setX(j*50);
            imagens[i][j].setY(i*50);
            telaJogar.getChildren().add(imagens[i][j]);
        }
    }
}    
    
}