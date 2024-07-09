package org.example;



class Resource{
    private int data;
    private boolean isEmpty = true;

    public synchronized void produce(int x){
        System.out.println("Start producing");
        while(!isEmpty){
            try{
                wait();
            }catch(InterruptedException ex){
                System.out.println(ex.getMessage());
            }


        }
        data = x;
        isEmpty =false;
        System.out.println("Value Produced" + data );
        notify();
    }

    public synchronized void consume(){
        System.out.println("Start Consuming");
        while(isEmpty){
            try{
                wait();
            }catch (InterruptedException ex){
                System.out.println(ex.getMessage());
            }
            isEmpty = true;
            System.out.println("Produced: " + data);
            notify();
        }
    }
}

class Producer implements Runnable{
    private final Resource resource;

    Producer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++){
            resource.produce(i);
            try{
                Thread.sleep(1000);

            }catch(InterruptedException ex){
                System.out.println(ex.getMessage());
            }

        }
    }
}


class Consumer implements Runnable{
    private final Resource resource;

    Consumer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int  i =0; i < 5; i++){
            resource.consume();
            try{
                Thread.sleep(1000);
            }catch (InterruptedException ex){
                System.out.println(ex.getMessage());
            }

        }
    }
}

public class InterThread {
    public static void main(String[] args){
        Resource res = new Resource();

        Thread producer_thread = new Thread(new Producer(res));
        Thread consumer_thread = new Thread(new Consumer(res));
        Thread thread = new Thread(()->{

        });

        producer_thread.start();
        consumer_thread.start();

        try{
            producer_thread.join();
            consumer_thread.join();
        }catch (InterruptedException ex){
            System.out.println(ex.getMessage());
        }
    }
}
