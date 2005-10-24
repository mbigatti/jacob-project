package com.jacob.com;

/**
 * This test class exists to test the WeakRefernce implementation .
 * 
 * It is not useful if there isn't one at this time
 * 
 * This will eventually be changed to a unit test.
 * 
 * Run in Eclipse with command line arguments
 * -Djava.library.path=d:/jacob/release -Dcom.jacob.autogc=false -Dcom.jacob.debug=false
 */
public class ROT2Test
{

    public static void main(String args[]) throws Exception
    {
        ROT2TestThread threads[] = new ROT2TestThread[4];
        for (int i = 0; i < threads.length; i++)
        {
            threads[i] = new ROT2TestThread("thread-" + i, 3000);
        }
        for (int i = 0; i < threads.length; i++)
        {
            threads[i].start();
        }
    }
}

/**
 * This wil try and exercise the thread support in the ROT 
 **/

class ROT2TestThread extends Thread
{
    private java.util.List ThreadObjects;

    private int initialRunSize = 0;
    /**
     * @param arg0
     */
    public ROT2TestThread(String arg0, int iStartCount)
    {
        super(arg0);
        initialRunSize = iStartCount;
        
    }

    /**
     * A semi-complexe serie of steps to put the ROT under stress. 1) discard
     * half the objects we've created 2) if size is greater than 1 but not a
     * even number, add 1 new object 3) stop when size is 1.
     * 
     * @see java.lang.Runnable#run()
     */
    public void run()
    {
        // something that keeps object references around
        // so the gc can't collect them
        // we need to create these in the thread so they end up in the right ROT table
        ThreadObjects = new java.util.ArrayList(initialRunSize);
        for (int i = 0; i < initialRunSize; i++)
        {
            // create the object
            Variant aNewVariant = new Variant(getName() + "_" + i);
            // create a hard reference to it
            ThreadObjects.add(aNewVariant);
        }

        while (ThreadObjects.size() > 1)
        {
            String message = "";
            message = getName()+" Workingset=" +ThreadObjects.size()
            	+" ROT: ";
			message += "(before additions and gc "+ROT.getThreadObjects(false).size()+")";
            // if there is an odd number of objects greater than 2
            if (ThreadObjects.size() > 2 && ThreadObjects.size() % 2 != 0)
            {
                // add a new object
                Variant aNewVariant = new Variant(getName() + "_*" + ThreadObjects.size());
                ThreadObjects.add(aNewVariant);
            }
            // now iterate across all the objects in our list
            for (int i = ThreadObjects.size(); i > 0; i--)
            {
                // removing every other one?
                if (i % 2 == 0)
                {
                    // remove the reference so gc can get it
                    ThreadObjects.remove(i-1);
                }

            }
            
            try {
                // simulate the system under load and run the GC
                // should end up with weak references with no objects attached
                Thread.sleep(9);
            } catch (InterruptedException e){
                // the VM doesn't want us to sleep anymore,
                // so get back to work
            }
			message += " (before gc, after additions "+ROT.getThreadObjects(false).size()+")";
			System.gc();
			message += " (after System.gc "+ROT.getThreadObjects(false).size()+")";
			System.out.println(message);
        }
    }

    /**
     * Another test would be to overide this to always return the same name.
     * That would really screw the ROT!
     * 
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return super.toString();
    }
}