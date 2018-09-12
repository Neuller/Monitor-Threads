package monitor;

import java.util.Scanner;

// Prática Interrompendo Threads
// @Ncesar;

public class Monitor implements Runnable {
    
    private Thread th;
    private boolean monitorando;
    
    public Monitor(){
        monitorando = true;
        th = new Thread(this);
    }
    
    public void iniciar(){
        th.start();
    }
    
    public void parar(){
        monitorando = false;
        th.interrupt();
        try {
            th.join(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        monitor.iniciar();
        Scanner sc = new Scanner(System.in);
        
        boolean monitorar = true;
        do{
            System.out.println("Continuar Monitoramento S/N?");
            String resp = sc.next();
            if (resp.equalsIgnoreCase("N") == true){
                monitorar = false;
                monitor.parar();
            }
        }while (monitorar == true);
        sc.close();
    }

    @Override
    public void run() {
        System.out.println("Iniciando Monitoramento.");
        while (monitorando == true){
            // Verifica se o Sistema alvo ainda está em execução
            System.out.println("Monitorando.");
            if(th.isInterrupted() == true){
                System.out.println("Parando Monitoramento.");
                return;
            }try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                monitorando = false;
            }
        }
    }

    
}
