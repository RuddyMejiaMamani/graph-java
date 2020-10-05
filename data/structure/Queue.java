package data.structure;

public class Queue {
    class Node {

        int info;

        Node next;

    }

    

    private Node Start,End;

    

    Queue() {

        Start=null;

        End=null;

    }

    

    boolean Empty (){

        if (Start == null)

            return true;

        else

            return false;

    }



    void insert (int info)

    {

        Node New;

        New = new Node ();

        New.info = info;

        New.next = null;

        if (Empty ()) {

            Start = New;

            End = New;

        } else {

            End.next = New;

            End = New;

        }

    }



    int extract ()

    {

        if (!Empty ())

        {

            int informacion = Start.info;

            if (Start == End){

                Start = null;

                End = null;

            } else {

                Start = Start.next;

            }

            return informacion;

        } else

            return Integer.MAX_VALUE;

    }



    public void rintAll() {

        Node reco=Start;

        System.out.println("Listado de todos los elementos de la cola.");

        while (reco!=null) {

            System.out.print(reco.info+"-");

            reco=reco.next;

        }

        System.out.println();

    }
    
}
