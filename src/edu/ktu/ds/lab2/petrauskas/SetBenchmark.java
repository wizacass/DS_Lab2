package edu.ktu.ds.lab2.petrauskas;

import edu.ktu.ds.lab2.demo.Timekeeper;
import edu.ktu.ds.lab2.gui.ValidationException;
import edu.ktu.ds.lab2.utils.Ks;

import java.util.HashSet;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.TreeSet;

public class SetBenchmark
{
    private static final int[] counts = {10000, 20000, 40000, 80000};
    private static final int maxBound = 4096 * 4096;
    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("edu.ktu.ds.lab2.gui.messages");

    private final Timekeeper timeKeeper;
    private final String[] errors;

    private TreeSet<Integer> treeSet;
    private HashSet<Integer> hashSet;
    private Random rnd;

    public SetBenchmark()
    {
        timeKeeper = new Timekeeper(counts);

        treeSet = new TreeSet<>();
        hashSet = new HashSet<>();
        rnd = new Random(2017);

        errors = new String[]{
                MESSAGES.getString("badSetSize"),
                MESSAGES.getString("badInitialData"),
                MESSAGES.getString("badSetSizes"),
                MESSAGES.getString("badShuffleCoef")
        };
    }

    public void run()
    {
        Ks.oun("Benchmark starts!");
        try
        {
            benchmark();
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        catch (Exception ex)
        {
            ex.printStackTrace(System.out);
        }

    }

    private void benchmark() throws InterruptedException
    {
        try
        {
            for (var count : counts)
            {
                clear();
                generate(count);
                int number = rnd.nextInt(maxBound);
                timeKeeper.startAfterPause();

                timeKeeper.start();

                treeSet.contains(number);
                timeKeeper.finish("Tree");

                hashSet.contains(number);
                timeKeeper.finish("Hash");

                timeKeeper.seriesFinish();
            }
            timeKeeper.logResult("");
        }
        catch (ValidationException e)
        {
            if (e.getCode() >= 0 && e.getCode() <= 3)
            {
                timeKeeper.logResult(errors[e.getCode()] + ": " + e.getMessage());
            }
            else if (e.getCode() == 4)
            {
                timeKeeper.logResult(MESSAGES.getString("allSetIsPrinted"));
            }
            else
            {
                timeKeeper.logResult(e.getMessage());
            }
        }
    }

    private void clear()
    {
        treeSet.clear();
        hashSet.clear();
    }

    private void generate(int count)
    {
        for (int i = 0; i < count; i++)
        {
            int number = rnd.nextInt(maxBound);
            treeSet.add(number);
            hashSet.add(number);
        }
    }

    public static void main(String... args)
    {
        new SetBenchmark().run();
        Ks.oun("Benchmark ends!");
    }
}
