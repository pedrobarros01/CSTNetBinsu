/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoxande.cst;

import CSTgame.CSTpeca;
import CSTgame.CSTposicao;
import CSTgame.exececaoCST;
import CSTgame.itemConsumivel;
import CSTgame.itemEquipavel;
import CSTgame.partidaCST;
import CSTgame.time;
import static grupoxande.cst.App.imagens;
import static grupoxande.cst.App.tabuleiro;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javafx.scene.control.Alert;

/**
 *
 * @author Pedrão Barros
 */
public class UI {
    
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void limparTelaConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static String[] lerNomes(Scanner scan){
        String[] nomes = new String[2];
        for (int i = 0; i < nomes.length; i++) {
            System.out.print("Digite o seu nome jogador #" + (i+1) + ":");
            nomes[i] = scan.next();
        }
        return nomes;
    }
    public static void printarVencedor(String nome){
        limparTelaConsole();
        System.out.println("==================================================================");
        System.out.println("\tO VENCENDOR EH: "+ nome+" PARABENS VERMAO");
        System.out.println("==================================================================");
        
    }
    public static String printarPartida(partidaCST partidaCST, String[] nomes){
        StringBuilder string = new StringBuilder();
       
        
   

        
              
        //printarTabuleiro(partidaCST.getPecas(), numeroLinhas);
        //System.out.println();
        
        string.append("Turno: " + partidaCST.getTurno() + "\n");
        


        

        if(partidaCST.getJogador().getTimeAtual() == time.ORACULO){
            partidaCST.getJogador().setNome(nomes[0]);
            string.append("Esperando " + partidaCST.getJogador().getNome() + " jogar" + "\n");
            string.append("Peça a ser jogada: " + partidaCST.getJogador().getPecaAtual().getNome() + " " +partidaCST.getJogador().getPecaAtual().toString() + "\n");
        }else{
            partidaCST.getJogador().setNome(nomes[1]);
            string.append("Esperando " + partidaCST.getJogador().getNome() + " jogar" + "\n");
            string.append("Peça a ser jogada: " + partidaCST.getJogador().getPecaAtual().getNome() + " " +partidaCST.getJogador().getPecaAtual().toString() + "\n");
        }
       // String status = printarStatus(partidaCST.getJogador().getPecaAtual(), numeroLinhas);
        //System.out.println(status);
        return string.toString();
    }
    protected static void imprimirLIsta(List<itemConsumivel> qualquer, partidaCST partidaCST){
        Alert mostrar = new Alert(Alert.AlertType.INFORMATION);
        StringBuilder mostrarItem = new StringBuilder();
        mostrar.setTitle("ITEMS");
        int cont = 1;
        if(qualquer.size() > 0){
            for (itemConsumivel itemConsumivel : qualquer) {
               mostrarItem.append(cont + " - " + itemConsumivel.getNome() + "\n");
                cont++;
            }
            mostrar.setContentText(mostrarItem.toString());
            mostrar.show();
        }else{
            throw new exececaoCST("lista de itens vazia");
        }

    }
    protected static void imprimirLista(List<itemEquipavel> qualquer, partidaCST partidaCST){
        Alert mostrar = new Alert(Alert.AlertType.INFORMATION);
        StringBuilder mostrarItem = new StringBuilder();
        mostrar.setTitle("ITEMS");
        mostrar.setHeaderText("Lista de Items Equipaveis");
        int cont = 1;
        if(qualquer.size() > 0){
            for (itemEquipavel itemEquipavel : qualquer) {
                mostrarItem.append(cont + " - " + itemEquipavel.getNomeItem() + "\n");
                cont++;
            }
            mostrar.setContentText(mostrarItem.toString());
            mostrar.show();
        }else{
            throw new exececaoCST("lista de itens vazia");
        }
    }
    public static void menuItem(Scanner scan, partidaCST partidaCST, String posicao){
        int resp, ID = 0;
        System.out.println("Item");
        System.out.println("Qual tipo?");
        System.out.println("1 - Consumivel");
        System.out.println("2 - Equipavel");
        resp = scan.nextInt();
        
        if(resp == 1){
            
            if(partidaCST.getJogador().getTimeAtual() == time.ORACULO){
                imprimirLIsta(partidaCST.getItensConsumivelsO(), partidaCST);
                System.out.println("Escolha: ");
                ID = scan.nextInt();
                
            }else{
                imprimirLIsta(partidaCST.getItensConsumivelsT(), partidaCST);
                System.out.println("Escolha: ");
                ID = scan.nextInt();
            }
            scan.nextLine();
            System.out.print("posicao destino: ");
            
            CSTposicao destino = UI.traduzirPosicao(20, posicao);
             partidaCST.perfomaceUsarItem(destino, ID);
            
        }else if(resp == 2){
           
            if(partidaCST.getJogador().getTimeAtual() == time.ORACULO){
                imprimirLista(partidaCST.getItensEquipavelsO(), partidaCST);
                System.out.println("Escolha: ");
                ID = scan.nextInt();
            }else{
                imprimirLista(partidaCST.getItensEquipavelsT(), partidaCST);
                System.out.println("Escolha: ");
                ID = scan.nextInt();
            }
            scan.nextLine();
            System.out.print("posicao destino: ");
            
            CSTposicao destino = UI.traduzirPosicao(20, posicao);
             partidaCST.perfomaceEquiparItem(destino, ID);
        }
        

    }
    public static CSTposicao traduzirPosicao(int linhaMax, String posicao){
        try{
           
        String string = posicao;
        char coluna = string.charAt(0);
        int linha;
        if(string.length() == 3){
             linha = Integer.parseInt(string.substring(1, 3));
        }else{
            linha = Integer.parseInt(string.substring(1));
        }
        
        return new CSTposicao(coluna, linha, linhaMax);
        }catch(RuntimeException e){
            throw new InputMismatchException("erro lendo posicao");
            
        }
        
    }

