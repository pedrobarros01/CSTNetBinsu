/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTgame;

import CSTgame.interfacesItems.gachaRollIF;
import javafx.scene.control.Alert;

/**
 *
 * @author Pedrão Barros
 */
public class gacha extends itemEquipavel implements gachaRollIF {

    public gacha(String nomeItem, CSTgame.partidaCST partidaCST, int ID) {
        super(nomeItem, partidaCST, ID);
    }

    @Override
    public void efeitoFoice(CSTpeca generico) {
        generico.setAtaque(generico.getAtaque()+5);
        generico.setRangeMovimento(5);
    }

    @Override
    public void efeitoAk(CSTpeca generico) {
        generico.setAtaque(generico.getAtaque()-5);
        generico.setRangeMovimento(4);
        
    }

    @Override
    public void efeitoMartelo(CSTpeca generico) {
        generico.setAtaque(generico.getAtaque()+20);
        generico.setRangeMovimento(2);
    }

    @Override
    public void efeitoRoupaGrife(CSTpeca generico) {
        generico.setAtaque(generico.getAtaque()-10);
        generico.setDefesa(generico.getDefesa()+25);
        generico.setVida(generico.getVida()+(generico.getVida()/5));
    }
    public void desequiparGacha(itemEquipavel item, CSTpeca generico){
            String nome = item.getNomeItem();
            if(nome.equals("Foice")){
                generico.setAtaque(generico.getAtaque()-5);
            }
            if(nome.equals("AK Trovoada")){
                generico.setAtaque(generico.getAtaque()+5);
            }
            if(nome.equals("Martelo")){
                generico.setAtaque(generico.getAtaque()-20);
            }
            if(nome.equals("Bodychain da Gucci")){
                generico.setAtaque(generico.getAtaque()+10);
                generico.setDefesa(generico.getDefesa()-25);
            }
           // if(nome.equals("Lucky Wheel")){
                //System.out.println("Ativando roleta do gacha! Boa sorte!");
           // }
            generico.setRangeMovimento(3);
    }
    
    public void atributos(CSTpeca generico, int id){
        desequiparGacha(generico.getInventario(), generico);
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle( "Ativando roleta do gacha! Boa sorte!");
        info.setHeaderText("Rodando o gacha... " +generico.getNome() +" recebeu: ");
       
        switch(id){
            case 1:
                this.setNomeItem("Foice");
                generico.setInventario(this);
                efeitoFoice(generico);
                info.setContentText("Racofoice!! Preparado para roubar almas!!");
                
                break;
            case 2:
                this.setNomeItem("AK Trovoada");
                generico.setInventario(this);
                efeitoAk(generico);
                 info.setContentText("AK 47 trovoada!! Ataque a distancia!");
               
                break;
            case 3:
                this.setNomeItem("Martelo");
                generico.setInventario(this);  
                efeitoMartelo(generico);
                 info.setContentText("MARTELOBALDO!");
               
                break;
            case 4:
                this.setNomeItem("Bodychain da Gucci");
                generico.setInventario(this); 
                efeitoRoupaGrife(generico);
                info.setContentText("Bodychain da Gucci! ta podendo filho!");
                break;
            default:
                break;                    
        }
        info.showAndWait();
    }
}
