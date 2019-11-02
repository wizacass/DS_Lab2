package edu.ktu.ds.lab2.petrauskas;

import edu.ktu.ds.lab2.utils.BstSet;
import edu.ktu.ds.lab2.utils.Ks;

import java.util.Random;

public class TreeTest
{
    private BstSet<Integer> mainTree;
    private BstSet<Integer> anotherTree;
    private Random rnd;

    private static final int maxBound = 128;

    private TreeTest()
    {
        mainTree = new BstSet<>();
        anotherTree = new BstSet<>();
        rnd = new Random();
        rnd.setSeed(2017);
    }

    private void run()
    {
        Ks.oun("Tree test starting...");

        addElements(mainTree, 16);
        addElements(anotherTree, 8);
        printTree(mainTree, "Main");
        printTree(anotherTree, "Another");

        boolean addAllSuccess = mainTree.addAll(anotherTree);
        Ks.oun(addAllSuccess ? "Merging successful" : "Merging not successful");
        printTree(mainTree, "Merge");

        boolean removeAllSuccess = mainTree.removeAll(anotherTree);
        Ks.oun(removeAllSuccess ? "Remove successful" : "Remove not successful");
        printTree(mainTree, "Remove");

        var toElement = rnd.nextInt(maxBound);
        Ks.oun("Head element: " + toElement);

        var headSetInclusive = mainTree.headSet(toElement, true);
        printTree(headSetInclusive, "Head Inclusive");

        var headSet = mainTree.headSet(toElement, false);
        printTree(headSet, "Inclusive");

        var higherElement = rnd.nextInt(maxBound);
        Ks.oun("Element: " + higherElement + "; Higher Element: " + mainTree.higher(higherElement));

        Ks.oun("Last element is: " + mainTree.last());
        var removedLast = mainTree.pollLast();
        printTree(mainTree, "Remove Last");
    }

    private void addElements(BstSet<Integer> tree, int count)
    {
        for (int i = 0; i < count; i++)
        {
            tree.add(rnd.nextInt(maxBound));
        }
    }

    private void printTree(BstSet<Integer> tree, String header)
    {
        Ks.oun(header + " Tree view:");
        System.out.println(tree.toVisualizedString(""));

        Ks.ou("Elements: ");
        for (var element: tree)
        {
            System.out.print(element + " ");
        }
        System.out.println();
        Ks.oun("Total: " + tree.size());
    }

    public static void main(String... args)
    {
        new TreeTest().run();
        Ks.oun("Tree test complete!");
    }
}
