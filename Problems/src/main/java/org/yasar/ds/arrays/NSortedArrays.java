package org.yasar.ds.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;


/**
 * 
 * http://karmaandcoding.blogspot.in/2012/02/merge-n-sorted-listsarrays.html
 * 
 * @author jyasar
 *
 */
public class NSortedArrays
{

	private static final int N = 7;
	private static HashMap<Integer, ArrayList<Integer>> arrayMap = new HashMap<Integer, ArrayList<Integer>>();
	private static int TOTAL = 0;

	public static void main(String[] args)
	{
		createArrays();
		System.out.println("Number of arrays: " + N);
		printArrays();
		ArrayList<Integer> mergedList = mergeArrays();
		System.out.println("Final merged lists data: ");
		printMergedList(mergedList);
		System.out.println();

	}

	public static void printMergedList(ArrayList<Integer> mergedList)
	{
		if(mergedList == null || mergedList.size() < 1)
		{
			System.out.println("Merged List is EMPTY!");
		}
		Iterator<Integer> iter = mergedList.iterator();

		while (iter.hasNext())
		{
			System.out.print(iter.next() + " ");
		}
		System.out.println();
	}

	public static ArrayList<Integer> mergeArrays()
	{
		ArrayList<Integer> mergedList = new ArrayList<Integer>();
		Set<Integer> keySet = arrayMap.keySet();
		Comparator<NSortedNode> comparator = new NumericComparator();
		PriorityQueue<NSortedNode> minHeap = new PriorityQueue<NSortedNode>(TOTAL, comparator);

		Iterator<Integer> iter = keySet.iterator();
		while (iter.hasNext())
		{
			int key = iter.next();
			ArrayList<Integer> list = arrayMap.get(key);
			if(list != null)
			{
				Integer data = list.remove(0);
				NSortedNode node = new NSortedNode(data, key);
				minHeap.add(node);
			}
		}

		while (minHeap.size() > 0)
		{
			NSortedNode node = minHeap.remove();
			// System.out.print(node.data + " ");
			mergedList.add(node.data);
			int id = node.id;
			ArrayList<Integer> list = arrayMap.get(id);
			if(list != null && list.size() > 0)
			{
				Integer data = list.remove(0);
				NSortedNode newNode = new NSortedNode(data, id);
				minHeap.add(newNode);
			}
		}

		return mergedList;

	}

	public static void createArrays()
	{
		Random rand = new Random();
		for (int i = 0; i < N; i++)
		{
			int size = rand.nextInt(5) + 5;
			TOTAL += size;
			ArrayList<Integer> numList = new ArrayList();
			for (int j = 0; j < size; j++)
			{
				int value = rand.nextInt(1000) + 1;
				numList.add(value);
			}
			Collections.sort(numList);
			arrayMap.put(i + 1, numList);
		}
	}

	public static void printArrays()
	{
		Iterator it = arrayMap.entrySet().iterator();

		while (it.hasNext())
		{
			Map.Entry<Integer, ArrayList<Integer>> pair = (Map.Entry<Integer, ArrayList<Integer>>) it
					.next();
			ArrayList<Integer> list = pair.getValue();
			Iterator liter = list.iterator();
			while (liter.hasNext())
			{
				System.out.print(liter.next() + " ");
			}
			System.out.println();
		}
	}
}

class NSortedNode
{
	int data;
	int id;

	NSortedNode(int data, int id)
	{
		this.data = data;
		this.id = id;
	}
}

class NumericComparator implements Comparator<NSortedNode>
{

	@Override
	public int compare(NSortedNode o1, NSortedNode o2)
	{
		if(o1.data < o2.data)
		{
			return -1;
		}
		else if(o1.data > o2.data)
		{
			return 1;
		}
		return 0;
	}

}