    public static String printarSorteioAtqPecas(partidaCST partidaCST){
        List<CSTpeca> auxOraculo = partidaCST.getPecasOraculo();
        List<CSTpeca> auxTropa = partidaCST.getPecasTropa();
        //System.out.println();
        StringBuilder string = new StringBuilder();
        //System.out.println("ordem de atq da tropa: ");
        string.append("ordem de atq da tropa: " + "\n");
        for (CSTpeca csTpeca : auxTropa) {
            //System.out.print("-> " +csTpeca.getNome());
            string.append("-> " +csTpeca.getNome());

        }
        string.append("\n");
        //System.out.println();
         string.append("ordem de atq da Oraculo: " + "\n");
        //System.out.println("ordem de atq da Oraculo: ");
        for (CSTpeca csTpeca : auxOraculo) {
            string.append("-> " +csTpeca.getNome());

        }
        return string.toString();
    }

    public static void printarTabuleiro(CSTpeca[][] pecas, int numeroLinhas){
        for (int i = 0; i < pecas.length; i++) {
            if(numeroLinhas - i <= 9){
            System.out.print("0"+(numeroLinhas-i) + " ");
            }else{
            System.out.print((numeroLinhas-i) + " ");
            }
            for (int j = 0; j < pecas.length; j++) {
                printarPeca(pecas[i][j], false, j);
            }
            
            System.out.println();
        }
        StringBuilder string = new StringBuilder();
        string.append("   ");
        for (int i = 0; i < numeroLinhas; i++) {
            if(i == numeroLinhas - 1){
                string.append((char)('A' + i));
            }else{
                string.append((char)('A' + i) + " ");
            } 
        }
        System.out.println(string.toString());
        
    }
    public static void printarTabuleiro(CSTpeca[][] pecas, int numeroLinhas, boolean[][] possiveisAtaques){
        for (int i = 0; i < pecas.length; i++) {
            System.out.print((numeroLinhas - i) + " ");
            for (int j = 0; j < pecas.length; j++) {
                printarPeca(pecas[i][j], possiveisAtaques[i][j], j);
            }
            System.out.println();
        }
        
        StringBuilder string = new StringBuilder();
        string.append("   ");
        for (int i = 0; i < numeroLinhas; i++) {
            if(i == numeroLinhas - 1){
                string.append((char)('A' + i));
            }else{
                string.append((char)('A' + i) + " ");
            } 
        }
        System.out.println(string.toString());
    }



