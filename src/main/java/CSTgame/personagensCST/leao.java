/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTgame.personagensCST;

import CSTgame.CSTpeca;
import CSTgame.time;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import tabuleiroGame.posicao;
import tabuleiroGame.tabuleiro;

/**
 *
 * @author Pedrão Barros
 */
public class leao extends CSTpeca{
    private boolean travaratq; 
    private List<CSTpeca> aliados = new ArrayList<>();
    private time timinho;
    public boolean isTravaratq() {
        return travaratq;
    }



    public void setTravaratq(boolean travaratq) {
        this.travaratq = travaratq;
    }



    public boolean isHabAtivado() {
        return habAtivado;
    }



    public void setHabAtivado(boolean habAtivado) {
        this.habAtivado = habAtivado;
    }



    private int contSurtez;
    private boolean habAtivado;
    private Image visual;

    public Image getVisual() throws FileNotFoundException {
        if(modoSurtado()){
            if(timinho == time.ORACULO){
                visual = new Image(new FileInputStream("C:\\Users\\Pedrão Barros\\Documents\\NetBeansProjects\\CST\\src\\main\\resources\\grupoxande\\cst\\imagem\\leaoOraculoSurtado.png"));
            }else{
                visual = new Image(new FileInputStream("C:\\Users\\Pedrão Barros\\Documents\\NetBeansProjects\\CST\\src\\main\\resources\\grupoxande\\cst\\imagem\\leaoTropaSurtado.png"));
            }
        }else{
            if(timinho == time.ORACULO){
                visual = new Image(new FileInputStream("C:\\Users\\Pedrão Barros\\Documents\\NetBeansProjects\\CST\\src\\main\\resources\\grupoxande\\cst\\imagem\\leaoOraculo.png"));
            }else{
                visual = new Image(new FileInputStream("C:\\Users\\Pedrão Barros\\Documents\\NetBeansProjects\\CST\\src\\main\\resources\\grupoxande\\cst\\imagem\\leaoTropa.png"));
            }
            
        }
        
        return visual;
    }
   public int getContSurtez() {
        return contSurtez;
    }



    public void setContSurtez(int contSurtez) {
        this.contSurtez = contSurtez;
    }




    public leao(tabuleiro tabul, time timinho, int ataque, int defesa, int vida, int rangeMovimento, String nome) {
        super(tabul, timinho, ataque, defesa, vida, rangeMovimento, nome);
        this.timinho = timinho;
        setTravaratq(true);
        setHabAtivado(false);
        setTravaMov(false);
    }

    

    @Override
    public String toString(){
        if(modoSurtado()){
            return "S";
        }
        return "L";
    }


    private boolean modoSurtado(){
        setContSurtez(getContTomarAtq());
        if(getContSurtez() == 3 && isTravaratq() == true){
            setAtaque(getAtaque() + 100);
            setTravaratq(false);
            return true;
        }else if(getContSurtez() > 3 || isTravaratq() == false){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public boolean[][] possiveisMovimentos() {
        boolean[][] matAux = new boolean[getTabul().getLinha()][getTabul().getColuna()];
        int contMovimento = 1;
        posicao posTeste = new posicao(0, 0);
        //acima
        
        posTeste.setCoordenada(posicao.getLinha() - 1, posicao.getColuna());
        while(getTabul().posicaoExiste(posTeste) && !getTabul().istoEhUmaPeca(posTeste) && contMovimento <= getRangeMovimento() ){
            matAux[posTeste.getLinha()][posTeste.getColuna()] = true;
            posTeste.setCoordenada(posTeste.getLinha() - 1, posTeste.getColuna());
            contMovimento++;
        }
        //abaixo
        contMovimento = 1;
        posTeste.setCoordenada(posicao.getLinha() + 1, posicao.getColuna());
        while(getTabul().posicaoExiste(posTeste) && !getTabul().istoEhUmaPeca(posTeste) && contMovimento <= getRangeMovimento()){
            matAux[posTeste.getLinha()][posTeste.getColuna()] = true;
            posTeste.setCoordenada(posTeste.getLinha() + 1, posTeste.getColuna());
            contMovimento++;
        }
        //esquerda
          contMovimento = 1;
          posTeste.setCoordenada(posicao.getLinha() , posicao.getColuna() - 1);
          while(getTabul().posicaoExiste(posTeste) && !getTabul().istoEhUmaPeca(posTeste) && contMovimento <= getRangeMovimento() ){
              matAux[posTeste.getLinha()][posTeste.getColuna()] = true;
              posTeste.setCoordenada(posTeste.getLinha(), posTeste.getColuna() - 1);
              contMovimento++;
          }
            //direita
        contMovimento = 1;
        posTeste.setCoordenada(posicao.getLinha() , posicao.getColuna() + 1);
        while(getTabul().posicaoExiste(posTeste) && !getTabul().istoEhUmaPeca(posTeste) && contMovimento <= getRangeMovimento() ){
            matAux[posTeste.getLinha()][posTeste.getColuna()] = true;
            posTeste.setCoordenada(posTeste.getLinha() , posTeste.getColuna() + 1);
            contMovimento++;
        }



        return matAux;
    }



    @Override
    public boolean[][] possiveisAtaques() {
        boolean[][] matAux = new boolean[getTabul().getLinha()][getTabul().getColuna()];
        posicao posTeste = new posicao(0, 0);
        //acima
        posTeste.setCoordenada(getPosicao().getLinha() - 1, getPosicao().getColuna());
        if(getTabul().posicaoExiste(posTeste) && haUmaPecaDoOponente(posTeste) && getTabul().istoEhUmaPeca(posTeste)){
            matAux[posTeste.getLinha()][posTeste.getColuna()] = true;
        }
        //baixo
        posTeste.setCoordenada(getPosicao().getLinha() + 1, getPosicao().getColuna());
        if(getTabul().posicaoExiste(posTeste) && haUmaPecaDoOponente(posTeste) && getTabul().istoEhUmaPeca(posTeste)){
            matAux[posTeste.getLinha()][posTeste.getColuna()] = true;
        }
        //esquerda
        posTeste.setCoordenada(getPosicao().getLinha(), getPosicao().getColuna() - 1);
        if(getTabul().posicaoExiste(posTeste) && haUmaPecaDoOponente(posTeste) && getTabul().istoEhUmaPeca(posTeste)){
            matAux[posTeste.getLinha()][posTeste.getColuna()] = true;
        }
        //direita
        posTeste.setCoordenada(getPosicao().getLinha() , getPosicao().getColuna() + 1);
        if(getTabul().posicaoExiste(posTeste) && haUmaPecaDoOponente(posTeste)&& getTabul().istoEhUmaPeca(posTeste)){
            matAux[posTeste.getLinha()][posTeste.getColuna()] = true;
        }

        return matAux;
    }



    @Override
    public void habilidade(CSTpeca generico) {
        CSTpeca auxaliado;
        if(haUmaPecaAliada(generico.getPosicao())){
            if(isHabAtivado() == false){
                generico.setDefesa(generico.getDefesa() + 5);
                aliados.add(generico);
                setHabAtivado(true);
            
          }
            if(isHabAtivado() == true){
                auxaliado = aliados.get(0);
                auxaliado.setDefesa(auxaliado.getDefesa() - 5);
                generico.setDefesa(generico.getDefesa() + 5);
                aliados.remove(0);
                aliados.add(generico);
                setHabAtivado(true);
          }
    }
        
    }


    
}
