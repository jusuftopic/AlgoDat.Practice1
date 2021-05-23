package ab1;

import ab1.impl.Topic.Ab1Impl;
import ab1.Ab1.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class Ab1Test {

	private Random rand = new Random(System.currentTimeMillis());

	private static Ab1 ab1Impl = new Ab1Impl();

	private static int NUM_TESTS = 50;
	private static int NUM_TESTS_LARGE = 500000;
	private static int ARRAY_SIZE_SMALL = 7500;
	private static int ARRAY_SIZE_LARGE = 500000;
	private static int ARRAY_SIZE_HUGE = 25_000_000;

	@Test
	public void testIsHeapSmall()
	{
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_SMALL);
			assertTrue(!ab1Impl.isHeap(1, test.length, test));

			test = getRandomHeap(ARRAY_SIZE_SMALL);
			assertTrue(ab1Impl.isHeap(1, test.length, test));
		}
	}

	@Test
	public void testIsHeapLarge()
	{
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_LARGE);
			assertTrue(!ab1Impl.isHeap(1, test.length, test));

			test = getRandomHeap(ARRAY_SIZE_LARGE);
			assertTrue(ab1Impl.isHeap(1, test.length, test));
		}
	}

	@Test
	public void testMaxHeapOnEmptyArray(){

		int [] data = new int[]{};
		int i = 1;
		int j = data.length;

		Assertions.assertTrue(ab1Impl.isHeap(i,j,data));
	}

	@Test
	public void testMaxHeapOnOneElement(){

		int [] data = new int[] {9};
		int i = 1;
		int j = data.length;

		Assertions.assertTrue(ab1Impl.isHeap(i,j,data));
	}

	@Test
	public void testMaxHeapOnMoreElements(){

		int [] data = new int [] {9,9,7,7,5,4};
		int i = 1;
		int j = data.length;

		Assertions.assertTrue(ab1Impl.isHeap(i,j,data));
	}

	@Test
	public void testToHeapSmall()
	{
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_SMALL);
			ab1Impl.toHeap(test);
			assertTrue(ab1Impl.isHeap(1, test.length, test));
		}
	}

	@Test
	public void testToHeapLarge()
	{
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_LARGE);
			ab1Impl.toHeap(test);
			assertTrue(ab1Impl.isHeap(1, test.length, test));
		}
	}


	@Test
	public void testToHeapSorted()
	{
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_SMALL);
			Arrays.sort(test);
			ab1Impl.toHeap(test);
			assertTrue(ab1Impl.isHeap(1, test.length, test));
		}
	}

	@Test
	public void testHeapCornerCases()
	{
		// empty array
		int[] test = new int[0];
		ab1Impl.toHeap(test);

		// array of size 1
		test = getRandomArray(1);
		ab1Impl.toHeap(test);
		assertTrue(ab1Impl.isHeap(1, test.length, test));

		// array with all elements zero
		test = new int[ARRAY_SIZE_SMALL];
		ab1Impl.toHeap(test);
		assertTrue(ab1Impl.isHeap(1, test.length, test));

		// array with all elements zero, except the last one
		test = new int[ARRAY_SIZE_SMALL];
		test[test.length - 1] = 1;
		ab1Impl.toHeap(test);
		assertTrue(ab1Impl.isHeap(1, test.length, test));
	}

	@Test
	public void testHeapConstructionOnEmptyArray(){

		int [] data = new int[0];
		int i = 1;
		int j = data.length;

		ab1Impl.toHeap(data);

		Assertions.assertTrue(ab1Impl.isHeap(i,j,data));
	}

	@Test
	public void testHeapConstructionOnOneElement(){

		int [] data = new int[] {9};
		int i = 1;
		int j = data.length;

		ab1Impl.toHeap(data);

		Assertions.assertTrue(ab1Impl.isHeap(i,j,data));
	}

	@Test
	public void testHeapOnConstructionMoreElementsWithOneSwap(){

		int[] data = new int[] {9,8,5,4,3,7};
		int [] expected = new int[] {9,8,7,4,3,5};

		ab1Impl.toHeap(data);

		assertArrayEquals(expected,data);
	}

	@Test
	public void testHeapSortSmall()
	{
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_SMALL);
			int[] copy = Arrays.copyOf(test, test.length);
			ab1Impl.heapsort(test);
			Arrays.sort(copy);
			assertArrayEquals(copy, test);
		}
	}

	@Test
	public void testHeapSortLarge()
	{
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_LARGE);
			int[] copy = Arrays.copyOf(test, test.length);
			ab1Impl.heapsort(test);
			Arrays.sort(copy);
			assertArrayEquals(copy, test);
		}
	}