    private static void printarPeca(CSTpeca peca, boolean telaDeFundo, int coluna){
        if(telaDeFundo == true){
            System.out.print(ANSI_PURPLE_BACKGROUND);
        } 
        /*if(peca == null && telaDeFundo == false && coluna % 2 == 0){
            System.out.print(ANSI_CYAN_BACKGROUND + "-" + ANSI_RESET);
        }else if(peca == null && telaDeFundo == false && coluna % 2 == 1){
            System.out.print(ANSI_BLUE_BACKGROUND + "-" + ANSI_RESET);
        }else*/ if(peca == null){
            System.out.print("-" + ANSI_RESET);
        }else{
            if(peca.getTiminho() == time.ORACULO){
                System.out.print(ANSI_BLUE + peca + ANSI_RESET);
            }else if(peca.getTiminho() == time.OBSTACULO){
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }else{
                System.out.print(ANSI_RED + peca + ANSI_RESET);
            }
           
                
        }
        System.out.print(" ");
    }
    public static void printarTabuleiroPossiveisAlgumaCoisa(boolean teladefundo, int i, int j){
        if(teladefundo == true){
            tabuleiro[i][j].setStyle("-fx-fill : red;");
        }else{
         if(imagens[i][j] != null){
              if((i%2==0 && j%2==0) || (i%2==1 && j%2==1)){
                tabuleiro[i][j].setStyle("-fx-fill : white;");
            }else{
                tabuleiro[i][j].setStyle("-fx-fill : darkred;");
                }
           }
        }
       
    }
    public static String printarStatus(CSTpeca peca, int linhas){
        StringBuilder Status = new StringBuilder();
        Status.append("\n==========STATUS==========");
        Status.append("\n");
        Status.append("\tNome: " + peca.getNome() + "\n");
        Status.append("\tPeca: " + peca + "\n");
        Status.append("\tVida: " + peca.getVida());
        Status.append("\n");
        Status.append("\tAtaque: "+peca.getAtaque());
        Status.append("\n");
        Status.append("\tDefesa: "+peca.getDefesa());
        Status.append("\n");
        Status.append("\tRange geral: "+ peca.getRangeMovimento());
        Status.append("\n");
        CSTposicao posatual = new CSTposicao('A', 1, linhas);
        posatual.atualizarPosicao(peca.getPosicao(), linhas);
        Status.append("\tPosição: "+ posatual);
        Status.append("\n");
        if(peca.getInventario() == null){
            Status.append("\tInventario: "+ peca.getInventario());
        }else{
            Status.append("\tInventario: "+ peca.getInventario().getNomeItem());
        }
        
        
        Status.append("\n");
        Status.append("\n===========================");
        Status.append("\n");
        return Status.toString();
        
    }
    public static int escolhaDoFormato(Scanner scan){
        int linhas = 0, resp;
        System.out.println("Bem vindo ao Comp-Senai-Tactics");
        System.out.println("Escolha qual formato de mapa voce quer: ");
        System.out.println("1- 20 x 20");
        System.out.println("2- 10 x 10");
        System.out.println("3- 26 x 26");
        resp = scan.nextInt();
        switch (resp) {
            case 1:
                linhas = 20;
                break;
            case 2:
                linhas = 10;
                break;
            case 3:
                linhas = 26;
                break;
            default:
                break;
        }
        return linhas;
    }
  
}
