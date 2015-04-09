package org.yasar.ds.arrays;

public class Sort2Arrays
{
	
	//http://www.programcreek.com/2012/12/leetcode-merge-two-sorted-lists-java/
	class ListNode
	{
		public ListNode(int v)
		{
			val =v; next = null;
		}
		int val; ListNode next;
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2)
	{

		ListNode p1 = l1;
		ListNode p2 = l2;

		ListNode fakeHead = new ListNode(0);
		ListNode p = fakeHead;

		while (p1 != null && p2 != null)
		{
			if(p1.val <= p2.val)
			{
				p.next = p1;
				p1 = p1.next;
			}
			else
			{
				p.next = p2;
				p2 = p2.next;
			}

			p = p.next;
		}

		if(p1 != null)
			p.next = p1;
		if(p2 != null)
			p.next = p2;

		return fakeHead.next;
	}
}