/*	@Test
	public void testToMaxHeap(){

		int [] data = new int[] {2,7,8,1,3,9};
		int length = data.length;

		int [] expected = new int[] {8,7,2,1,3,9};

		ab1Impl.toMaxHeap(data,length-1);

		assertArrayEquals(expected,data);

	}*/

	@Test
	public void testHeapSort(){

		int[] data = new int[] {9,7,8,1,3,2};
		int[] expected = new int[] {1,2,3,7,8,9};

		ab1Impl.heapsort(data);

		assertArrayEquals(expected,data);
	}

	@Test
	public void testListInsert()
	{
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_SMALL);
			ListNode head = null;
			for(int j = 0; j < test.length; ++j)
			{
				head = ab1Impl.insert(head, test[j]);
				assertNotNull(head);
			}
			Arrays.sort(test);
			for(int j = 0; j < test.length; ++j)
			{
				assertEquals(test[j], head.value);
				head = head.next;
			}
		}
	}

	@Test
	public void testInsertOnEmptyList(){

		ListNode head = null;
		ListNode nodeToInsert = new ListNode();
		nodeToInsert.value = 2;

		Assertions.assertEquals(2,ab1Impl.insert(head,2).value);
	}

	@Test
	public void testInsertAsSmallestElementInList(){

		ListNode head = new ListNode();
		head.value=2;
		ListNode node2 = new ListNode();
		node2.value = 3;

		head.next = node2;


		Assertions.assertEquals(1,ab1Impl.insert(head,1).value);
	}

	@Test
	public void testInsertInMiddle(){

		ListNode head = new ListNode();
		ListNode node1 = new ListNode();
		ListNode node2 = new ListNode();
		ListNode node3 = new ListNode();

		head.value = 1;
		node1.value = 2;
		node2.value = 4;
		node3.value = 5;

		head.next = node1;
		node1.next = node2;
		node2.next = node3;

		ListNode nodeToInsert = new ListNode();
		nodeToInsert.value = 3;

		Assertions.assertEquals(nodeToInsert.value,ab1Impl.insert(node2,3).value);
	}

	@Test
	public void testInsertAtTheEnd(){
		ListNode head = new ListNode();
		ListNode node1 = new ListNode();
		ListNode node2 = new ListNode();
		ListNode node3 = new ListNode();

		head.value = 1;
		node1.value = 2;
		node2.value = 3;
		node3.value = 4;

		head.next = node1;
		node1.next = node2;
		node2.next = node3;

		ListNode nodeToInsert = new ListNode();
		nodeToInsert.value = 5;

		Assertions.assertEquals(nodeToInsert.value,ab1Impl.insert(node3,5).next.value);


	}

	@Test
	public void testListSearch()
	{
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_SMALL);
			ListNode head = null;
			for(int j = 0; j < test.length; ++j)
			{
				head = ab1Impl.insert(head, test[j]);
				assertNotNull(head);
			}
			ListNode found = ab1Impl.search(head, test[0]);
			for(int j = 0; j < test.length; ++j)
			{
				if(found == head) break;
				head = head.next;
			}
			assertNotNull(head); // check that we found the node
		}
	}

	@Test
	public void testSearchOnEmptyList(){

		ListNode head = null;

		Assertions.assertNull(ab1Impl.search(head,5));
	}

	@Test
	public void testSearchValueInList(){
		ListNode head = new ListNode();
		ListNode node1 = new ListNode();
		ListNode node2 = new ListNode();
		ListNode node3 = new ListNode();

		head.value = 1;
		node1.value = 3;
		node2.value = 5;
		node3.value = 7;

		head.next = node1;
		node1.next = node2;
		node2.next = node3;

		Assertions.assertEquals(node2,ab1Impl.search(head,5));

	}

	@Test
	public void testSearchValueNotInList(){

		ListNode head = new ListNode();
		ListNode node1 = new ListNode();
		ListNode node2 = new ListNode();
		ListNode node3 = new ListNode();

		head.value = 1;
		node1.value = 3;
		node2.value = 5;
		node3.value = 7;

		head.next = node1;
		node1.next = node2;
		node2.next = node3;

		Assertions.assertNull(ab1Impl.search(head,2));
	}

	@Test
	public void testSearchValueGraetherThenLastElementValue(){
		ListNode head = new ListNode();
		ListNode node1 = new ListNode();
		ListNode node2 = new ListNode();
		ListNode node3 = new ListNode();

		head.value = 1;
		node1.value = 3;
		node2.value = 5;
		node3.value = 7;

		head.next = node1;
		node1.next = node2;
		node2.next = node3;

		Assertions.assertNull(ab1Impl.search(head,9));
	}


	@Test
	public void testListMinimum()
	{
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_SMALL);
			int min = Integer.MAX_VALUE;
			ListNode head = null;
			for(int j = 0; j < test.length; ++j)
			{
				head = ab1Impl.insert(head, test[j]);
				assertNotNull(head);
				if(min > test[j]) min = test[j];
			}
			ListNode foundMin = ab1Impl.minimum(head);
			assertEquals(min, foundMin.value);
		}
	}

	@Test
	public void testMinimumOnEmptyList(){

		ListNode head = null;

		Assertions.assertNull(ab1Impl.minimum(head));
	}

	@Test
	public void testMinimumOnNotEmptyList(){

		ListNode head = new ListNode();
		ListNode node1 = new ListNode();
		ListNode node2 = new ListNode();

		head.value = 1;
		node1.value = 3;
		node2.value = 4;

		Assertions.assertEquals(head,ab1Impl.minimum(head));

	}

	@Test
	public void testMergeSortSmall()
	{
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_SMALL);
			int[] copy = Arrays.copyOf(test, test.length);
			ab1Impl.mergesort(test);
			Arrays.sort(copy);
			assertArrayEquals(copy, test);
		}
	}

	@Test
	public void testMergeSortLarge()
	{
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_LARGE);
			int[] copy = Arrays.copyOf(test, test.length);
			ab1Impl.mergesort(test);
			Arrays.sort(copy);
			assertArrayEquals(copy, test);
		}
	}




	private int[] getRandomArray(int size) {
		int[] arr = new int[size];
		for (int i = 0; i < size; ++i)
			arr[i] = Math.abs(rand.nextInt(2 * size));
		return arr;
	}

	private int[] getRandomHeap(int size) {
		int[] arr = new int[size];
		arr[0] = Integer.MAX_VALUE - Math.abs(rand.nextInt(10));

		int pos = 0;
		while(true)
		{
			int l = 2*pos + 1;
			int r = 2*pos + 2;

			if(l >= arr.length) break;
			arr[l] = arr[pos] - Math.abs(rand.nextInt(9) + 1);

			if(r >= arr.length) break;
			arr[r] = arr[pos] - Math.abs(rand.nextInt(9) + 1);

			++pos;
		}

		return arr;
	}
}
