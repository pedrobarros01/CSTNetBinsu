/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTgame;

import java.io.FileNotFoundException;
import tabuleiroGame.peca;
import tabuleiroGame.posicao;
import tabuleiroGame.tabuleiro;

/**
 *
 * @author Pedrão Barros
 */
public abstract class CSTpeca extends peca{
    private time timinho;
    private int ataque;
    private int vida;
    private int defesa;
    private int rangeMovimento;
    private int contTomarAtq;
    private boolean travaMov;
    private String nome;
    private itemEquipavel inventario;
    public itemEquipavel getInventario() {
        return inventario;
    }
    public void setInventario(itemEquipavel inventario) {
        this.inventario = inventario;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
     public boolean isTravaMov() {
        return travaMov;
    }
    public void setTravaMov(boolean travaMov) {
        this.travaMov = travaMov;
    }

    public int getContTomarAtq() {
        return contTomarAtq;
    }
    public void setContTomarAtq(int contTomarAtq) {
        this.contTomarAtq = contTomarAtq;
    }
    public void contarAtqTomado(){
        setContTomarAtq(getContTomarAtq() + 1);
    }
    public void diminuirAtqTomado(){
        setContTomarAtq(getContTomarAtq() - 1);
    }
    public int getRangeMovimento() {
        return rangeMovimento;
    }
    protected void setRangeMovimento(int rangeMovimento) {
        this.rangeMovimento = rangeMovimento;
    }
    public int getAtaque() {
        return ataque;
    }
    protected void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    public int getVida() {
        return vida;
    }
    public void setVida(int vida) {
        this.vida = vida;
    }
    public int getDefesa() {
        return defesa;
    }
    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }
    public CSTpeca(tabuleiro tabul, time timinho, int ataque, int defesa, int vida, int rangeMovimento, String nome) {
        super(tabul);
        this.timinho = timinho;
        this.ataque = ataque;
        this.defesa = defesa;
        this.vida = vida;
        this.rangeMovimento =rangeMovimento;
        this.nome = nome;
        
    }
    public time getTiminho() {
        return timinho;
    }
    public boolean haUmaPecaDoOponente(posicao posicao){
        CSTpeca peca = (CSTpeca) getTabul().peca(posicao);
        return peca != null && peca.getTiminho() != getTiminho();
    }
    public boolean haUmaPecaAliada(posicao posicao){
        CSTpeca peca = (CSTpeca) getTabul().peca(posicao);
        return peca != null && peca.getTiminho() == getTiminho();
    }
    public void equiparItem(itemEquipavel item, CSTpeca generico){
        if(getInventario() == null ){
            setInventario(item);
            item.efeito(generico);
            System.out.println(item.getID());

        }else{
            throw new exececaoCST("Inventario do personagem cheio");
        }
            
    }

    public CSTpeca(tabuleiro tabul) {
        super(tabul);  
    }
    public abstract void habilidade(CSTpeca generico) throws FileNotFoundException;
    

}
